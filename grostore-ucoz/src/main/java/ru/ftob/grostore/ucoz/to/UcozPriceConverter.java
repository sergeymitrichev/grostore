package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.util.Map;

public class UcozPriceConverter implements Converter<Map<String, String>, Integer> {

    @Override
    public Integer convert(Map<String, String> value) {
        String priceRaw = value.get("price_raw");
        Double price = Math.ceil(Double.parseDouble(priceRaw));
        return price.intValue();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructMapLikeType(Map.class, String.class, String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(new TypeReference<Integer>() {
        });
    }
}
