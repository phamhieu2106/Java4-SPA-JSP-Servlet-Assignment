package controllers;


import entities.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/mau-sac/index", "/mau-sac/create", "/mau-sac/store", "/mau-sac/edit", "/mau-sac/update", "/mau-sac/delete"}
)
public class MauSacServlet extends HttpServlet {
    private ArrayList<MauSac> mauSacList;

    public MauSacServlet() {
        this.mauSacList = new ArrayList<>();
        mauSacList.add(new MauSac(5, "5", "5"));
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
        mauSacList.add(new MauSac(1, ma, ten));

        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("mauSacList", mauSacList);
        request
                .getRequestDispatcher("/views/mausac/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        for (MauSac ms : mauSacList
        ) {
            if (ms.getId() == id) {
                mauSacList.remove(ms);
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        MauSac newMauSac;
        for (MauSac mauSac : mauSacList
        ) {
            if (mauSac.getId() == id) {
                newMauSac = mauSac;
                request
                        .setAttribute("data", newMauSac);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/mausac/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        for (int i = 0; i < mauSacList.size(); i++) {
            if (mauSacList.get(i).getId() == id) {
                mauSacList.set(i, new MauSac(id, ma, ten));
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/mau-sac/index");
    }
}
