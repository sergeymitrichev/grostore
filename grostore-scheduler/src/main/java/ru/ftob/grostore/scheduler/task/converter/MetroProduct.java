package ru.ftob.grostore.scheduler.task.converter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;
import ru.ftob.grostore.model.product.PriceType;

import java.util.ArrayList;
import java.util.List;

public class MetroProduct {

    private String name;
    private String sku;
    private String image;
    private List<MetroPrice> prices = new ArrayList<>();

    //TODO move deserialize logic to separate Jackson deserializer
    public MetroProduct(String html) {
        Document doc = Jsoup.parse(html);
        this.name = doc.select("span.title").get(0).html();
        this.sku = doc.select(".add2list").get(0).attr("data-article");

        // https://static.metro-cc.ru/data/public/image/cache/7/2016/12/02/8f270af5f576b381e58a8c8c3a9add00-150x150.jpg
        // https://static.metro-cc.ru/data/public/image/cache/7/2016/12/02/8f270af5f576b381e58a8c8c3a9add00-320x280.jpg
        //TODO resize images and persist all sizes
        this.image = doc.select("div.catalog-i_image>img").attr("src");

        int priceIn = Math.round(Float.parseFloat(doc.select(".add2list").get(0).attr("data-regular_price")));
        this.prices.add(new MetroPrice(PriceType.PRICE_TYPE_IN, priceIn));

        String countLevel = doc.select(".add2list").get(0).attr("data-count_lvl_1");
        if(!StringUtils.isEmpty(countLevel)) {
            int countLevel1 = Integer.parseInt(countLevel);
            if (countLevel1 > 1) {
                int priceLevel1 = priceIn - Math.round(Float.parseFloat(doc.select(".add2list").get(0).attr("data-discount_lvl_1")));
                this.prices.add(new MetroPrice(PriceType.PRICE_TYPE_WHOLESALE_IN, priceLevel1, countLevel1));
            }
        }
        countLevel = doc.select(".add2list").get(0).attr("data-count_lvl_2");
        if(!StringUtils.isEmpty(countLevel)) {
            int countLevel2 = Integer.parseInt(countLevel);
            if (countLevel2 > 1) {
                int priceLevel2 = priceIn - Math.round(Float.parseFloat(doc.select(".add2list").get(0).attr("data-discount_lvl_2")));
                this.prices.add(new MetroPrice(PriceType.PRICE_TYPE_WHOLESALE_IN, priceLevel2, countLevel2));
            }
        }

        //TODO add % to old price to correct calculating old price
        int priceOld = Math.round(Float.parseFloat(doc.select(".add2list").get(0).attr("data-old_price")));
        if (priceOld > 0 && priceOld < priceIn) {
            this.prices.add(new MetroPrice(PriceType.PRICE_TYPE_OLD_IN, priceOld));
        }

        //TODO parse category or get it from parse config
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MetroPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<MetroPrice> prices) {
        this.prices = prices;
    }


    @Override
    public String toString() {
        return "MetroProduct{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", image='" + image + '\'' +
                ", prices=" + prices +
                '}';
    }
}
