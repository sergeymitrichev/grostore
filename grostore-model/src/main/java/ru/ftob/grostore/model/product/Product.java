package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.analytics.ProductAnalytic;
import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.image.ProductImage;
import ru.ftob.grostore.model.ingredient.Ingredient;
import ru.ftob.grostore.model.modification.ModificationFloatValue;
import ru.ftob.grostore.model.modification.ModificationStringValue;
import ru.ftob.grostore.model.productlist.Brand;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "sku", name = "product_unique_sku_idx")})
@NamedEntityGraph(name = "Product.detail",
        attributeNodes = {
                @NamedAttributeNode("categories"),
                @NamedAttributeNode("modificationFloatValues"),
                @NamedAttributeNode("modificationStringValues"),
                @NamedAttributeNode("ingredients"),
                @NamedAttributeNode("alsoBuy"),
                @NamedAttributeNode("recommended"),
                @NamedAttributeNode("brand"),
                @NamedAttributeNode("analytic")
        }
)
public class Product extends AbstractPublishedEntity<ProductImage> {

    @Column(name = "sku")
    @Size(min = 4, max = 56)
    @NotNull(message = "Product SKU must not be null")
    private String sku;

    @Column(name = "unit")
    @Size(max = 56)
    private String unit;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @NotNull(message = "Product category list must not be null")
    private Set<Category> categories = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "price", joinColumns = @JoinColumn(name = "product_id"))
    @NotNull(message = "Product prices must not be null")
    private List<Price> prices = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "product_modification_float_value",
                    joinColumns = {@JoinColumn(name = "product_id")},
                    inverseJoinColumns = {@JoinColumn(name = "modification_float_value_id")}
            )
    @OrderBy("modification_float_id")
    private Set<ModificationFloatValue> modificationFloatValues = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "product_modification_string_value",
                    joinColumns = {@JoinColumn(name = "product_id")},
                    inverseJoinColumns = {@JoinColumn(name = "modification_string_value_id")}
            )
    @OrderBy("modification_string_id")
    private Set<ModificationStringValue> modificationStringValues = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @OrderBy("forename")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProductAnalytic analytic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_also_buy",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    @OrderColumn(name = "weight")
    private Set<Product> alsoBuy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_recommended",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    @OrderColumn(name = "weight")
    private Set<Product> recommended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "barcode")
    private String barcode;

    public Product() {
    }

    public Product(@Size(min = 4, max = 56) @NotNull(message = "Product SKU must not be null") String sku) {
        this.sku = sku;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Set<ModificationFloatValue> getModificationFloatValues() {
        return modificationFloatValues;
    }

    public void setModificationFloatValues(Set<ModificationFloatValue> modificationFloatValues) {
        this.modificationFloatValues = modificationFloatValues;
    }

    public void addModificationFloatValue(ModificationFloatValue modificationFloatValue) {
        modificationFloatValues.add(modificationFloatValue);
    }

    public Set<ModificationStringValue> getModificationStringValues() {
        return modificationStringValues;
    }

    public void setModificationStringValues(Set<ModificationStringValue> modificationStringValues) {
        this.modificationStringValues = modificationStringValues;
    }

    public void addModificationStringValue(ModificationStringValue modificationStringValue) {
        this.modificationStringValues.add(modificationStringValue);
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ProductAnalytic getAnalytic() {
        return analytic;
    }

    public void setAnalytic(ProductAnalytic analytic) {
        this.analytic = analytic;
    }

    public Set<Product> getAlsoBuy() {
        return alsoBuy;
    }

    public void setAlsoBuy(Set<Product> alsoBuy) {
        this.alsoBuy = alsoBuy;
    }

    public Set<Product> getRecommended() {
        return recommended;
    }

    public void setRecommended(Set<Product> recommended) {
        this.recommended = recommended;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", unit='" + unit + '\'' +
                ", categories=" + categories +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        return sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < sku.length(); i++) {
            result += sku.charAt(i);
        }
        return result;
    }
}
