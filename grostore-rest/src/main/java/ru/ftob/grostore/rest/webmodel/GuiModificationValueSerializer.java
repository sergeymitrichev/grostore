package ru.ftob.grostore.rest.webmodel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.ftob.grostore.model.modification.ModificationFloatValue;

import java.io.IOException;

public class GuiModificationValueSerializer extends JsonSerializer<ModificationFloatValue> {
    @Override
    public void serialize(ModificationFloatValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getModificationFloat().toString());
        gen.writeStringField("unit", value.getModificationFloat().getUnit());
        gen.writeEndObject();
    }


//    @Override
//    public GuiModificationValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        JsonNode node = p.getCodec().readTree(p);
//
//        return null;
//    }
}
