package controllers;

import entities.SanPham;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.SanPhamRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(
        {"/san-pham/index", "/san-pham/search", "/san-pham/create", "/san-pham/store", "/san-pham/edit", "/san-pham/update", "/san-pham/delete"}
)
public class SanPhamServlet extends HttpServlet {

    private ArrayList<SanPham> sanPhamList;
    private SanPhamRepository repository;

    public SanPhamServlet() {
        this.sanPhamList = new ArrayList();
        repository = new SanPhamRepository();

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
        SanPham sp = new SanPham(null, ma, ten);
        repository.insert(sp);
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("sanPhamList", repository.getAll());
        //return jsp file
        request
                .getRequestDispatcher("/views/sanpham/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        request
                .setAttribute("data", repository.getById(id));
        request
                .getRequestDispatcher("/views/sanpham/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        SanPham sp = new SanPham(id, ma, ten);
        repository.update(sp);
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        repository.delete(repository.getById(id));
        response
                .sendRedirect("/Java4_Demo_war_exploded/san-pham/index");
    }

}
