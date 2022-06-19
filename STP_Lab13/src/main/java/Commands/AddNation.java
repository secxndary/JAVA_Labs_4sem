package Commands;

import DB.DBConnection;
import Models.Nation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNation {
    public static void AddNationMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        DBConnection dataBaseContext = new DBConnection();
        String name = req.getParameter("name");
        String number = req.getParameter("country");
        if (name.equals("") || number.equals("")) {
            req.setAttribute("error", "Заполните все поля");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller?command=page");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                Nation newnat = new Nation(name, number);
                DBConnection dataBaseHandler = new DBConnection();
                Nation newforcheack = new Nation();
                newforcheack = dataBaseHandler.GetByname(name);
                if (newforcheack != null) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller?command=page");
                    requestDispatcher.forward(req, resp);
                } else {
                    try {
                        dataBaseHandler.AddNations(newnat);


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller?command=page");
                    requestDispatcher.forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}