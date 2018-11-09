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
import ru.ftob.grostore.ucoz.handler.SnapshotHandler;
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

    private final SnapshotCategoryRepository snapshotCategoryRepository;

    private final ModelMapper mapper = new ModelMapper();

    private final SnapshotHandler handler;

    @Autowired
    public UcozSynchronizeController(StorageService storageService, SnapshotCategoryRepository snapshotCategoryRepository, SnapshotHandler handler) {
        this.storageService = storageService;
        this.snapshotCategoryRepository = snapshotCategoryRepository;
        this.handler = handler;
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
        xlsProducts.forEach(l -> {
            if (null == l.getSecond()) {
                if (l.getFirst() != null) {
                    category.set(snapshotCategoryRepository.findByName(l.getFirst()));
                }
            } else {
                int priceIn = 0;
                try {
                    priceIn = NumberFormat.getNumberInstance(Locale.FRANCE).parse(l.getFourth()).intValue();
                } catch (ParseException e) {
                    log.error("Cannot parse excel line: " + l.toString(), e);
                }
                String stock = "";
                switch (l.getFifth()) {
                    case "КД":
                        stock = "AUCHAN";
                        break;
                    case "СКЛАД":
                        stock = "STOCK";
                        break;
                    default:
                        stock = "METRO";
                }
                SnapshotProduct product = new SnapshotProduct(
                        Integer.valueOf(l.getFirst()),
                        l.getThird(),
                        category.get(),
                        l.getSecond(),
                        priceIn,
                        stock
                );
                products.add(product);
            }
        });
        return ResponseEntity.ok(handler.persistAllProducts(products));
    }

    @PostMapping("/categories/snapshot/")
    public ResponseEntity<?> snapCategories(@RequestParam(defaultValue = "/var/lib/grostore/ucoz-categories-snashot.json") String filePath) {
        ResponseEntity<?> response = null;
        File tmp = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<SnapshotCategory> categories = new ArrayList<>();
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
                        r[7]
                ));
            });
            response = ResponseEntity.ok(snapshotCategoryRepository.saveAll(categories));
        } catch (IOException e) {
            response = new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/products/")
    public ResponseEntity<?> products(@RequestParam("file") MultipartFile file, @RequestParam List<ProductImportFieldType> fields, int toSkip, String stock) {
        storageService.store(file);
        log.debug("Reading from file with fields: " + fields);

        try {
            File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
            XlsHandlerUtil.addFieldsToHeader(tmp, fields);
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .preferNullOverDefault(true)
                    .skip(toSkip)
                    .build();
            List<XlsProduct> xlsProducts = Poiji.fromExcel(tmp, XlsProduct.class, options);
            List<SnapshotProduct> productsToUpdate = new ArrayList<>();
            xlsProducts.forEach(x -> {
                SnapshotProduct p = new SnapshotProduct();
                mapper.map(x, p);
                if (p.getSku() != null && p.getPriceIn() != null) {
                    productsToUpdate.add(p);
                }
            });
            handler.updateAllProducts(productsToUpdate, stock);

        } catch (IOException e) {
            log.error("Cannot open file", e);
        }
        log.debug("Finish reading from file " + file.getOriginalFilename());

        return ResponseEntity.ok().build();
    }
}
