package ru.ftob.grostore.rest.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import ru.ftob.grostore.model.modification.ModificationFloatValue;
import ru.ftob.grostore.model.modification.ModificationStringValue;
import ru.ftob.grostore.rest.webmodel.GuiModificationValue;

public class DbModificationStringValueGuiConverter implements Converter<ModificationStringValue, GuiModificationValue> {
    @Override
    public GuiModificationValue convert(MappingContext<ModificationStringValue, GuiModificationValue> context) {
        GuiModificationValue result = new GuiModificationValue();
        result.setId(context.getSource().getId());
        result.setValue(context.getSource().getValue());
        result.setName(context.getSource().getModificationString().getName());
        return result;
    }
}
