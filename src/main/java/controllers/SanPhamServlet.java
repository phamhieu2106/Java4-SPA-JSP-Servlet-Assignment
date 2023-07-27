package controllers;

import entities.SanPham;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/san-pham/index", "/san-pham/search", "/san-pham/create", "/san-pham/store", "/san-pham/edit", "/san-pham/update", "/san-pham/delete"}
)
public class SanPhamServlet extends HttpServlet {

    private ArrayList<SanPham> sanPhamList;

    public SanPhamServlet() {
        this.sanPhamList = new ArrayList();

        sanPhamList.add(new SanPham(1, "1", "1"));
        sanPhamList.add(new SanPham(2, "2", "2"));
        sanPhamList.add(new SanPham(3, "3", "3"));
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

        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }

    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String maSearch = request.getParameter("maSearch");
        ArrayList<SanPham> newList = new ArrayList<>();
        for (SanPham ch : sanPhamList
        ) {
            if (ch.getMa().equalsIgnoreCase(maSearch)) {
                newList.add(ch);
            }
        }
        if (newList.isEmpty()) {
            response
                    .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
        } else {
            request
                    .setAttribute("sanPhamList", newList);

            request
                    .getRequestDispatcher("/views/sanpham/index.jsp")
                    .forward(request, response);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .getRequestDispatcher("/views/sanpham/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        SanPham sp = new SanPham(1, ma, ten);
        sanPhamList.add(sp);
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("sanPhamList", sanPhamList);
        //return jsp file
        request
                .getRequestDispatcher("/views/sanpham/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        SanPham sanPham;
        for (int i = 0; i < sanPhamList.size(); i++) {
            if (sanPhamList.get(i).getId() == id) {
                sanPham = sanPhamList.get(i);
                request
                        .setAttribute("data", sanPham);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/sanpham/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        SanPham sp = new SanPham(id, ma, ten);

        for (int i = 0; i < sanPhamList.size(); i++) {
            if (sanPhamList.get(i).getId() == id) {
                sanPhamList.set(i, sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (SanPham sp : sanPhamList
        ) {
            if (sp.getId() == id) {
                sanPhamList.remove(sp);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

}
