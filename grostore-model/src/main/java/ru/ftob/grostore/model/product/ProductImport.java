package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractNamedEntity;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_import", uniqueConstraints = {@UniqueConstraint(columnNames = {"file", "forename"}, name = "product_import_unique_file_name_idx")})
public class ProductImport extends AbstractNamedEntity {

    @Column(name = "file", nullable = false)
    private String file;

    @Enumerated
    @ElementCollection(targetClass = ProductImportFieldType.class, fetch = FetchType.EAGER)
    private List<ProductImportFieldType> fields = new ArrayList<>();

    @Transient
    private List<List<String>> raw;

    @Transient
    private List<Product> uploadedProducts;

    @Transient
    private List<Category> uploadedCategories;

    public ProductImport() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<ProductImportFieldType> getFields() {
        return fields;
    }

    public void setFields(List<ProductImportFieldType> fields) {
        this.fields = fields;
    }

    public List<List<String>> getRaw() {
        return raw;
    }

    public void setRaw(List<List<String>> raw) {
        this.raw = raw;
    }

    public List<Product> getUploadedProducts() {
        return uploadedProducts;
    }

    public void setUploadedProducts(List<Product> uploadedProducts) {
        this.uploadedProducts = uploadedProducts;
    }

    public List<Category> getUploadedCategories() {
        return uploadedCategories;
    }

    public void setUploadedCategories(List<Category> uploadedCategories) {
        this.uploadedCategories = uploadedCategories;
    }

    @Override
    public String toString() {
        return "ProductImport{" +
                "file='" + file + '\'' +
                ", fields=" + fields +
                ", raw=" + raw +
                '}';
    }
}
