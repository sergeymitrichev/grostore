package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiModificationFloat {
    private String unit;
    private List<GuiModificationFloatValue> values;
    private String name;

    public GuiModificationFloat() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<GuiModificationFloatValue> getValues() {
        return values;
    }

    public void setValues(List<GuiModificationFloatValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
