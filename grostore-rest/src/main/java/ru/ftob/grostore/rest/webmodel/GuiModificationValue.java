package ru.ftob.grostore.rest.webmodel;

//@JsonDeserialize(using = GuiModificationValueSerializer.class)
public class GuiModificationValue {
    private Long id;
    private String name;
    private String value;
    private String unit;

    public GuiModificationValue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
