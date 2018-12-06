package ru.ftob.grostore.scheduler.task.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;
import ru.ftob.grostore.model.product.Price;
import ru.ftob.grostore.model.product.PriceType;
import ru.ftob.grostore.model.product.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class MetroPageDeserializer extends JsonDeserializer<MetroPage> {

    @Override
    public MetroPage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        MetroPage metroPage = new MetroPage();
        if (!checkFindValueSafe(node, "success", TRUE.toString())) {
            throw new IOException("Cannot deserialize page. No success field in result.");
        }
        JsonNode data = findValueSafe(node, "data");
        JsonNode items = findValueSafe(data, "items");
        List<Product> products = new ArrayList<>();
        items.forEach(i -> {
            products.add(convertItemToProduct(i));
        });

        metroPage.setProducts(products);

        return metroPage;
    }

    private Product convertItemToProduct(JsonNode item) {
        Product product = new Product();
        Document doc = Jsoup.parse(item.textValue());
        product.setName(doc.select("span.title").get(0).html());
        product.setSku(doc.select(".add2list").get(0).attr("data-article"));
        product.setImage(doc.select("div.catalog-i_image>img").attr("src"));
        product.setUnit(doc.select("div.box_size").get(0).html());
        product.setPrices(convertDocToPrices(doc));
        product.setUpdated(LocalDateTime.now());

        return product;
    }

    private List<Price> convertDocToPrices(Document doc) {
        List<Price> prices = new ArrayList<>();
        Element source = doc.select(".add2list").get(0);
        if (null == source) {
            return prices;
        }

        int priceIn = Math.round(Float.parseFloat(source.attr("data-regular_price")));
        prices.add(new Price(priceIn, PriceType.PRICE_TYPE_IN));

        String countLevel = source.attr("data-count_lvl_1");
        if (!StringUtils.isEmpty(countLevel)) {
            int countLevel1 = Math.round(Float.parseFloat(countLevel));
            if (countLevel1 > 1) {
                int priceLevel1 = priceIn - Math.round(Float.parseFloat(source.attr("data-discount_lvl_1")));
                prices.add(new Price(priceLevel1, PriceType.PRICE_TYPE_WHOLESALE, countLevel1));
            }
        }
        countLevel = source.attr("data-count_lvl_2");
        if (!StringUtils.isEmpty(countLevel)) {
            int countLevel2 = Math.round(Float.parseFloat(countLevel));
            if (countLevel2 > 1) {
                int priceLevel2 = priceIn - Math.round(Float.parseFloat(source.attr("data-discount_lvl_2")));
                prices.add(new Price(priceLevel2, PriceType.PRICE_TYPE_WHOLESALE, countLevel2));
            }
        }

        int priceOld = Math.round(Float.parseFloat(source.attr("data-old_price")));
        if (priceOld > 0 && priceOld < priceIn) {
            prices.add(new Price(priceOld, PriceType.PRICE_TYPE_OLD_IN));
        }

        return prices;
    }

    private JsonNode findValueSafe(JsonNode node, String value) throws IOException {
        JsonNode result = node.findValue(value);
        if (null == result) {
            throw new IOException("Cannot deserialize value " + value);
        }
        return result;
    }

    private boolean checkFindValueSafe(JsonNode node, String value, String expected) throws IOException {
        String actual = findValueSafe(node, value).toString();
        if (expected.equals(actual)) {
            return true;
        }
        return false;
    }
}
