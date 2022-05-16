package com.example.testservlet.Task6.forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "toServletForward", value = "/to-servlet-forward")
public class ToServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var mesTo = request.getAttribute("mesFrom");

        // ================  TASK 6  ================
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h2>" + mes.toString() + "</h2>");
//        out.println("</body></html>");
//        out.close();



        // ================  TASK 6b  ================
        request.setAttribute("mesTo", mesTo);
        request.getRequestDispatcher("/task6forward.jsp").forward(request, response);
    }
}