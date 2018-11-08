package ru.ftob.grostore.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.rest.storage.StorageService;
import ru.ftob.grostore.service.util.XlsHandlerUtil;
import ru.ftob.grostore.service.xlsto.XlsProduct;
import ru.ftob.grostore.service.xlsto.XlsProductSnapshotRow;
import ru.ftob.grostore.ucoz.repository.ApiProductRepositoryImpl;
import ru.ftob.grostore.ucoz.snapshot.*;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/sync")
public class UcozSynchronizeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final StorageService storageService;

    private final ApiProductRepositoryImpl apiProductRepository;

    private final SnapshotCategoryRepository snapshotCategoryRepository;

    private final SnapshotProductRepository snapshotProductRepository;

    private final SnapshotWorkerRepository snapshotWorkerRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public UcozSynchronizeController(StorageService storageService, ApiProductRepositoryImpl apiProductRepository, SnapshotCategoryRepository snapshotCategoryRepository, SnapshotProductRepository snapshotProductRepository, SnapshotWorkerRepository snapshotWorkerRepository) {
        this.storageService = storageService;
        this.apiProductRepository = apiProductRepository;
        this.snapshotCategoryRepository = snapshotCategoryRepository;
        this.snapshotProductRepository = snapshotProductRepository;
        this.snapshotWorkerRepository = snapshotWorkerRepository;
    }

    @PostMapping("/products/snapshot/")
    public ResponseEntity<?> snapProducts(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .preferNullOverDefault(true)
                .skip(1)
                .build();
        List<XlsProductSnapshotRow> xlsProducts = Poiji.fromExcel(tmp, XlsProductSnapshotRow.class, options);
        AtomicReference<SnapshotCategory> category = new AtomicReference<>();
        List<SnapshotProduct> products = new ArrayList<>();
        SnapshotWorker worker = snapshotWorkerRepository.save(new SnapshotWorker());
        xlsProducts.forEach(l -> {
            if (null == l.getSecond()) {
                if(l.getFirst() != null) {
                    category.set(snapshotCategoryRepository.findByName(l.getFirst()));
                }
            } else {
                int priceIn = 0;
                try {
                    priceIn = NumberFormat.getNumberInstance(Locale.FRANCE).parse(l.getThird()).intValue();
                } catch (ParseException e) {
                    log.error("Cannot parse excel line: " + l.toString(), e);
                }
                SnapshotProduct product = new SnapshotProduct(Integer.valueOf(l.getFirst()), category.get(), l.getSecond(), priceIn, worker);
                products.add(product);
            }
        });
        return ResponseEntity.ok(snapshotProductRepository.saveAll(products));
    }

    @PostMapping("/categories/snapshot/")
    public ResponseEntity<?> snapCategories(@RequestParam(defaultValue = "/var/lib/grostore/ucoz-categories-snashot.json") String filePath) {
        ResponseEntity<?> response = null;
        File tmp = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<SnapshotCategory> categories = new ArrayList<>();
        SnapshotWorker worker = snapshotWorkerRepository.save(new SnapshotWorker());
        try {
            String[][] read = mapper.readValue(tmp, String[][].class);
            Arrays.stream(read).forEach(r -> {
                categories.add(new SnapshotCategory(
                        Integer.valueOf(r[0]),
                        r[3],
                        r[5],
                        Integer.valueOf(r[1]),
                        Integer.valueOf(r[2]),
                        r[4],
                        Integer.valueOf(r[6]),
                        r[7],
                        worker
                ));
            });
            response = ResponseEntity.ok(snapshotCategoryRepository.saveAll(categories));
        } catch (IOException e) {
            response = new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/products/")
    public ResponseEntity<?> products(@RequestParam("file") MultipartFile file, @RequestParam List<ProductImportFieldType> fields, int toSkip) {
        storageService.store(file);
        List<String> resultLog = new ArrayList<>();
        log.debug("Reading from file with fields: " + fields);

        try {
            File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
            XlsHandlerUtil.addFieldsToHeader(tmp, fields);
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .preferNullOverDefault(true)
                    .skip(toSkip)
                    .build();
            List<XlsProduct> xlsProducts = Poiji.fromExcel(tmp, XlsProduct.class, options);
            xlsProducts.forEach(p -> {
                try {
                    Optional<SnapshotProduct> snapshotProduct = snapshotProductRepository.findBySku(p.getSku());
                    UcozProduct product = null;
                    if(snapshotProduct.isPresent()) {
                        mapper.map(snapshotProduct.get(), product);
                    } else {
                        product = apiProductRepository.getBySku(p.getSku());
                    }

                    if (null != product && null != p.getPriceIn()) {
                        mapper.map(product, snapshotProduct);

                        product.setPriceIn(p.getPriceIn());
                        apiProductRepository.save(product);
                        log.debug("XLS line successfully updated " + product);
                    }
                } catch (ExecutionException | InterruptedException | IOException e) {
                    log.error("Can't connect to api ucoz", e);
                }
            });

        } catch (IOException e) {
            log.error("Cannot open file", e);
        }
        log.debug("Finish reading from file " + file.getOriginalFilename());

        return ResponseEntity.ok().build();
    }
}
