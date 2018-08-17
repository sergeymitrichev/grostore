package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_import")
//@Table(name="product_imports", uniqueConstraints = {@UniqueConstraint(columnNames = "file, name")})
public class ProductImport extends AbstractNamedEntity {

    @Column(name = "file", nullable = false)
    private String file;

    @Transient
    private List<ProductImportFieldType> fields = new ArrayList<>();

    @Transient
    private List<List<String>> raw;

    @Transient
    private int rowLength;

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

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    @Override
    public String toString() {
        return "ProductImport{" +
                "file='" + file + '\'' +
                ", fields=" + fields +
                ", raw=" + raw +
                ", rowLength=" + rowLength +
                '}';
    }
}
