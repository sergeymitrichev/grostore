package ru.ftob.grostore.rest.webmodel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GuiNodeCategorySerializer extends JsonSerializer<GuiNodeCategory> {
    @Override
    public void serialize(GuiNodeCategory value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        GuiNodeCategoryPojo pojo = new GuiNodeCategoryPojo();
        pojo.setId(value.getId());
        pojo.setName(value.getName());
        pojo.setDescription(value.getDescription());
        GuiNodeCategoryPojo pojoParent;
        if(value.getParent() != null) {
            pojoParent = new GuiNodeCategoryPojo(value.getParent().getId(), value.getParent().getName());
        } else {
            pojoParent = new GuiNodeCategoryPojo(null, null);
        }
        pojo.setParent(pojoParent);

        gen.writeStartObject();
        gen.writeStringField("title", value.getName());
        gen.writeNumberField("id", value.getId());
        gen.writeObjectField("data", pojo);
        gen.writeObjectField("children", value.getChildren());
        gen.writeEndObject();
    }
}
