package controllers;


import entities.CuaHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CuaHangRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet(
        {"/cua-hang/index", "/cua-hang/create", "/cua-hang/store", "/cua-hang/edit", "/cua-hang/update", "/cua-hang/delete", "/cua-hang/search"}
)
public class CuaHangServlet extends HttpServlet {

    private CuaHangRepository repository;

    public CuaHangServlet() {
        this.repository = new CuaHangRepository();
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
        } else if (uri.contains("search")) {
            this.search(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }

    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String maSearch = request.getParameter("maSearch");
        List<CuaHang> newCuaHangList = new ArrayList<>();
        for (CuaHang ch : repository.getAll()
        ) {
            if (ch.getMa().equalsIgnoreCase(maSearch)) {
                newCuaHangList.add(ch);
            }
        }
        request
                .setAttribute("cuaHangList", newCuaHangList);
        request
                .getRequestDispatcher("/views/cuahang/index.jsp")
                .forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .getRequestDispatcher("/views/cuahang/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");

        CuaHang cuaHang = new CuaHang(null, ma, ten, diaChi, thanhPho, quocGia);
        repository.insert(cuaHang);

        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request
                .setAttribute("cuaHangList", this.repository.getAll());
        request
                .getRequestDispatcher("/views/cuahang/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        CuaHang ch = repository.getById(id);
        repository.delete(ch);
        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        CuaHang ch = repository.getById(id);
        request
                .setAttribute("data", ch);
        request
                .getRequestDispatcher("/views/cuahang/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");

        CuaHang cuaHang = new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia);
        this.repository.update(cuaHang);


        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }
}
