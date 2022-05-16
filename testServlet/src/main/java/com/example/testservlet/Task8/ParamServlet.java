package com.example.testservlet.Task8;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ParamServlet")
public class ParamServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        String message = getServletConfig().getInitParameter("message");
        System.out.println(message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = getServletConfig().getInitParameter("message");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>" + message + "</h2>");
        } finally {
            writer.close();
        }

//        request.setAttribute("parameter", message);
//        request.getRequestDispatcher("/task8.jsp").include(request, response);

    }

}
