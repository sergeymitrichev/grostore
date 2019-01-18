package ru.ftob.grostore.rest.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.ftob.grostore.model.util.PasswordGenerator;
import ru.ftob.grostore.rest.base.AbstractValidator;
import ru.ftob.grostore.rest.webmodel.GuiAccount;
import ru.ftob.grostore.service.account.AccountService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import static ru.ftob.grostore.rest.config.RestConstants.*;

@Component("accountValidator")
public class AccountValidator extends AbstractValidator<GuiAccount> {

    private final PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .build();

    @Autowired
    private final AccountService service;

    public AccountValidator(AccountService service) {
        this.service = service;
    }

    @Override
    protected void processValidate(@NonNull GuiAccount guiTarget, @NonNull final Errors errors) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validateCreate(guiTarget, errors);
    }

    public void validateCreate(@NonNull GuiAccount guiTarget, @NonNull final Errors errors) {
        if (StringUtils.isEmpty(guiTarget.getEmail()) && StringUtils.isEmpty(guiTarget.getPhone())) {
            errors.rejectValue(VALIDATION_FIELD_EMAIL, VALIDATION_ERROR_NOT_EMPTY);
            errors.rejectValue(VALIDATION_FIELD_PHONE, VALIDATION_ERROR_NOT_EMPTY);
        } else {
            try {
                service.getByEmail(guiTarget.getEmail());
                errors.rejectValue(VALIDATION_FIELD_EMAIL, VALIDATION_ERROR_ALREADY_USE);
            } catch (NotFoundException ignored) {
            }
            try {
                service.getByPhone(guiTarget.getPhone());
                errors.rejectValue(VALIDATION_FIELD_PHONE, VALIDATION_ERROR_ALREADY_USE);
            } catch (NotFoundException ignored) {
            }
        }

        if (StringUtils.isEmpty(guiTarget.getName())) {
            guiTarget.setName(VALIDATION_FIELD_EMPTY);
        }
        if (StringUtils.isEmpty(guiTarget.getPassword())) {
            guiTarget.setPassword(passwordGenerator.generate(8));
        }

    }
}
