package ru.ftob.grostore.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ftob.grostore.model.product.Product;

@Component("productValidator")
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if(StringUtils.isEmpty(product.getSku())) {
            errors.reject("Product SKU must not be null");
        }
        if(StringUtils.isEmpty(product.getName())) {
            errors.reject("Product SKU must not be null");
        }
        if(product.getCategories().size() == 0) {
            errors.reject("Product categories must not be empty");
        }
    }
}
