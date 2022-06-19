package Commands;

import DB.DBConnection;
import Models.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class Register {
    public static void RegisterMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Users usercheck = null;
        if (login.isEmpty()  || password.isEmpty()) {
            req.setAttribute("result", "Поля не заполнены");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            try {
                DBConnection dataBaseHandler = new DBConnection();
                usercheck = dataBaseHandler.GetByUsername(login);
                if (usercheck!=null ) {
                    req.setAttribute("result", "Пользователь с таким логином уже существует");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
                else {
                    Users user = new Users(login, password, "user");
                    dataBaseHandler.AddUser(user);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcome.jsp");
            requestDispatcher.forward(req, resp);

        }
    }
}
