package controllers;


import entities.GioHang;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.GioHangRepository;
import repositories.KhachHangRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.UUID;


@WebServlet(
        {"/khach-hang/index", "/khach-hang/create", "/khach-hang/store", "/khach-hang/edit", "/khach-hang/update", "/khach-hang/delete"}
)
public class KhachHangServlet extends HttpServlet {

    private KhachHangRepository repository;
    private ArrayList<KhachHang> khachHangList;
    private GioHangRepository gioHangRepository;

    public KhachHangServlet() {
        this.khachHangList = new ArrayList<>();
        repository = new KhachHangRepository();
        gioHangRepository = new GioHangRepository();
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


        KhachHang kh = new KhachHang(null, ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau);
        repository.insert(kh);
        GioHang gioHang = new GioHang(null, kh, null, kh.getMa(), null, null, kh.getTen(), kh.getDiaChi(), 0);
        gioHangRepository.add(gioHang);

        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");

    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set khach hang list
        request
                .setAttribute("khachHangList", repository.getAll());

        request
                .getRequestDispatcher("/views/khachhang/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        repository.delete(repository.getById(id));
        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        request
                .setAttribute("data", repository.getById(id));
        request
                .getRequestDispatcher("/views/khachhang/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
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

        repository.update(new KhachHang(id, ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau));

        response
                .sendRedirect("/Java4_Demo_war_exploded/khach-hang/index");
    }
}
