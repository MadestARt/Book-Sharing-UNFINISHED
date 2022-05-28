package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthorService;
import util.HelloMessageManager;
import validator.CreateUserValidator;

import java.io.IOException;

@WebServlet(value = "/authors",name = "AuthorsServlet")
public class AuthorsServlet extends HttpServlet {

    private AuthorService authorService = AuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var allAuthors = authorService.getAllAuthors();
        var helloMessage = HelloMessageManager.buildHelloMessage();

        req.setAttribute("helloMessage",helloMessage);
        req.setAttribute("authors",allAuthors);

        req.getRequestDispatcher("/WEB-INF/jsp/authors.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var header = req.getHeader("Content-Type");
    }
}
