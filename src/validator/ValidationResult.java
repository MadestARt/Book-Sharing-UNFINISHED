package validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private final List<String> validationErrors = new ArrayList<>();

    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    public void addError(String errorMessage) {
        validationErrors.add(errorMessage);
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
