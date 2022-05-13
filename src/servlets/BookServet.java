package servlets;

import dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.BookService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/books")
public class BookServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var authorId = Integer.parseInt(req.getParameter("authorId"));
        var bookService = BookService.getInstace();
        var booksByAuthorId = bookService.getBooksByAuthorId(authorId);

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Книги автора:</h1>");
            writer.write("<ul>");
            booksByAuthorId.forEach(bookDto -> writer.write(("<li> %s Дата выхода: %s Число страниц: %d </li>").formatted(bookDto.getName(),bookDto.getYearOfPublish(),bookDto.getPageCount())));
            writer.write("</ul>");
        }

    }


}
