package Commands;


import DB.DBConnection;
import Models.Nation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Page {
    public static void PageMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dataBaseContext = new DBConnection();
        ArrayList<Nation> nations = null;
        int countNations = 0;
        try {
            nations = dataBaseContext.GetAllNations();
            countNations = nations.size();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("nations",nations);
        req.setAttribute("user","user");
        req.getServletContext().getRequestDispatcher("/page.jsp").forward(req,resp);
    }
}