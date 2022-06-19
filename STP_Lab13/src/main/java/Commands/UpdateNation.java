package Commands;

import DB.DBConnection;
import Models.Nation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class UpdateNation {
    public static void UpdateNationMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        if (name.equals("") || number.equals("")) {
            req.setAttribute("error", "Заполните все поля");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller?command=page");
            requestDispatcher.forward(req, resp);
        } else {
            DBConnection dataBaseHandler = new DBConnection();
            Nation Nation = null;
            try {
                Nation = dataBaseHandler.GetByname(name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (Nation == null)
            {
                req.setAttribute("error", " Такого народа не существует");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller?command=page");
                requestDispatcher.forward(req, resp);
            }
            else{
                try {
                    dataBaseHandler.Update(name, number);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        req.setAttribute("error", " Обновлено");
        req.getRequestDispatcher("controller?command=page").forward(req, resp);
    }
}

