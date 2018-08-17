package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_imports_fields")
public class ProductImportField extends AbstractBaseEntity {

    @Column(name = "column_number")
    private Integer columnNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProductImportFieldType type;

    @Column(name = "identity")
    private boolean identity;

    @Column(name = "product_import_id")
    private Integer productImportId;

    public ProductImportField() {
    }

    public ProductImportField(ProductImportFieldType type) {
        this.type = type;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public ProductImportFieldType getType() {
        return type;
    }

    public void setType(ProductImportFieldType type) {
        this.type = type;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public Integer getProductImportId() {
        return productImportId;
    }

    public void setProductImportId(Integer productImportId) {
        this.productImportId = productImportId;
    }
}
