package services;

import dao.UserDao;
import dto.CreateUserDto;
import entity.User;
import exceptions.IncorrectRegistrationDataException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserDtoMapper;
import validator.CreateUserValidator;
import validator.ValidationResult;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserDtoMapper userDtoMapper = CreateUserDtoMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public int saveNewUser(CreateUserDto createUserDto) {

        var validationResult = createUserValidator.validate(createUserDto);
        if (validationResult.isValid()) {
            imageService.saveAvatar(createUserDto.getAvatar());
            var user = userDtoMapper.mapFrom(createUserDto);

            var savedUser = userDao.create(user);
            return savedUser.getId();
        } else {
            throw new IncorrectRegistrationDataException(validationResult);
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
