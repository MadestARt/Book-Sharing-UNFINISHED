package dao;

import util.ConnectionManager;
import entity.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AuthorDao implements Dao<Integer, Author>{

    private static final AuthorDao INSTANCE = new AuthorDao();

    private AuthorDao() {

    }

    public static AuthorDao getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_ALL_SQL = """
            SELECT id,
            name,
            second_name,
            gender
            FROM author
            """;

    private static final String SELECT_BY_ID_SQL = SELECT_ALL_SQL + "\nWHERE id = ?";

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Author getById(Integer id) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        try (var connection = ConnectionManager.get();
        var getAllSql =  connection.prepareStatement(SELECT_ALL_SQL)) {
            connection.setSchema("info");
            List<Author> allAuthors = new ArrayList<>();
            var authors = getAllSql.executeQuery();
            while (authors.next()) {
                allAuthors.add(getAuthor(authors));
            }

            return allAuthors;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Optional<Author> getById(Integer id, Connection connection) {
        try (var selectStatement = connection.prepareStatement(SELECT_BY_ID_SQL)){
            selectStatement.setInt(1,id);
            var resultSet = selectStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = getAuthor(resultSet);

            }
            return Optional.ofNullable(author);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Author getAuthor(ResultSet resultSet) {
        try {
            return new Author(resultSet.getInt("id"),resultSet.getString("name"),
                    resultSet.getString("second_name"),
                    resultSet.getString("gender").charAt(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
