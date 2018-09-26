package ru.ftob.grostore.rest.webmodel;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

public class GuiNodeConverter implements Converter<GuiCategory, GuiNodeCategory> {
    @Override
    public GuiNodeCategory convert(MappingContext<GuiCategory, GuiNodeCategory> context) {
        ModelMapper mm = new ModelMapper();
        GuiCategory source = context.getSource();
        GuiNodeCategory destination = mm.map(source, GuiNodeCategory.class);
        destination.setData(source);
        return destination;
    }
}
