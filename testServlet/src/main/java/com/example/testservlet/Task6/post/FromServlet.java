package com.example.testservlet.Task6.post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "fromServletPost", value = "/from-servlet-post")
public class FromServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // set response headers
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("        <head>\r\n")
                .append("            <title>Form input</title>\r\n")
                .append("        </head>\r\n")
                .append("        <body>\r\n")
                .append("            <form action=\"to-servlet-post\" method=\"POST\">\r\n")
                .append("                Enter your name: \r\n")
                .append("                <input type=\"text\" name=\"user\" />\r\n")
                .append("                <input type=\"submit\" value=\"Submit\" />\r\n")
                .append("            </form>\r\n")
                .append("        </body>\r\n")
                .append("</html>\r\n");
    }

}