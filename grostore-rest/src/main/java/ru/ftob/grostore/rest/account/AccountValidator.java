package ru.ftob.grostore.rest.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import ru.ftob.grostore.model.account.Role;
import ru.ftob.grostore.rest.util.PasswordGenerator;
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

    private final BCryptPasswordEncoder encoder;

    private final AccountService service;

    @Autowired
    public AccountValidator(BCryptPasswordEncoder encoder, AccountService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @Override
    protected void processValidate(@NonNull GuiAccount guiTarget, @NonNull final Errors errors) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validateCreate(guiTarget, errors);
    }

    public void validateCreate(@NonNull GuiAccount guiTarget, @NonNull final Errors errors) {

        populate(guiTarget);

        if (StringUtils.isEmpty(guiTarget.getEmail()) && StringUtils.isEmpty(guiTarget.getPhone())) {
            errors.rejectValue(VALIDATION_FIELD_EMAIL, VALIDATION_ERROR_NOT_EMPTY);
            errors.rejectValue(VALIDATION_FIELD_PHONE, VALIDATION_ERROR_NOT_EMPTY);
        }
        if (!StringUtils.isEmpty(guiTarget.getEmail())) {
            try {
                service.getByEmail(guiTarget.getEmail());
                errors.rejectValue(VALIDATION_FIELD_EMAIL, VALIDATION_ERROR_ALREADY_USE);
            } catch (NotFoundException ignored) {
            }
        }
        if (!StringUtils.isEmpty(guiTarget.getPhone())) {
            try {
                service.getByPhone(guiTarget.getPhone());
                errors.rejectValue(VALIDATION_FIELD_PHONE, VALIDATION_ERROR_ALREADY_USE);
            } catch (NotFoundException ignored) {
            }
        }
    }

    private void populate(final GuiAccount guiAccount) {
        if (StringUtils.isEmpty(guiAccount.getName())) {
            guiAccount.setName(VALIDATION_FIELD_EMPTY);
        }
        if (StringUtils.isEmpty(guiAccount.getPassword())) {
            String password = passwordGenerator.generate(8);
            System.out.println(password);
            System.out.println(encoder.encode(password));
            //TODO start email notify
            guiAccount.setPassword(password);
        }
        if (guiAccount.getRoles() == null || guiAccount.getRoles().size() == 0) {
            guiAccount.addRole(Role.ROLE_USER);
        }
    }
}
