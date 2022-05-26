package dao;

import exceptions.SQLRequestException;
import util.ConnectionManager;
import entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class BookDao implements Dao<Integer,Book> {

    private static final BookDao TABLE_INSTANCE = new BookDao();
    private final AuthorDao authorTableInstance = AuthorDao.getInstance();



    private static final String GET_BY_ID_SQL = """
            SELECT id,
                   name,
                   year_of_publish,
                   page_count,
                   author_info_id 
            FROM book
            WHERE author_info_id = ?;
            """;

    private static final String DELETE_REQUEST_SQL = """
            DELETE FROM book
            WHERE id = ?;
            """;

    private static final String GET_REQUEST_SQL = """
            SELECT id,
                   name,
                   year_of_publish,
                   page_count
            FROM book
            """;


    private BookDao() {
    }



    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get();
        var deleteStatement =  connection.prepareStatement(DELETE_REQUEST_SQL)) {
            connection.setSchema("info");
            deleteStatement.setInt(1,id);
            return deleteStatement.executeUpdate() == 1;
        } catch (SQLException exception) {
            throw new SQLRequestException(exception);
        }


    }

    @Override
    public Book getById(Integer id) {
        try (var connection = ConnectionManager.get();
        var getStatement = connection.prepareStatement(GET_REQUEST_SQL + " WHERE id = ? ")) {
            connection.setSchema("info");
            getStatement.setInt(1,id);
            var resultSet = getStatement.executeQuery();
            resultSet.next();
            var authorId = resultSet.getInt("author_info_id");
            return new Book(id,resultSet.getString("name"),
                    resultSet.getTimestamp("year_of_publish").toLocalDateTime(),
                    resultSet.getInt("page_count"),
                    authorTableInstance.getById(authorId,connection).orElse(null));


        } catch (SQLException exception) {
            throw new SQLRequestException(exception);
        }
    }



    @Override
    public List<Book> getAll() {
        try (var connection = ConnectionManager.get();
        var sql = connection.prepareStatement(GET_REQUEST_SQL)) {
            connection.setSchema("info");
            List<Book> books = new ArrayList<>();
            var resultSet = sql.executeQuery();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException exception) {
            throw new SQLRequestException(exception);
        }
    }

    @Override
    public Book create(Book object) {
        return null;
    }

    public List<Book> getBooksByAuthorId(int id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(GET_BY_ID_SQL)) {
            connection.setSchema("info");
            List<Book> books = new ArrayList<>();
            statement.setInt(1,id);
            var booksResultSet = statement.executeQuery();

            while (booksResultSet.next()) {
                var book = buildBook(booksResultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException exception) {
            throw new SQLRequestException(exception);
        }
    }


    private Book buildBook(ResultSet resultSet) {
        try {
            var id = resultSet.getInt("author_info_id");
            return new Book(resultSet.getInt("id"),resultSet.getString("name"),
                    resultSet.getTimestamp("year_of_publish").toLocalDateTime(),
                    resultSet.getInt("page_count"),
                    authorTableInstance.getById(id,resultSet.getStatement().getConnection()).orElse(null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Book> collectAllBooks(ResultSet resultSet) {
        List<Book> books = new ArrayList<>();
        try {
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static BookDao getInstance() {
        return TABLE_INSTANCE;
    }


}
