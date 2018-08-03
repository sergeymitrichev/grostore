package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_import_fields")
public class ProductImportField extends AbstractNamedEntity {

    @Column(name = "column_number")
    private Integer columnNumber;

    @ManyToOne
    @JoinColumn(name = "product_import_id")
    private ProductImport productImport;

    public ProductImportField() {
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public ProductImport getProductImport() {
        return productImport;
    }

    public void setProductImport(ProductImport productImport) {
        this.productImport = productImport;
    }
}
