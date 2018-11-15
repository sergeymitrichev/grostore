package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.util.Map;

public class UcozCategoryConverter implements Converter<Map<String, String>, UcozCategory> {
    @Override
    public UcozCategory convert(Map<String, String> value) {
        UcozCategory category = new UcozCategory();
        category.setId(value.get("id"));
        category.setName(value.get("name"));
        category.setUrl(value.get("url"));
        return category;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructMapLikeType(Map.class, String.class, String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(new TypeReference<UcozCategory>() {});
    }
}
