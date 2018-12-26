package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiModificationString {
    private String name;
    private List<GuiModificationStringValue> values;

    public GuiModificationString() {
    }

    public List<GuiModificationStringValue> getValues() {
        return values;
    }

    public void setValues(List<GuiModificationStringValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
