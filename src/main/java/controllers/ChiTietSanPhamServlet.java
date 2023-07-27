package controllers;

import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        {"/chi-tiet-san-pham/index", "/chi-tiet-san-pham/create", "/chi-tiet-san-pham/store", "/chi-tiet-san-pham/edit", "/chi-tiet-san-pham/update", "/chi-tiet-san-pham/delete"}
)
public class ChiTietSanPhamServlet extends HttpServlet {
    private ArrayList<ChiTietSanPham> chiTietSanPhamList;

    public ChiTietSanPhamServlet() {
        this.chiTietSanPhamList = new ArrayList<>();
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
                .getRequestDispatcher("/views/chitietsanpham/create.jsp")
                .forward(request, response);
    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
        int idNhaSanXuat = Integer.parseInt(request.getParameter("idNhaSanXuat"));
        int idMauSac = Integer.parseInt(request.getParameter("idMauSac"));
        int idDongSanPham = Integer.parseInt(request.getParameter("idDongSanPham"));
        int namBaoHanh = Integer.parseInt(request.getParameter("namBaohanh"));
        int soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
        double giaNhap = Double.parseDouble(request.getParameter("giaNhap"));
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        String moTa = request.getParameter("moTa");

        SanPham sanPham = new SanPham();
        NhaSanXuat nhaSanXuat = new NhaSanXuat();
        MauSac mauSac = new MauSac();
        DongSanPham dongSanPham = new DongSanPham();

        //

        //
        ChiTietSanPham cv = new ChiTietSanPham(1, sanPham, nhaSanXuat, mauSac, dongSanPham, namBaoHanh, moTa, soLuongTon, giaNhap, giaBan);
        chiTietSanPhamList.add(cv);
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //set list to chucVuList jsp
        request
                .setAttribute("chiTietSanPhamList", chiTietSanPhamList);
        //return jsp file
        request
                .getRequestDispatcher("/views/chitietsanpham/index.jsp")
                .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ChiTietSanPham chucvu;
        for (int i = 0; i < chiTietSanPhamList.size(); i++) {
            if (chiTietSanPhamList.get(i).getId() == id) {
                chucvu = chiTietSanPhamList.get(i);
                request
                        .setAttribute("data", chucvu);
                break;
            }
        }
        request
                .getRequestDispatcher("/views/chitietsanpham/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
        int idNhaSanXuat = Integer.parseInt(request.getParameter("idNhaSanXuat"));
        int idMauSac = Integer.parseInt(request.getParameter("idMauSac"));
        int idDongSanPham = Integer.parseInt(request.getParameter("idDongSanPham"));
        int namBaoHanh = Integer.parseInt(request.getParameter("namBaohanh"));
        int soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
        double giaNhap = Double.parseDouble(request.getParameter("giaNhap"));
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        String moTa = request.getParameter("moTa");

        SanPham sanPham = new SanPham();
        NhaSanXuat nhaSanXuat = new NhaSanXuat();
        MauSac mauSac = new MauSac();
        DongSanPham dongSanPham = new DongSanPham();

        //
        
        //
        ChiTietSanPham cv = new ChiTietSanPham(1, sanPham, nhaSanXuat, mauSac, dongSanPham, namBaoHanh, moTa, soLuongTon, giaNhap, giaBan);

        for (int i = 0; i < chiTietSanPhamList.size(); i++) {
            if (chiTietSanPhamList.get(i).getId() == id) {
                chiTietSanPhamList.set(i, cv);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (ChiTietSanPham cv : chiTietSanPhamList
        ) {
            if (cv.getId() == id) {
                chiTietSanPhamList.remove(cv);
                break;
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/chi-tiet-san-pham/index");
    }
}
