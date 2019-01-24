package ru.ftob.grostore.rest.webmodel;

public class GuiNodeCategoryPojo {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    private String imageAlt;
    private String imageTitle;
    private GuiNodeCategoryPojo parent;
    private String hgu;

    public GuiNodeCategoryPojo() {
    }

    public GuiNodeCategoryPojo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GuiNodeCategoryPojo getParent() {
        return parent;
    }

    public void setParent(GuiNodeCategoryPojo parent) {
        this.parent = parent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getHgu() {
        return hgu;
    }

    public void setHgu(String hgu) {
        this.hgu = hgu;
    }
}
