package controllers;

import entities.DongSanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/dong-san-pham/index", "/dong-san-pham/create", "/dong-san-pham/store", "/dong-san-pham/edit", "/dong-san-pham/update", "/dong-san-pham/delete"}
)
public class DongSanPhamServlet extends HttpServlet {

    private ArrayList<DongSanPham> dongSanPhamList;

    public DongSanPhamServlet() {
        this.dongSanPhamList = new ArrayList();

        dongSanPhamList.add(new DongSanPham(1, "1", "1"));
        dongSanPhamList.add(new DongSanPham(2, "2", "2"));
        dongSanPhamList.add(new DongSanPham(3, "3", "3"));
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
                .getRequestDispatcher("/views/dongsanpham/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        DongSanPham sp = new DongSanPham(1, ma, ten);
        dongSanPhamList.add(sp);
        response
                .sendRedirect("/Java4_Demo_war_exploded/dong-san-pham/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("dongSanPhamList", dongSanPhamList);
        //return jsp file
        request
                .getRequestDispatcher("/views/dongsanpham/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        DongSanPham sanPham;
        for (int i = 0; i < dongSanPhamList.size(); i++) {
            if (dongSanPhamList.get(i).getId() == id) {
                sanPham = dongSanPhamList.get(i);
                request
                        .setAttribute("data", sanPham);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/dongsanpham/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        DongSanPham sp = new DongSanPham(id, ma, ten);

        for (int i = 0; i < dongSanPhamList.size(); i++) {
            if (dongSanPhamList.get(i).getId() == id) {
                dongSanPhamList.set(i, sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/dong-san-pham/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (DongSanPham sp : dongSanPhamList
        ) {
            if (sp.getId() == id) {
                dongSanPhamList.remove(sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/dong-san-pham/index");
    }
}
