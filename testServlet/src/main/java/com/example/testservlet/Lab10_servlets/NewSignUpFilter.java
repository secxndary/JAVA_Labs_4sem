package com.example.testservlet.Lab10_servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/NewSignUpFilter")
public class NewSignUpFilter implements Filter
{
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;  // хранилище страниц
    private static Logger logger = Logger.getLogger(MainPageAdminServlet.class);


    public NewSignUpFilter()
    {
        BasicConfigurator.configure();
        // Создание хранилища страниц
        if (pages == null)
            pages = new ArrayList<String>();
    }

    public void destroy()
    {
        filterConfig = null;
    }

    public void init(FilterConfig fConfig) throws ServletException { filterConfig = fConfig; }



    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        logger.debug("Started sign up filter");
        // Если фильтр активной, то выполнить проверку
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            HttpServletRequest req = (HttpServletRequest)request;


            HttpSession session = ((HttpServletRequest) request).getSession();
            Object userSignedIn = session.getAttribute("userSignedIn");



            // Раскладываем адрес на составляющие
            String[] list = req.getRequestURI().split("/");
            // Извлекаем наименование страницы
            String page = null;
            if (list[list.length - 1].indexOf(".jsp") > 0) {
                page = list[list.length - 1];
            }
            // Если открывается главная страница, то выполняем проверку
            if ((page != null) && page.equalsIgnoreCase("mainPageAdmin_10.jsp")) {
                // Если была предварительно открыта одна из страниц
                // login.jsp, то передаем управление
                // следующему элементу цепочки фильтра
                if (pages.contains("signInForm_10.jsp") && userSignedIn != null) {
                    System.out.println(userSignedIn);
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    // Перенаправление на страницу error.jsp
                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/errorPage_10.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } else if (page != null) {
                // Добавляем страницу в список
                if (!pages.contains(page))
                    pages.add(page);
            }
        }
        filterChain.doFilter(request, response);
    }
}


