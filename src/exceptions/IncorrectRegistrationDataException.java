package exceptions;

import validator.ValidationResult;

import java.util.List;

public class IncorrectRegistrationDataException extends RuntimeException {
    private final List<String> incorrectDataInfo;

    public IncorrectRegistrationDataException(ValidationResult validationResult) {
        incorrectDataInfo = validationResult.getValidationErrors();
    }

    public List<String> getIncorrectDataInfo() {
        return incorrectDataInfo;
    }
}
