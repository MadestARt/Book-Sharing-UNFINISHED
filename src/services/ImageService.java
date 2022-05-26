package services;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public final class ImageService {

    private static ImageService INSTANCE = new ImageService();
    private static final String AVATARS_PATH = "C:/Users/Артём/Pictures/avatars";

    @SneakyThrows
    public String saveAvatar(Part avatar) {
        var fileName = avatar.getSubmittedFileName();
        var avatarFullPath = Path.of(AVATARS_PATH, fileName);

        try (var avatarInputStream = avatar.getInputStream()) {
            Files.createDirectories(avatarFullPath.getParent());
            Files.write(avatarFullPath,avatarInputStream.readAllBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        }
        return avatarFullPath.toString();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }

    public static String getAvatarsDirectory() {
        return "/avatars/";
    }
}
