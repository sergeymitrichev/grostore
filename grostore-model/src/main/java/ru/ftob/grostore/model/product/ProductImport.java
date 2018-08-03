package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product_imports")
//@Table(name="product_imports", uniqueConstraints = {@UniqueConstraint(columnNames = "file, name")})
public class ProductImport extends AbstractNamedEntity {

    @Column(name = "file", nullable = false)
    private String file;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductImportType type;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_import_fields", joinColumns = @JoinColumn(name = "product_import_id"))
    @Column(name = "field")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductImportField> fields;

    @Enumerated(EnumType.STRING)
    @Column(name = "identityField")
    private ProductImportField identityField;

    public ProductImport() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ProductImportType getType() {
        return type;
    }

    public void setType(ProductImportType type) {
        this.type = type;
    }

    public List<ProductImportField> getFields() {
        return fields;
    }

    public void setFields(List<ProductImportField> fields) {
        this.fields = fields;
    }

    public ProductImportField getIdentityField() {
        return identityField;
    }

    public void setIdentityField(ProductImportField identityField) {
        this.identityField = identityField;
    }

    @Override
    public String toString() {
        return "ProductImport{" +
                "file='" + file + '\'' +
                ", type=" + type +
                ", fields=" + fields +
                ", identityField=" + identityField +
                '}';
    }
}
