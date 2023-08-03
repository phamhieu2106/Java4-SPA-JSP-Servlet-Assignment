package controllers;

import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(
        {"/chi-tiet-san-pham/index", "/chi-tiet-san-pham/create", "/chi-tiet-san-pham/store", "/chi-tiet-san-pham/edit", "/chi-tiet-san-pham/update", "/chi-tiet-san-pham/delete"}
)
public class ChiTietSanPhamServlet extends HttpServlet {

    private ArrayList<ChiTietSanPham> chiTietSanPhamList;
    private ChiTietSanPhamRepository repository;
    private SanPhamRepository sanPhamRepository;
    private NhaSanXuatRepository nhaSanXuatRepository;
    private MauSacRepository mauSacRepository;
    private DongSanPhamRepository dongSanPhamRepository;

    public ChiTietSanPhamServlet() {
        this.chiTietSanPhamList = new ArrayList<>();
        this.repository = new ChiTietSanPhamRepository();
        this.sanPhamRepository = new SanPhamRepository();
        this.nhaSanXuatRepository = new NhaSanXuatRepository();
        this.mauSacRepository = new MauSacRepository();
        this.dongSanPhamRepository = new DongSanPhamRepository();
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
                .setAttribute("listSanPham", sanPhamRepository.getAll());
        request
                .setAttribute("listNhaSanXuat", nhaSanXuatRepository.getAll());
        request
                .setAttribute("listMauSac", mauSacRepository.getAll());
        request
                .setAttribute("listDongSanPham", dongSanPhamRepository.getAll());
        request
                .getRequestDispatcher("/views/chitietsanpham/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        UUID idSanPham = UUID.fromString(request.getParameter("sanPham"));
        UUID idNhaSanXuat = UUID.fromString(request.getParameter("nhaSanXuat"));
        UUID idMauSac = UUID.fromString(request.getParameter("mauSac"));
        UUID idDongSanPham = UUID.fromString(request.getParameter("dongSanPham"));

        int namBaoHanh = Integer.parseInt(request.getParameter("namBaohanh"));
        int soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
        double giaNhap = Double.parseDouble(request.getParameter("giaNhap"));
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        String moTa = request.getParameter("moTa");

        SanPham sanPham = sanPhamRepository.getById(idSanPham);
        NhaSanXuat nhaSanXuat = nhaSanXuatRepository.getById(idNhaSanXuat);
        MauSac mauSac = mauSacRepository.getById(idMauSac);
        DongSanPham dongSanPham = dongSanPhamRepository.getById(idDongSanPham);

        ChiTietSanPham cv = new ChiTietSanPham(null, sanPham, nhaSanXuat, mauSac, dongSanPham, namBaoHanh, moTa, soLuongTon, giaNhap, giaBan);
        repository.insert(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("chiTietSanPhamList", repository.getAll());
        //return jsp file
        request
                .getRequestDispatcher("/views/chitietsanpham/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        request
                .setAttribute("data", repository.getById(id));
        request
                .setAttribute("listSanPham", sanPhamRepository.getAll());
        request
                .setAttribute("listNhaSanXuat", nhaSanXuatRepository.getAll());
        request
                .setAttribute("listMauSac", mauSacRepository.getAll());
        request
                .setAttribute("listDongSanPham", dongSanPhamRepository.getAll());
        request
                .getRequestDispatcher("/views/chitietsanpham/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        UUID idSanPham = UUID.fromString(request.getParameter("sanPham"));
        UUID idNhaSanXuat = UUID.fromString(request.getParameter("nhaSanXuat"));
        UUID idMauSac = UUID.fromString(request.getParameter("mauSac"));
        UUID idDongSanPham = UUID.fromString(request.getParameter("dongSanPham"));

        int namBaoHanh = Integer.parseInt(request.getParameter("namBaohanh"));
        int soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
        double giaNhap = Double.parseDouble(request.getParameter("giaNhap"));
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        String moTa = request.getParameter("moTa");

        SanPham sanPham = sanPhamRepository.getById(idSanPham);
        NhaSanXuat nhaSanXuat = nhaSanXuatRepository.getById(idNhaSanXuat);
        MauSac mauSac = mauSacRepository.getById(idMauSac);
        DongSanPham dongSanPham = dongSanPhamRepository.getById(idDongSanPham);

        ChiTietSanPham cv = new ChiTietSanPham(id, sanPham, nhaSanXuat, mauSac, dongSanPham, namBaoHanh, moTa, soLuongTon, giaNhap, giaBan);
        repository.update(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        repository.delete(repository.getById(id));
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }
}
