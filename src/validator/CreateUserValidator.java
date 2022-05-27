package validator;

import dto.CreateUserDto;
import entity.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.DateFormatter;
import util.EmailChecker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult validate(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getName() == null || object.getName().isEmpty() || object.getName().isBlank()) {
            validationResult.addError("Вы не ввели имя :( Ну как же так");
        }
        if (!EmailChecker.isCorrect(object.getEmail())) {
            validationResult.addError("Вы неправильно ввели электронную почту :( не надо так");
        }
        if (object.getPassword() == null || object.getPassword().length() < 3) {
            validationResult.addError("Вы ввели слишком маленький пароль :( Придумай что-то получше");
        }
        if (DateFormatter.getDateFrom(object.getBirthday()).isEmpty()) {
            validationResult.addError("Вы ввели некорректную дату рождения :(");
        }
        if (object.getGender() == null) {
            validationResult.addError("Вы не выбрали пол");
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
