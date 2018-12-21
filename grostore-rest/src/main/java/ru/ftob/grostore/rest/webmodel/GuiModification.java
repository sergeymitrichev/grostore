package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiModification {
    private String name;
    private List<GuiModificationValue> values;


    public GuiModification() {
    }

    public List<GuiModificationValue> getValues() {
        return values;
    }

    public void setValues(List<GuiModificationValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
