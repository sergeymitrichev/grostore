package ru.ftob.grostore.model.image;

import ru.ftob.grostore.model.base.AbstractEntityImage;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_image")
public class CategoryImage extends AbstractEntityImage<Category> {

    public CategoryImage() {
    }

    public CategoryImage(
            @Size(max = 255) @NotNull(message = "Image URL must not be null") String url,
            @NotNull(message = "Category image must not be null") Category entity) {
        super(url, entity);
    }
}
