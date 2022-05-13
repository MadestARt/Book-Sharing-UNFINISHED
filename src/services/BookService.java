package services;

import dao.BookDao;
import dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public final class BookService {

    private static final BookService INSTANCE = new BookService();
    private final BookDao bookDao = BookDao.getInstance();

    private BookService() {

    }

    public List<BookDto> getBooksByAuthorId(int authorId) {


        var booksByAuthorId = bookDao.getBooksByAuthorId(authorId);

        List<BookDto> books = new ArrayList<>();
        booksByAuthorId.forEach(book -> books.add(BookDto.builder()
                        .pageCount(book.getPageCount())
                        .yearOfPublish(book.getYearOfPublish())
                        .name(book.getName())
                .build()));

        return books;
    }

    public static BookService getInstace() {
        return INSTANCE;
    }
}
