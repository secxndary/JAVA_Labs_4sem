package com.example.testservlet.Task6.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "toServletRedirect", value = "/to-servlet-redirect")
public class ToServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // ================  TASK 6c  ================
        var path = request.getContextPath() + "/task6redirect.jsp";
        response.sendRedirect(path);
    }
}