package controllers;

import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.*;
import webmodel.HoaDonSanPham;
import webmodel.SanPhamGioHang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(
        {"/home/index", "/home/add", "/home/cart", "/home/delete", "/home/cashout", "/home/invoice"}
)
public class HomeServlet extends HttpServlet {

    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    private GioHangChiTietRepository gioHangChiTietRepository;
    private GioHangRepository gioHangRepository;
    private SanPhamRepository sanPhamRepository;
    private KhachHangRepository khachHangRepository;
    private HoaDonRepository hoaDonRepository;
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public HomeServlet() {
        this.chiTietSanPhamRepository = new ChiTietSanPhamRepository();
        this.gioHangChiTietRepository = new GioHangChiTietRepository();
        this.gioHangRepository = new GioHangRepository();
        this.sanPhamRepository = new SanPhamRepository();
        this.khachHangRepository = new KhachHangRepository();
        this.hoaDonRepository = new HoaDonRepository();
        this.hoaDonChiTietRepository = new HoaDonChiTietRepository();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        if (uri.contains("cart")) {
            this.cart(request, response);
        } else if (uri.contains("add")) {
            this.addToCart(request, response);
        } else if (uri.contains("delete")) {
            this.remove(request, response);
        } else if (uri.contains("cashout")) {
            this.cashout(request, response);
        } else if (uri.contains("invoice")) {
            this.invoice(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID chiTietSP = UUID.fromString(request.getParameter("id"));

        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findOneById(chiTietSP);

        gioHangChiTietRepository.xoa(gioHangChiTiet);

        response
                .sendRedirect("/Java4_Demo_war_exploded/home/cart");
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int check = 0;
        UUID idCTSP = UUID.fromString(request.getParameter("id"));


        GioHang gioHang = gioHangRepository.findByMa("kh1");
        ChiTietSanPham ctsp = chiTietSanPhamRepository.getById(idCTSP);

        GioHangChiTietED gioHangChiTietED = new GioHangChiTietED(gioHang.getId(), ctsp.getId());


        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(gioHangChiTietED, 1, ctsp.getGiaBan(), ctsp.getGiaBan());
        for (GioHangChiTiet ghct : gioHangChiTietRepository.findById(gioHang.getId(), ctsp.getId())
        ) {
            if (gioHangChiTietED.getGioHangId() == ghct.getGioHangChiTietED().getGioHangId()
                    && gioHangChiTietED.getCtspId() == ghct.getGioHangChiTietED().getCtspId()) {
                ghct.setSoLuong(ghct.getSoLuong() + 1);
                gioHangChiTietRepository.update(ghct);
                check = 1;
            }
        }
        if (check == 0) {
            gioHangChiTietRepository.add(gioHangChiTiet);
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/home/index");
    }

    public void cart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        double tong = 0;
        List<SanPhamGioHang> newList = new ArrayList<>();
        for (GioHangChiTiet ghct : gioHangChiTietRepository.getAll()
        ) {
            ChiTietSanPham ctsp = chiTietSanPhamRepository.getById(ghct.getGioHangChiTietED().getCtspId());
            SanPham sp = sanPhamRepository.getById(ctsp.getSanPham().getId());

            newList.add(new SanPhamGioHang(ghct.getGioHangChiTietED().getGioHangId(),
                    ghct.getGioHangChiTietED().getCtspId(), sp.getMa(), sp.getTen(), ctsp.getGiaBan(), ghct.getSoLuong()));
        }
        for (SanPhamGioHang sanPhamGioHang : newList) {
            tong += sanPhamGioHang.getGia() * sanPhamGioHang.getSoLuong();
        }
        request
                .setAttribute("listSanPham", newList);
        request
                .setAttribute("tong", tong);
        request
                .getRequestDispatcher("/views/giohang/index.jsp")
                .forward(request, response);
    }

    public void invoice(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<HoaDonSanPham> hoaDonSanPhams = new ArrayList<>();

        String ma = hoaDonRepository.add();

        HoaDon newHoaDon = hoaDonRepository.findOne(ma);

        double tong = 0;

        for (GioHangChiTiet newGHCT : gioHangChiTietRepository.getAll()
        ) {
            HoaDonChiTietED hoaDonChiTietED = new HoaDonChiTietED(newHoaDon.getId(), newGHCT.getGioHangChiTietED().getCtspId());
            hoaDonChiTietRepository.add(new HoaDonChiTiet(hoaDonChiTietED, newGHCT.getSoLuong(), newGHCT.getDonGia()));

            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.getById(newGHCT.getGioHangChiTietED().getCtspId());
            SanPham sanPham = sanPhamRepository.getById(chiTietSanPham.getSanPham().getId());
            hoaDonSanPhams.add(new HoaDonSanPham(sanPham.getTen(), newGHCT.getSoLuong(), newGHCT.getDonGia()));
            tong += newGHCT.getSoLuong() * newGHCT.getDonGia();
        }
        for (GioHangChiTiet ghct : gioHangChiTietRepository.getAll()
        ) {
            gioHangChiTietRepository.xoa(ghct);
        }
        request
                .setAttribute("listSanPham", hoaDonSanPhams);
        request
                .setAttribute("tong", tong);
        request
                .setAttribute("name", request.getParameter("name"));
        request
                .setAttribute("diaChi", request.getParameter("diaChi"));
        request
                .getRequestDispatcher("/views/giohang/invoice.jsp")
                .forward(request, response);

    }

    public void cashout(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String tong = request.getParameter("tong");

        GioHang gioHang = gioHangRepository.findByMa("kh1");

        request
                .setAttribute("tong", tong);
        request
                .setAttribute("name", gioHang.getKhachHang().getMa());
        request
                .setAttribute("diaChi", gioHang.getKhachHang().getDiaChi());

        request
                .getRequestDispatcher("/views/giohang/cashout.jsp")
                .forward(request, response);
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("sanPhamChiTietList", chiTietSanPhamRepository.getAll());
        request
                .getRequestDispatcher("/views/home/index.jsp")
                .forward(request, response);
    }
}