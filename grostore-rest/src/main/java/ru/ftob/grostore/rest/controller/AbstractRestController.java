package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;

public class AbstractRestController {

    private final ModelMapper mapper = new ModelMapper();

    public ModelMapper getMapper() {
        return mapper;
    }
}
