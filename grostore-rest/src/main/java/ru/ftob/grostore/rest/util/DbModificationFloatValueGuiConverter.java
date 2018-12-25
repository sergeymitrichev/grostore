package ru.ftob.grostore.rest.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import ru.ftob.grostore.model.modification.ModificationFloatValue;
import ru.ftob.grostore.rest.webmodel.GuiModificationValue;

public class DbModificationFloatValueGuiConverter implements Converter<ModificationFloatValue, GuiModificationValue> {
    @Override
    public GuiModificationValue convert(MappingContext<ModificationFloatValue, GuiModificationValue> context) {
        GuiModificationValue result = new GuiModificationValue();
        result.setId(context.getSource().getId());
        result.setValue(context.getSource().getValue().toString());
        result.setName(context.getSource().getModificationFloat().getName());
        result.setUnit(context.getSource().getModificationFloat().getUnit());
        return result;
    }
}
