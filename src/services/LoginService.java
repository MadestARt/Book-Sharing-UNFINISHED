package services;

import dao.UserDao;
import dto.LoginUserDto;
import entity.User;
import exceptions.IncorrectLoginDataException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validator.LoginUserValidator;
import validator.ValidationResult;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginService {

    private static final LoginService INSTANCE = new LoginService();

    private final UserDao userDao = UserDao.getInstance();
    private final LoginUserValidator loginUserValidator = LoginUserValidator.getInstance();

    public User loginUser(LoginUserDto loginUserDto) {
        var validationResult = loginUserValidator.validate(loginUserDto);
        if (!validationResult.isValid()) {
            throw new IncorrectLoginDataException();
        }
        var maybeUser = userDao.getByMailAndPassword(loginUserDto.getEmail(),loginUserDto.getPassword());
        return maybeUser.orElseThrow(() ->  {
            throw new IncorrectLoginDataException();
        });
    }



    public static LoginService getInstance() {
        return INSTANCE;
    }
}
