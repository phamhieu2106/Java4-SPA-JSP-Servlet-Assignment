package controllers;

import entities.NhaSanXuat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/nha-san-xuat/index", "/nha-san-xuat/create", "/nha-san-xuat/store", "/nha-san-xuat/edit", "/nha-san-xuat/update", "/nha-san-xuat/delete"}
)
public class NhaSanXuatServlet extends HttpServlet {
    private ArrayList<NhaSanXuat> nhaSanXuatList;

    public NhaSanXuatServlet() {
        this.nhaSanXuatList = new ArrayList<>();

        nhaSanXuatList.add(new NhaSanXuat(1, "1", "1"));
        nhaSanXuatList.add(new NhaSanXuat(2, "2", "2"));
        nhaSanXuatList.add(new NhaSanXuat(3, "3", "3"));
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
                .getRequestDispatcher("/views/nhasanxuat/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        NhaSanXuat sp = new NhaSanXuat(1, ma, ten);
        nhaSanXuatList.add(sp);
        response
                .sendRedirect("/Java4_Demo_war_exploded/nha-san-xuat/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("nhaSanXuatList", nhaSanXuatList);
        //return jsp file
        request
                .getRequestDispatcher("/views/nhasanxuat/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        NhaSanXuat nhasanxuat;
        for (int i = 0; i < nhaSanXuatList.size(); i++) {
            if (nhaSanXuatList.get(i).getId() == id) {
                nhasanxuat = nhaSanXuatList.get(i);
                request
                        .setAttribute("data", nhasanxuat);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/nhasanxuat/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        NhaSanXuat sp = new NhaSanXuat(id, ma, ten);

        for (int i = 0; i < nhaSanXuatList.size(); i++) {
            if (nhaSanXuatList.get(i).getId() == id) {
                nhaSanXuatList.set(i, sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/nha-san-xuat/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (NhaSanXuat sp : nhaSanXuatList
        ) {
            if (sp.getId() == id) {
                nhaSanXuatList.remove(sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/nha-san-xuat/index");
    }
}
