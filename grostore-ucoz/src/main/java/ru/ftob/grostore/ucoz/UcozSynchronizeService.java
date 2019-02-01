package ru.ftob.grostore.ucoz;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ftob.grostore.model.analytics.ProductAnalytic;
import ru.ftob.grostore.model.image.ProductImage;
import ru.ftob.grostore.model.ingredient.Ingredient;
import ru.ftob.grostore.model.modification.ModificationFloat;
import ru.ftob.grostore.model.modification.ModificationFloatValue;
import ru.ftob.grostore.model.modification.ModificationString;
import ru.ftob.grostore.model.modification.ModificationStringValue;
import ru.ftob.grostore.model.product.Price;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.productlist.Brand;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.model.stock.ProductInStock;
import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.service.analytic.ProductAnalyticService;
import ru.ftob.grostore.service.ingredient.IngredientService;
import ru.ftob.grostore.service.modification.ModificationFloatService;
import ru.ftob.grostore.service.modification.ModificationStringService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ftob.grostore.model.product.PriceType.*;

@Service
public class UcozSynchronizeService {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final StockService stockService;

    private final BrandService brandService;

    private final SnapshotCategoryRepository snapshotCategoryRepository;

    private final ProductAnalyticService productAnalyticService;

    private final IngredientService ingredientService;

    private final ModificationStringService modificationStringService;

    private final ModificationFloatService modificationFloatService;

    public UcozSynchronizeService(CategoryService categoryService, ProductService productService, StockService stockService, BrandService brandService, SnapshotCategoryRepository snapshotCategoryRepository, ProductAnalyticService productAnalyticService, IngredientService ingredientService, ModificationStringService modificationStringService, ModificationFloatService modificationFloatService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.stockService = stockService;
        this.brandService = brandService;
        this.snapshotCategoryRepository = snapshotCategoryRepository;
        this.productAnalyticService = productAnalyticService;
        this.ingredientService = ingredientService;
        this.modificationStringService = modificationStringService;
        this.modificationFloatService = modificationFloatService;
    }

    private String getStringCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        String result = "";
        if (null == cell) {
            return result;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING: {
                result = cell.getStringCellValue();
                break;
            }
            case NUMERIC: {
                result = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case BOOLEAN: {
                result = cell.getBooleanCellValue() ? "1" : "";
                break;
            }
            default: {
                result = "";
            }
        }
        return result;
    }

    private Integer getIntegerCellValue(Row row, int cellnum) {
        try {
            return new Double(getStringCellValue(row, cellnum)).intValue();
        } catch (Exception ignored) {
        }
        return 0;
    }

    private Float getFloatCellValue(Row row, int cellnum) {
        return (float) getIntegerCellValue(row, cellnum);
    }

    private Long getLongCellValue(Row row, int cellnum) {
        return Math.round(getDoubleCellValue(row, cellnum));
    }

    private Double getDoubleCellValue(Row row, int cellnum) {
        try {
            return new Double(getStringCellValue(row, cellnum));
        } catch (Exception ignored) {
        }
        return 0d;
    }


