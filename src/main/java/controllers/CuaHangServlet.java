package controllers;


import entities.CuaHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(
        {"/cua-hang/index", "/cua-hang/create", "/cua-hang/store", "/cua-hang/edit", "/cua-hang/update", "/cua-hang/delete", "/cua-hang/search"}
)
public class CuaHangServlet extends HttpServlet {

    private ArrayList<CuaHang> cuaHangList;

    public CuaHangServlet() {
        this.cuaHangList = new ArrayList<>();
        CuaHang cuaHang = new CuaHang(1, "1", "1", "1", "1", "1");
        CuaHang cuaHang1 = new CuaHang(2, "1", "1", "1", "1", "1");
        CuaHang cuaHang2 = new CuaHang(3, "1", "1", "1", "1", "1");
        this.cuaHangList.add(cuaHang);
        this.cuaHangList.add(cuaHang1);
        this.cuaHangList.add(cuaHang2);
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
        ArrayList<CuaHang> newList = new ArrayList<>();
        for (CuaHang ch : cuaHangList
        ) {
            if (ch.getMa().equalsIgnoreCase(maSearch)) {
                newList.add(ch);
            }
        }
        if (newList.isEmpty()) {
            response
                    .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
        } else {
            request
                    .setAttribute("cuaHangList", newList);

            request
                    .getRequestDispatcher("/views/cuahang/index.jsp")
                    .forward(request, response);
        }
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

        CuaHang cuaHang = new CuaHang(1, ma, ten, diaChi, thanhPho, quocGia);
        cuaHangList.add(cuaHang);

        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("cuaHangList", cuaHangList);
        request
                .getRequestDispatcher("/views/cuahang/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        for (CuaHang ch : cuaHangList
        ) {
            if (ch.getId() == id) {
                cuaHangList.remove(ch);
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        CuaHang newCuaHang;
        for (CuaHang ch : cuaHangList
        ) {
            if (ch.getId() == id) {
                newCuaHang = ch;
                request
                        .setAttribute("data", newCuaHang);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/cuahang/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");

        CuaHang cuaHang = new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia);

        for (int i = 0; i < cuaHangList.size(); i++) {
            if (cuaHangList.get(i).getId() == id) {
                cuaHangList.set(i, cuaHang);
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/cua-hang/index");
    }
}
