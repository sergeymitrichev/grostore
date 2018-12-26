package ru.ftob.grostore.rest.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import ru.ftob.grostore.model.modification.ModificationStringValue;
import ru.ftob.grostore.rest.webmodel.GuiModificationStringValue;

public class DbModificationStringValueGuiConverter implements Converter<ModificationStringValue, GuiModificationStringValue> {
    @Override
    public GuiModificationStringValue convert(MappingContext<ModificationStringValue, GuiModificationStringValue> context) {
        GuiModificationStringValue result = new GuiModificationStringValue();
        result.setId(context.getSource().getId());
        result.setValue(context.getSource().getValue());
        result.setName(context.getSource().getModificationString().getName());
        return result;
    }
}
