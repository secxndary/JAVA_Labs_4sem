package MyTag;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "FirstServlet", value = "/FirstServlet")
public class FirstServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Бойцовский клуб");
        strings.add("Криминальное чтиво");
        strings.add("Американский психопат");
        strings.add("Драйв");
        strings.add("Таксист");
        request.setAttribute("items", strings);
        getServletContext().getRequestDispatcher("/first.jsp").forward(request, response);
    }

}