    public void saveProducts(Workbook workbook) {
        Sheet firstSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = firstSheet.iterator();
        iterator.next(); // column numbers
        iterator.next(); // column names
        Row row;

        while (iterator.hasNext()) {
            row = iterator.next();
            String sku = getStringCellValue(row, 35);

            Product product = productService.getBySku(sku).orElse(new Product(sku));

            // check if category exists
            Category category = null;
            Optional<SnapshotCategory> optSnapCat = snapshotCategoryRepository
                    .findById(getIntegerCellValue(row, 1));
            if (optSnapCat.isPresent()) {
                String catName = optSnapCat.get().getName();
                try {
                    category = categoryService.getByName(catName);
                } catch (NotFoundException e) {
                    category = categoryService.get(1);
                }
            } else {
                category = categoryService.get(1);
            }
            product.addCategory(category);

            // Add simple fields
            product.setHgu(getStringCellValue(row, 3));
            product.setBrief(getStringCellValue(row, 5));
            product.setName(getStringCellValue(row, 6));
            String description = getStringCellValue(row, 7);
            product.setDescription(description);
            product.setEnabled(!getStringCellValue(row, 15).equals("1"));
            product.setMetaTitle(getStringCellValue(row, 17));
            product.setMetaDescription(getStringCellValue(row, 18));
            product.setUnit(getStringCellValue(row, 36));
            product.setWeight(getIntegerCellValue(row, 37));
            product.setRatingAverage(getDoubleCellValue(row, 25));
            product.setRatingCount(getIntegerCellValue(row, 26));
            product.setRatingSum(getIntegerCellValue(row, 27));
            product.setBarcode(getStringCellValue(row, 43));

            // check if brand exists
            // [;«](.*?)((&quot;)|») - between quotes
            // split by semicolon and delete all in (.*?)
            // parse stock from quotes, country and city
            // country \(.*?\)
            String brandName = getStringCellValue(row, 4)
                    .replace("&quot;", "")
                    .split(",")[0];
            Brand brand;
            try {
                brand = brandService.getByName(brandName);
            } catch (NotFoundException e) {
                brand = brandService.create(new Brand(brandName));
            }
            product.setBrand(brand);

            // add prices
            product.setPrices(new ArrayList<>());
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
                            Instant.ofEpochSecond(
                                    getIntegerCellValue(row, 21)), TimeZone
                                    .getDefault().toZoneId()));
            product.setUpdated(
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(
                                    getIntegerCellValue(row, 23)), TimeZone
                                    .getDefault().toZoneId()));


            // add analytics
            ProductAnalytic productAnalytic = new ProductAnalytic();
            if (null != product.getAnalytic()) {
                productAnalytic = product.getAnalytic();
            }

            productAnalytic.setViews(getLongCellValue(row, 30));
            productAnalytic.setAdditionsToCartQuantity(getLongCellValue(row, 29));
            productAnalytic.setSalesQuantity(getLongCellValue(row, 31));
            productAnalytic.setProduct(product);
            product.setAnalytic(productAnalytic);


            // parse ingredients
            Pattern p = Pattern.compile("Состав:(.*)Срок годности");
            Matcher m = p.matcher(description);
            if (m.find()) {
                Set<Ingredient> ingredients = new HashSet<>();
                String[] ingridientNames = m.group(1)
                        .trim()
                        .replace(";", ",")
                        .replace(".", "")
                        .split(",");
                for (String name : ingridientNames) {
                    name = name.trim().toLowerCase();
                    Ingredient ingredient;
                    try {
                        ingredient = ingredientService.getByName(name);
                    } catch (NotFoundException e) {
                        ingredient = ingredientService.create(new Ingredient(name));
                    }
                    ingredients.add(ingredient);
                }
                product.setIngredients(ingredients);
            }

            // parse modifications
            final String BEST_BEFORE_NAME = "Срок годности";
            p = Pattern.compile(BEST_BEFORE_NAME + ":(.*)");
            m = p.matcher(description);
            if (m.find()) {
                String bestBeforeValue = m.group(1);
                ModificationString modification;
                try {
                    modification = modificationStringService.getByName(BEST_BEFORE_NAME);
                } catch (NotFoundException e) {
                    modification = modificationStringService.create(new ModificationString(BEST_BEFORE_NAME));
                }
                ModificationStringValue modificationValue = product
                        .getModificationStringValues()
                        .stream()
                        .filter(mv -> BEST_BEFORE_NAME.equals(mv.getModificationString().getName()))
                        .findFirst()
                        .orElse(new ModificationStringValue(bestBeforeValue, modification));
                product.addModificationStringValue(modificationValue);
                modification.getCategories().addAll(product.getCategories());
                modificationStringService.update(modification);
            }

            Float caloriesValue = getFloatCellValue(row, 42);
            if(caloriesValue > 0f) {
                final String CALORIES_NAME = "Калорийность";
                final String CALORIES_UNIT = "ккал";
                ModificationFloat modification;
                try {
                    modification = modificationFloatService.getByName(CALORIES_NAME);
                } catch (NotFoundException e) {
                    modification = modificationFloatService.create(new ModificationFloat(CALORIES_NAME, CALORIES_UNIT));
                }
                ModificationFloatValue modificationValue = product
                        .getModificationFloatValues()
                        .stream()
                        .filter(mv -> CALORIES_NAME.equals(mv.getModificationFloat().getName()))
                        .findFirst()
                        .orElse(new ModificationFloatValue(caloriesValue, modification));
                product.addModificationFloatValue(modificationValue);
                modification.getCategories().addAll(product.getCategories());
                modificationFloatService.update(modification);
            }


            // generate image url from uid
            String imageUrl = "/img/p/" + getStringCellValue(row, 1) + ".jpg";
            ProductImage productImage = new ProductImage(imageUrl, product);
            product.addImage(productImage);

            product = productService.update(product);

            /* after save product */

            // check if stock exists
            if (!StringUtils.isEmpty(getStringCellValue(row, 38))) {
                if (sku.length() > 8) {
                    Stock stock = stockService.getWithProducts(3);
                    stock.addProduct(
                            new ProductInStock(
                                    product,
                                    getStringCellValue(row, 38))
                    );
                    stockService.update(stock);
                    // auchan
                } else if (sku.startsWith("TK")) {
                    // tay cosmetics
                    Stock stock = stockService.getWithProducts(4);
                    stock.addProduct(
                            new ProductInStock(
                                    product,
                                    getStringCellValue(row, 38))
                    );
                    stockService.update(stock);
                } else {
                    // metro
                    Stock stock = stockService.getWithProducts(1);
                    stock.addProduct(
                            new ProductInStock(
                                    product,
                                    getStringCellValue(row, 38))
                    );
                    stockService.update(stock);
                }
            }
            if (!StringUtils.isEmpty(getStringCellValue(row, 39))) {
                Stock stock = stockService.getWithProducts(2);
                stock.addProduct(
                        new ProductInStock(
                                product,
                                getStringCellValue(row, 39))
                );
                stockService.update(stock);
            }
        }
    }
}
