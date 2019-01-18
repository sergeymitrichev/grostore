package ru.ftob.grostore.rest.base;


import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractValidator<G> implements Validator {

    private Class<G> guiClass;

    public AbstractValidator() {
        this.guiClass = (Class<G>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return guiClass.toString().equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        G guiTarget = (G) target;
        processValidate(guiTarget, errors);
    }

    protected abstract void processValidate(@NonNull G guiTarget, @NonNull final Errors errors);

}
