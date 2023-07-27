package com.example.java4demo;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/login-form" , "/login"})
public class LoginServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        request
                .setAttribute("username",username);
        request
                .setAttribute("password",password);

        //return login.jsp
        request
                .getRequestDispatcher("/views/login.jsp")
                .forward(request,response);

    }

    public void destroy() {
    }
}