package controllers;

import entities.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/nhan-vien/index", "/nhan-vien/create", "/nhan-vien/store", "/nhan-vien/edit", "/nhan-vien/update"
                , "/nhan-vien/delete", "/nhan-vien/search", "/nhan-vien/loc"}
)
public class NhanVienServlet extends HttpServlet {
    private ArrayList<NhanVien> nhanVienList;

    public NhanVienServlet() {
        this.nhanVienList = new ArrayList<>();

        nhanVienList.add(new NhanVien(1, "1", "1", "1", "1", "nu", "2002-06-21", "Ha Noi", "123", "123", "Hoạt Động"));
        nhanVienList.add(new NhanVien(2, "2", "1", "1", "1", "nu", "2002-06-21", "Ha Noi", "123", "123", "Hoạt Động"));
        nhanVienList.add(new NhanVien(3, "3", "1", "1", "1", "nu", "2002-06-21", "Ha Noi", "123", "123", "Nghỉ Việc"));
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
        } else if (uri.contains("loc")) {
            this.loc(request, response);
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

    public void loc(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String locTrangThai = request.getParameter("locTrangThai");

        ArrayList<NhanVien> newList = new ArrayList<>();

        for (NhanVien nv : nhanVienList
        ) {
            if (nv.getTrangThai().equalsIgnoreCase(locTrangThai)) {
                newList.add(nv);
            }
        }
        if (newList.isEmpty()) {
            response
                    .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
        } else {
            request
                    .setAttribute("nhanVienList", newList);
            request
                    .getRequestDispatcher("/views/nhanvien/index.jsp")
                    .forward(request, response);
        }

    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String maSearch = request.getParameter("maSearch");
        ArrayList<NhanVien> newList = new ArrayList<>();
        for (NhanVien ch : nhanVienList
        ) {
            if (ch.getMa().equalsIgnoreCase(maSearch)) {
                newList.add(ch);
            }
        }
        if (newList.isEmpty()) {
            response
                    .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
        } else {
            request
                    .setAttribute("nhanVienList", newList);

            request
                    .getRequestDispatcher("/views/nhanvien/index.jsp")
                    .forward(request, response);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .getRequestDispatcher("/views/nhanvien/create.jsp")
                .forward(request, response);

    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");

        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");

        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        String trangThai = request.getParameter("trangThai");

        NhanVien nv = new NhanVien(1, ma, ten, tenDem, ho, gioiTinh, ngaySinh, diaChi, sdt, matKhau, trangThai);
        nhanVienList.add(nv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("nhanVienList", nhanVienList);
        request
                .getRequestDispatcher("/views/nhanvien/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        for (NhanVien nv : nhanVienList
        ) {
            if (nv.getId() == id) {
                nhanVienList.remove(nv);
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien newNhanVien;
        for (NhanVien nv : nhanVienList
        ) {
            if (nv.getId() == id) {
                newNhanVien = nv;
                request
                        .setAttribute("data", newNhanVien);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/nhanvien/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");
        String trangThai = request.getParameter("trangThai");

        for (int i = 0; i < nhanVienList.size(); i++) {
            if (nhanVienList.get(i).getId() == id) {
                nhanVienList.set(i, new NhanVien(id, ma, ten, tenDem, ho, gioiTinh, ngaySinh, diaChi, sdt, matKhau, trangThai));
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }
}
