package servlets;

import dto.AuthorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthorService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/authors")
public class AuthorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var authorService = AuthorService.getInstance();
        var allAuthors = authorService.getAllAuthors();


        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список авторов:</h1>");
            writer.write("<ul>");
            allAuthors.forEach(authorDto -> writer.write(("<li>" + "\n<a href=\"/books?authorId=%d\">" + authorDto.toString() + "\n</li>").formatted(authorDto.getId())));
            writer.write("</ul>");
        }

    }
}
