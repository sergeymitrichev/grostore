package ru.ftob.grostore.model.image;

import ru.ftob.grostore.model.base.AbstractEntityImage;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_image")
public class ProductImage extends AbstractEntityImage<Product> {

    public ProductImage() {
    }

    public ProductImage(
            @Size(max = 255) @NotNull(message = "Image URL must not be null") String url,
            @NotNull(message = "Product image must not be null") Product entity) {
        super(url, entity);
    }
}
