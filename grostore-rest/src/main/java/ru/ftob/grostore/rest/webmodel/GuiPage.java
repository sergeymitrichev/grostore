package ru.ftob.grostore.rest.webmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuiPage<T> {

    @JsonProperty("rowsPerPage")
    private Integer size;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("totalItems")
    private Integer totalElements;

    @JsonProperty("content")
    private List<T> content;

    public GuiPage() {
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
