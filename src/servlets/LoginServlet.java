package servlets;

import dto.LoginUserDto;
import entity.User;
import exceptions.IncorrectLoginDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private final LoginService loginService = LoginService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginUserDto = LoginUserDto.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
            var loggedUser = loginService.loginUser(loginUserDto);
            req.getSession().setAttribute("user",loggedUser);
            resp.sendRedirect("/authors");
        } catch (IncorrectLoginDataException e) {
            req.setAttribute("error","Неправильная почта или пароль");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }

    }
}
