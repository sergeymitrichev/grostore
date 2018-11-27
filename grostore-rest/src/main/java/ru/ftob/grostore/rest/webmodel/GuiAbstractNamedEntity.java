package ru.ftob.grostore.rest.webmodel;

public abstract class GuiAbstractNamedEntity extends GuiAbstractBaseEntity {

    private String name;

    public GuiAbstractNamedEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
