package com.example.testservlet.Task5;
import com.example.testservlet.Task3.classes.Database;
import com.example.testservlet.Task3.classes.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;


    public void init() throws ServletException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://DESKTOP-8HNL9IM;databaseName=STP_Lab9;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        String username = "sa";
        String password = "1111";
        Database database = new Database(driver, url, username, password);
        this.userDAO = new UserDAO(database);
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            userDAO.addUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>You successfully registered.</h2>");
        out.println("</body></html>");
        out.close();

//        request.getRequestDispatcher("/main.jsp").forward(request, response);

    }

}

