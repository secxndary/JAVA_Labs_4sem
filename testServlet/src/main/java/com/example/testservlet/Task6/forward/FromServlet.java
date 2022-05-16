package com.example.testservlet.Task6.forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "fromServletForward", value = "/from-servlet-forward")
public class FromServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String mesFrom = "Hello! I am from-servlet.";
        request.setAttribute("mesFrom", mesFrom);
        request.getRequestDispatcher("/to-servlet-forward").forward(request, response);
    }

}