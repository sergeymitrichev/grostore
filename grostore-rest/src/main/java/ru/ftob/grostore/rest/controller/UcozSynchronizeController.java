package ru.ftob.grostore.rest.controller;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.ftob.grostore.ucoz.repository.ApiProductRepositoryImpl;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/sync")
public class UcozSynchronizeController {

    private final StorageService storageService;

    private final ApiProductRepositoryImpl apiProductRepository;

    @Autowired
    public UcozSynchronizeController(StorageService storageService, ApiProductRepositoryImpl apiProductRepository) {
        this.storageService = storageService;
        this.apiProductRepository = apiProductRepository;
    }

    @PostMapping("/products/")
    public ResponseEntity<?> products(@RequestParam("file") MultipartFile file, @RequestParam List<ProductImportFieldType> fields, int toSkip) {
        storageService.store(file);
        List<String> resultLog = new ArrayList<>();
        resultLog.add("*** Reading from file with fields: " + fields);


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
                    UcozProduct product = apiProductRepository.getBySku(p.getSku());
                    if (null != product && null != p.getPriceIn()) {
                        product.setPriceIn(p.getPriceIn());
                        apiProductRepository.save(product);
                    }
                } catch (ExecutionException | InterruptedException | IOException e) {
                    System.out.println("Can't connect to api ucoz");
                }
            });
            System.out.println(xlsProducts);

        } catch (IOException e) {
            resultLog.add("*** Cannot open file: " + e);
        }
        resultLog.add("*** Finish reading from file");
        try {
            apiProductRepository.getBySku("364547");
        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(resultLog);
    }
}
