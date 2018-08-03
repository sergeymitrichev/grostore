package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.AbstractNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_imports")
//@Table(name="product_imports", uniqueConstraints = {@UniqueConstraint(columnNames = "file, name")})
public class ProductImport extends AbstractNamedEntity {

    @Column(name = "file", nullable = false)
    private String file;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductImportType type;

    @OneToMany(mappedBy = "product_imports_id", fetch = FetchType.EAGER)
    private List<ProductImportField> fields = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "identityField")
    private ProductImportField identityField;

    @Transient
    private List<List<String>> raw;

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

    public void addField(ProductImportField field) {
        fields.add(field);
    }

    public ProductImportField getIdentityField() {
        return identityField;
    }

    public void setIdentityField(ProductImportField identityField) {
        this.identityField = identityField;
    }

    public List<List<String>> getRaw() {
        return raw;
    }

    public void setRaw(List<List<String>> raw) {
        this.raw = raw;
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
