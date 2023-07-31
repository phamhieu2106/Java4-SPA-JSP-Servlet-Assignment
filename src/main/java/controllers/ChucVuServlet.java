package controllers;

import entities.ChucVu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.ChucVuRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(
        {"/chuc-vu/index", "/chuc-vu/create", "/chuc-vu/search", "/chuc-vu/store", "/chuc-vu/edit", "/chuc-vu/update", "/chuc-vu/delete"}
)
public class ChucVuServlet extends HttpServlet {
    private ArrayList<ChucVu> chucVuList;
    private ChucVuRepository repository;

    public ChucVuServlet() {
        this.chucVuList = new ArrayList();
        repository = new ChucVuRepository();
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

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .getRequestDispatcher("/views/chucvu/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        ChucVu cv = new ChucVu(null, ma, ten);
        repository.insert(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String maSearch = request.getParameter("maSearch");
        List<ChucVu> newChucVuList = new ArrayList<>();
        for (ChucVu cv : repository.getAll()
        ) {
            if (cv.getMa().equalsIgnoreCase(maSearch)) {
                newChucVuList.add(cv);
            }
        }
        request
                .setAttribute("chucVuList", newChucVuList);
        //return jsp file
        request
                .getRequestDispatcher("/views/chucvu/index.jsp")
                .forward(request, response);
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("chucVuList", repository.getAll());
        //return jsp file
        request
                .getRequestDispatcher("/views/chucvu/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChucVu cv = repository.getById(id);
        request
                .setAttribute("data", cv);
        request
                .getRequestDispatcher("/views/chucvu/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        ChucVu cv = new ChucVu(id, ma, ten);
        repository.update(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChucVu cv = repository.getById(id);
        repository.delete(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

}
