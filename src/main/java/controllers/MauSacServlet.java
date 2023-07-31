package controllers;


import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.MauSacRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(
        {"/mau-sac/index", "/mau-sac/create", "/mau-sac/store", "/mau-sac/edit", "/mau-sac/update", "/mau-sac/delete"}
)
public class MauSacServlet extends HttpServlet {
    private ArrayList<MauSac> mauSacList;
    private MauSacRepository repository;

    public MauSacServlet() {
        this.mauSacList = new ArrayList<>();
        repository = new MauSacRepository();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String uri = request.getRequestURI();

        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.remove(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String uri = request.getRequestURI();

        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .getRequestDispatcher("/views/mausac/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        repository.insert(new MauSac(null, ma, ten));

        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("mauSacList", repository.getAll());
        request
                .getRequestDispatcher("/views/mausac/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));

        repository.delete(repository.getById(id));

        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        request
                .setAttribute("data", repository.getById(id));
        request
                .getRequestDispatcher("/views/mausac/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        repository.update(new MauSac(id, ten, ma));
        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }
}
