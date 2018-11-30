package ru.ftob.grostore.ucoz.snapshot;

import javax.persistence.*;

@Entity
@Table(name = "ucoz_categories_snapshot")
public class SnapshotCategory {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "forename")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "cat_level")
    private Integer level;

    @Column(name = "descr")
    private String description;

    @Column(name = "num_entries")
    private Integer productCount;

    @Column(name = "cat_img_url")
    private String imageUrl;

    public SnapshotCategory() {
    }

    public SnapshotCategory(Integer id, String name, String url, Integer parentId, Integer level, String description, Integer productCount, String imageUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parentId = parentId;
        this.level = level;
        this.description = description;
        this.productCount = productCount;
        this.imageUrl = imageUrl;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SnapshotCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", productCount=" + productCount +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
