package mapper;

import dto.CreateUserDto;
import entity.Gender;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import services.ImageService;
import util.DateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDtoMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserDtoMapper INSTANCE = new CreateUserDtoMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .avatar(ImageService.getAvatarsDirectory() + object.getAvatar().getSubmittedFileName())
                .password(object.getPassword())
                .birthday(DateFormatter.getDateFrom(object.getBirthday()).get())
                .gender(Gender.valueOf(object.getGender()))
                .build();
    }

    public static CreateUserDtoMapper getInstance() {
        return INSTANCE;
    }
}
