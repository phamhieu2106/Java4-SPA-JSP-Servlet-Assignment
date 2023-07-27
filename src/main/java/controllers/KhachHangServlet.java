package controllers;


import entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;


@WebServlet(
        {"/khach-hang/index", "/khach-hang/create", "/khach-hang/store", "/khach-hang/edit", "/khach-hang/update", "/khach-hang/delete"}
)
public class KhachHangServlet extends HttpServlet {

    private ArrayList<KhachHang> khachHangList;

    public KhachHangServlet() {
        this.khachHangList = new ArrayList<>();
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
                .getRequestDispatcher("/views/khachhang/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");

        String ngaySinh = request.getParameter("ngaySinh");

        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");

        khachHangList.add(new KhachHang(1, ten, ma, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau));
        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");

    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set khach hang list
        request
                .setAttribute("khachHangList", khachHangList);

        request
                .getRequestDispatcher("/views/khachhang/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        for (KhachHang kh : khachHangList
        ) {
            if (kh.getId() == id) {
                khachHangList.remove(kh);
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        KhachHang newKhachHang;
        for (KhachHang kh : khachHangList
        ) {
            if (kh.getId() == id) {
                newKhachHang = kh;
                request
                        .setAttribute("data", newKhachHang);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/khachhang/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");


        for (int i = 0; i < khachHangList.size(); i++) {
            if (khachHangList.get(i).getId() == id) {
                khachHangList.set(i, new KhachHang(id, ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau));
                break;
            }
        }

        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");
    }
}
