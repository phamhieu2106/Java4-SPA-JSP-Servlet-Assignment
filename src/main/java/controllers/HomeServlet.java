package controllers;

import entities.ChiTietSanPham;
import entities.GioHang;
import entities.GioHangChiTiet;
import entities.GioHangChiTietED;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.ChiTietSanPhamRepository;
import repositories.GioHangChiTietRepository;
import repositories.GioHangRepository;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(
        {"/home/index", "/home/add", "/home/cart", "/home/delete", "/home/cashout", "/home/invoice"}
)
public class HomeServlet extends HttpServlet {

    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    private GioHangChiTietRepository gioHangChiTietRepository;
    private GioHangRepository gioHangRepository;

    public HomeServlet() {
        this.chiTietSanPhamRepository = new ChiTietSanPhamRepository();
        this.gioHangChiTietRepository = new GioHangChiTietRepository();
        this.gioHangRepository = new GioHangRepository();
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


        response
                .sendRedirect("/Java4_Demo_war_exploded/home/cart");
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID idCTSP = UUID.fromString(request.getParameter("id"));

        // có thể thay đổi
        GioHang gioHang = gioHangRepository.findByMa("Hieu");
        //
        ChiTietSanPham ctsp = chiTietSanPhamRepository.getById(idCTSP);

        GioHangChiTietED gioHangChiTietED = new GioHangChiTietED(gioHang.getId(), ctsp.getId());

        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(gioHangChiTietED, 1, ctsp.getGiaBan(), ctsp.getGiaBan());

        for (GioHangChiTiet gioHangChiTiet1 : gioHangChiTietRepository.getAll()
        ) {
            if (gioHangChiTiet1.getGhctId().getGioHangId() == gioHang.getId()
                    && gioHangChiTiet1.getGhctId().getCtspId() == ctsp.getId()) {
                gioHangChiTiet1.setSoLuong(gioHangChiTiet1.getSoLuong() + 1);
                return;
            }

            gioHangChiTietRepository.add(gioHangChiTiet);

            response
                    .sendRedirect("/Java4_Demo_war_exploded/home/index");
        }
    }

    public void cart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        request
                .getRequestDispatcher("/views/giohang/index.jsp")
                .forward(request, response);
    }

    public void invoice(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request
                .getRequestDispatcher("/views/giohang/invoice.jsp")
                .forward(request, response);
    }

    public void cashout(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

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