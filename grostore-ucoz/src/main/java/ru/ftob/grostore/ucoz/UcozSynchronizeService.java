package ru.ftob.grostore.ucoz;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.product.Price;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.productlist.Brand;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.model.stock.ProductInStock;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.BrandService;
import ru.ftob.grostore.service.productlist.CategoryService;
import ru.ftob.grostore.service.stock.StockService;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.ucoz.snapshot.SnapshotCategory;
import ru.ftob.grostore.ucoz.snapshot.SnapshotCategoryRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import static ru.ftob.grostore.model.product.PriceType.*;

@Service
public class UcozSynchronizeService {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final StockService stockService;

    private final BrandService brandService;

    private final SnapshotCategoryRepository snapshotCategoryRepository;

    public UcozSynchronizeService(CategoryService categoryService, ProductService productService, StockService stockService, BrandService brandService, SnapshotCategoryRepository snapshotCategoryRepository) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.stockService = stockService;
        this.brandService = brandService;
        this.snapshotCategoryRepository = snapshotCategoryRepository;
    }

    private String getStringCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        if (null != cell) {
            return cell.getStringCellValue();
        }
        return "";
    }

    private Integer getIntegerCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        if (null != cell) {
            return new Double(cell.getStringCellValue()).intValue();
        }
        return 0;
    }

    private Double getDoubleCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        if (null != cell) {
            return new Double(cell.getStringCellValue());
        }
        return 0d;
    }


    public void saveProducts(Workbook workbook) {
        Sheet firstSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = firstSheet.iterator();
        Row row = firstSheet.getRow(2);

        Iterator<Cell> cellIterator = row.cellIterator();

        List<Product> products = new ArrayList<>();
        while (iterator.hasNext()) {
            Product product = new Product();

            // check if category exists
            Category category = null;
            Optional<SnapshotCategory> optSnapCat = snapshotCategoryRepository
                    .findById(Integer.parseInt(row.getCell(1).getStringCellValue()));
            if (optSnapCat.isPresent()) {
                String catName = optSnapCat.get().getName();
                category = categoryService.getByName(catName);
            } else {
                category = categoryService.get(1);
            }
            product.addCategory(category);

            // Add simple fields
            product.setHgu(getStringCellValue(row, 3));
            product.setBrief(getStringCellValue(row, 5));
            product.setName(getStringCellValue(row, 6));
            product.setDescription(getStringCellValue(row, 7));
            product.setEnabled(!getStringCellValue(row, 15).equals("1"));
            product.setMetaTitle(getStringCellValue(row, 17));
            product.setMetaDescription(getStringCellValue(row, 18));
            String sku = getStringCellValue(row, 35);
            product.setSku(sku);
            product.setUnit(getStringCellValue(row, 36));
            product.setWeight(getIntegerCellValue(row, 37));
            product.setRatingAverage(getDoubleCellValue(row, 25));
            product.setRatingCount(getIntegerCellValue(row, 26));
            product.setRatingSum(getIntegerCellValue(row, 27));

            // check if brand exists
            // [;«](.*?)((&quot;)|») - between quotes
            // split by semicolon and delete all in (.*?)
            // parse stock from quotes, country and city
            // country \(.*?\)
            String brandName = getStringCellValue(row, 4).replace("&quot;", "").split(",")[0];
            Brand brand;
            try {
                brand = brandService.getByName(brandName);
            } catch (NotFoundException e) {
                brand = brandService.create(new Brand(brandName));
            }
            product.setBrand(brand);

            // add prices
            product.addPrice(new Price(
                    getIntegerCellValue(row, 11),
                    PRICE_TYPE_SOLD
            ));
            product.addPrice(new Price(
                    getIntegerCellValue(row, 12),
                    PRICE_TYPE_IN
            ));
            product.addPrice(new Price(
                    getIntegerCellValue(row, 13),
                    PRICE_TYPE_OLD_SOLD
            ));

            // add localdate time
            product.setCreated(
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(
                                    getIntegerCellValue(row, 21)), TimeZone
                                    .getDefault().toZoneId()));
            product.setUpdated(
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(
                                    getIntegerCellValue(row, 23)), TimeZone
                                    .getDefault().toZoneId()));


            // add analytics

            // parse modifications

            // parse ingredients

            // generate image url from uid


            productService.create(product);
            /* after save product */

            // check if stock exists
            if (sku.length() > 8) {
                stockService.get(3).addProduct(
                        new ProductInStock(
                                product,
                                getStringCellValue(row, 38))
                );
                // auchan
            } else if (sku.startsWith("TK")) {
                // tay cosmetics
                stockService.get(4).addProduct(
                        new ProductInStock(
                                product,
                                getStringCellValue(row, 38))
                );
            } else {
                // metro
                stockService.get(1).addProduct(
                        new ProductInStock(
                                product,
                                getStringCellValue(row, 38))
                );
                stockService.get(2).addProduct(
                        new ProductInStock(
                                product,
                                getStringCellValue(row, 39))
                );
            }

        }
    }
}
