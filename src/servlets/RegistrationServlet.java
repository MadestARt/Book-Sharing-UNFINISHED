package servlets;


import dto.CreateUserDto;
import exceptions.IncorrectRegistrationDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.UserService;

import java.io.IOException;

@MultipartConfig
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        var createUserDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .avatar(req.getPart("avatar"))
                .password(req.getParameter("password"))
                .birthday(req.getParameter("birthday"))
                .gender(req.getParameter("gender"))
                .build();

        try {
            userService.saveNewUser(createUserDto);
            resp.sendRedirect("/login");
        } catch (IncorrectRegistrationDataException e) {
            req.setAttribute("errors",e.getIncorrectDataInfo());
            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req,resp);
        }
    }
}
