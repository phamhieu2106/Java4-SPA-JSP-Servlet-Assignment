package controllers;

import entities.ChucVu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/chuc-vu/index", "/chuc-vu/create", "/chuc-vu/search", "/chuc-vu/store", "/chuc-vu/edit", "/chuc-vu/update", "/chuc-vu/delete"}
)
public class ChucVuServlet extends HttpServlet {
    private ArrayList<ChucVu> chucVuList;

    public ChucVuServlet() {
        this.chucVuList = new ArrayList();

        chucVuList.add(new ChucVu(1, "1", "1"));
        chucVuList.add(new ChucVu(2, "2", "2"));
        chucVuList.add(new ChucVu(3, "3", "3"));
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
        ChucVu cv = new ChucVu(1, ma, ten);
        chucVuList.add(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String maSearch = request.getParameter("maSearch");
        ArrayList<ChucVu> newList = new ArrayList<>();
        for (ChucVu ch : chucVuList
        ) {
            if (ch.getMa().equalsIgnoreCase(maSearch)) {
                newList.add(ch);
            }
        }
        if (newList.isEmpty()) {
            response
                    .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
        } else {
            request
                    .setAttribute("chucVuList", newList);

            request
                    .getRequestDispatcher("/views/chucvu/index.jsp")
                    .forward(request, response);
        }
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("chucVuList", chucVuList);
        //return jsp file
        request
                .getRequestDispatcher("/views/chucvu/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ChucVu chucvu;
        for (int i = 0; i < chucVuList.size(); i++) {
            if (chucVuList.get(i).getId() == id) {
                chucvu = chucVuList.get(i);
                request
                        .setAttribute("data", chucvu);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/chucvu/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        ChucVu cv = new ChucVu(id, ma, ten);

        for (int i = 0; i < chucVuList.size(); i++) {
            if (chucVuList.get(i).getId() == id) {
                chucVuList.set(i, cv);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (ChucVu cv : chucVuList
        ) {
            if (cv.getId() == id) {
                chucVuList.remove(cv);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/chuc-vu/index");
    }

}
