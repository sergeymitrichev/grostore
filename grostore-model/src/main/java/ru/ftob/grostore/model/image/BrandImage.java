package ru.ftob.grostore.model.image;

import ru.ftob.grostore.model.base.AbstractEntityImage;
import ru.ftob.grostore.model.productlist.Brand;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brand_image")
public class BrandImage extends AbstractEntityImage<Brand> {

    public BrandImage() {
    }

    public BrandImage(
            @Size(max = 255) @NotNull(message = "Image URL must not be null") String url,
            @NotNull(message = "Brand image must not be null") Brand entity) {
        super(url, entity);
    }
}
