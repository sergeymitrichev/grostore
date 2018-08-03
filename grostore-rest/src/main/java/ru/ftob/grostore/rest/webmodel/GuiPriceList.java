package ru.ftob.grostore.rest.webmodel;

import java.util.ArrayList;
import java.util.List;

public class GuiPriceList {

    private String fileName;

    private List<List<String>> products = new ArrayList<>();

    private String priceContent;

    public GuiPriceList() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPriceContent() {
        return priceContent;
    }

    public void setPriceContent(String priceContent) {
        this.priceContent = priceContent;
    }

    public List<List<String>> getProducts() {
        return products;
    }

    public void setProducts(List<List<String>> products) {
        this.products = products;
    }

    public void setTableRow(List<String> row) {
        products.add(row);
    }
}
