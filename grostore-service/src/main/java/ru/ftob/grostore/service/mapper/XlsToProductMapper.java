package ru.ftob.grostore.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.service.util.ReflectionUtil;
import ru.ftob.grostore.service.util.exception.ConfigurationException;
import ru.ftob.grostore.service.xlsto.XlsProduct;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class XlsToProductMapper extends AbstractMapper<XlsProduct, Product> {

    @Override
    public Product map(XlsProduct xlsProduct, Product product) throws ConfigurationException {
        //TODO move to validator layer
        if(StringUtils.isEmpty(xlsProduct.getCategoriesString())) {
            return null;
        }
        populateBasicFields(product, xlsProduct);
        populateCustomFields(product, xlsProduct);
        populateGeneratedFields(product);
        return product;
    }

    private void populateBasicFields(Product product, XlsProduct xlsProduct) throws ConfigurationException {

        List<String> similarFields = ReflectionUtil.findSimilarFields(product.getClass(), xlsProduct.getClass());
        Class clazz = product.getClass().getSuperclass();
        while(clazz != null) {
            similarFields.addAll(ReflectionUtil.findSimilarFields(clazz, xlsProduct.getClass()));
            clazz = clazz.getSuperclass();
        }
        ReflectionUtil.copyFields(xlsProduct, product, similarFields);
        product.setUpdated(LocalDateTime.now());
    }

    private void populateGeneratedFields(Product product){
        product.setUrl(product.getId() + "/desc/" + product.getUrl());
    }

    private void populateCustomFields(Product product, XlsProduct xlsProduct) throws ConfigurationException {
        Set<Category> categories = Arrays.stream(xlsProduct.getCategoriesString().split(",")).map(name -> {
            Category category = new Category();
            category.setName(name);
            return category;
        }).collect(Collectors.toSet());
        product.setCategories(categories);
    }
}
