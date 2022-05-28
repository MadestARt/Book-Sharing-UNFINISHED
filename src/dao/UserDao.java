package dao;

import entity.Gender;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String CREATE_SQL = """
            INSERT INTO users(name,email,avatar,password,birthday,gender)
            VALUES (?,?,?,?,?,?); 
            """;

    private static final String GET_BY_MAIL_SQL = """
            SELECT *
            FROM users
            WHERE email = ? AND password = ?
            """;

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @SneakyThrows
    public Optional<User> getByMailAndPassword(String email,String password) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(GET_BY_MAIL_SQL)) {
            connection.setSchema("info");
            statement.setString(1,email);
            statement.setString(2,password);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = buildUser(resultSet);
                return Optional.of(user);
            }
            return  Optional.empty();
        }

    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return User.builder()
                .avatar(resultSet.getString("avatar"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .id(resultSet.getInt("id"))
                .build();
    }

    @Override
    @SneakyThrows
    public User create(User user) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            connection.setSchema("info");
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getAvatar());
            statement.setString(4,user.getPassword());
            statement.setDate(5, Date.valueOf(user.getBirthday()));
            statement.setString(6,user.getGender().name());
            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();

            user.setId(generatedKeys.getInt("id"));

        }
        return user;
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
