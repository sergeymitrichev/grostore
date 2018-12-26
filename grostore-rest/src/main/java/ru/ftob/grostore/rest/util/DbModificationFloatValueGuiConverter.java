package ru.ftob.grostore.rest.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import ru.ftob.grostore.model.modification.ModificationFloatValue;
import ru.ftob.grostore.rest.webmodel.GuiModificationFloatValue;

public class DbModificationFloatValueGuiConverter implements Converter<ModificationFloatValue, GuiModificationFloatValue> {
    @Override
    public GuiModificationFloatValue convert(MappingContext<ModificationFloatValue, GuiModificationFloatValue> context) {
        GuiModificationFloatValue result = new GuiModificationFloatValue();
        result.setId(context.getSource().getId());
        result.setValue(context.getSource().getValue());
        result.setName(context.getSource().getModificationFloat().getName());
        result.setUnit(context.getSource().getModificationFloat().getUnit());
        return result;
    }
}
