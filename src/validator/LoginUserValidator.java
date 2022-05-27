package validator;

import dto.LoginUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginUserValidator implements Validator<LoginUserDto> {
    private static final LoginUserValidator INSTANCE = new LoginUserValidator();



    @Override
    public ValidationResult validate(LoginUserDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getEmail() == null || object.getEmail().isEmpty() || object.getEmail().isBlank()) {
            validationResult.addError("Почта не введена");

        }
        if (object.getPassword() == null || object.getPassword().isEmpty() || object.getPassword().isBlank()) {
            validationResult.addError("Пароль не введён");
        }
        return validationResult;
    }

    public static LoginUserValidator getInstance() {
        return INSTANCE;
    }
}
