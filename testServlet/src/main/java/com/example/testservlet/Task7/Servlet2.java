package com.example.testservlet.Task7;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet2", value = "/servlet2")
public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // получаем переданный атрибут из servlet1 (число 5)
        var num1 = (int)request.getAttribute("number1");
        // увеличиваем его на 1 (получится 6 ебать)
        num1++;
        // передаем его обратно в servlet1 через атрибуты, ведь после этой строчки управление вернется к servlet1
        request.setAttribute("number2", num1);
    }

}
