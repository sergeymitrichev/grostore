package ru.ftob.grostore.service.xlsto;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class XlsProduct {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("ID")
    private Integer id;

    @ExcelCellName("NAME")
    private String name;

    @ExcelCellName("SKU")
    private String sku;

    @ExcelCellName("PRICE_IN")
    private Integer priceIn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Integer priceIn) {
        this.priceIn = priceIn;
    }

    @Override
    public String toString() {
        return "XlsProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", priceIn=" + priceIn +
                '}';
    }
}
