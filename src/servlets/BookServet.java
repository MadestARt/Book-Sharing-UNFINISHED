package servlets;

import dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.BookService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@WebServlet(value = "/books",name = "BookServlet")
public class BookServet extends HttpServlet {

    private final BookService bookService = BookService.getInstace();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var authorId = Integer.parseInt(req.getParameter("authorId"));
        var booksByAuthorId = bookService.getBooksByAuthorId(authorId);

        req.setAttribute("booksByAuthor",booksByAuthorId);
        req.getRequestDispatcher("/WEB-INF/jsp/books.jsp").forward(req,resp);

    }


}
