package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition","attachment; filename =\"benGurion.jpg\"");
        resp.setContentType("image/jpg");

        var benGurionImagePath = new File("C:\\Users\\Артём\\IdeaProjects\\JDBC\\resourses\\benGurion.jpg").toPath();
        var benGurionBytes = Files.readAllBytes(benGurionImagePath);

        try (var outputStream = resp.getOutputStream()) {
            outputStream.write(benGurionBytes);
        }


    }
}
