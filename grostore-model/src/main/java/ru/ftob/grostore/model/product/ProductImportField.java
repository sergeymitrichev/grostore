package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_import_fields")
public class ProductImportField extends AbstractNamedEntity {

    @Column(name = "column")
    private Integer column;

    @ManyToOne
    @JoinColumn(name = "product_import_id")
    private ProductImport productImport;

    public ProductImportField() {
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public ProductImport getProductImport() {
        return productImport;
    }

    public void setProductImport(ProductImport productImport) {
        this.productImport = productImport;
    }
}
