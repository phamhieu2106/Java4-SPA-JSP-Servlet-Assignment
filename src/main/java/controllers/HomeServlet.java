package controllers;

import entities.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

@WebServlet(
        {"/home/index", "/home/index1", "/home/index2", "/home/index3", "/home/add", "/home/cart", "/home/delete", "/home/cashout", "/home/invoice"}
)
public class HomeServlet extends HttpServlet {

    private ArrayList<GioHang> gioHangList;
    private ArrayList<ChiTietSanPham> chiTietSanPhamList1;
    private ArrayList<ChiTietSanPham> chiTietSanPhamList2;
    private ArrayList<ChiTietSanPham> chiTietSanPhamList3;

    public HomeServlet() {

        this.gioHangList = new ArrayList<>();
        this.chiTietSanPhamList1 = new ArrayList<>();
        this.chiTietSanPhamList2 = new ArrayList<>();
        this.chiTietSanPhamList3 = new ArrayList<>();

        SanPham sp1 = new SanPham(1, "Sản Phẩm 1", "Sản Phẩm 1");
        SanPham sp2 = new SanPham(2, "Sản Phẩm 2", "Sản Phẩm 2");
        SanPham sp3 = new SanPham(2, "Sản Phẩm 3", "Sản Phẩm 3");

        SanPham sp4 = new SanPham(4, "Sản Phẩm 4", "Sản Phẩm 4");
        SanPham sp5 = new SanPham(5, "Sản Phẩm 5", "Sản Phẩm 5");
        SanPham sp6 = new SanPham(6, "Sản Phẩm 6", "Sản Phẩm 6");

        SanPham sp7 = new SanPham(7, "Sản Phẩm 7", "Sản Phẩm 7");
        SanPham sp8 = new SanPham(8, "Sản Phẩm 8", "Sản Phẩm 8");
        SanPham sp9 = new SanPham(9, "Sản Phẩm 9", "Sản Phẩm 9");

        NhaSanXuat nsx = new NhaSanXuat(1, "1", "1");

        MauSac ms = new MauSac(1, "1", "1");

        DongSanPham dsp = new DongSanPham(1, "1", "1");

        ChiTietSanPham ctsp1 = new ChiTietSanPham(1, sp1, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp2 = new ChiTietSanPham(2, sp2, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp3 = new ChiTietSanPham(3, sp3, nsx, ms, dsp, 5, "", 99, 200, 200000);

        ChiTietSanPham ctsp4 = new ChiTietSanPham(4, sp4, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp5 = new ChiTietSanPham(5, sp5, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp6 = new ChiTietSanPham(6, sp6, nsx, ms, dsp, 5, "", 99, 200, 200000);

        ChiTietSanPham ctsp7 = new ChiTietSanPham(7, sp7, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp8 = new ChiTietSanPham(8, sp8, nsx, ms, dsp, 5, "", 99, 200, 200000);
        ChiTietSanPham ctsp9 = new ChiTietSanPham(9, sp9, nsx, ms, dsp, 5, "", 99, 200, 200000);

        this.chiTietSanPhamList1.add(ctsp1);
        this.chiTietSanPhamList1.add(ctsp2);
        this.chiTietSanPhamList1.add(ctsp3);

        this.chiTietSanPhamList2.add(ctsp4);
        this.chiTietSanPhamList2.add(ctsp5);
        this.chiTietSanPhamList2.add(ctsp6);

        this.chiTietSanPhamList3.add(ctsp7);
        this.chiTietSanPhamList3.add(ctsp8);
        this.chiTietSanPhamList3.add(ctsp9);
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
            if (uri.contains("index3")) {
                this.index(request, response, 3);
            } else if (uri.contains("index2")) {
                this.index(request, response, 2);
            } else {
                this.index(request, response, 1);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        for (GioHang gh : gioHangList
        ) {
            if (gh.getId() == id) {
                if (gh.getSoLuong() > 1) {
                    gh.setSoLuong(gh.getSoLuong() - 1);
                } else {
                    gioHangList.remove(gh);
                    break;
                }
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/home/cart");
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ChiTietSanPham ctsp = null;
        for (ChiTietSanPham newCTSP : chiTietSanPhamList1
        ) {
            if (newCTSP.getId() == id) {
                ctsp = newCTSP;
                break;
            }
        }
        for (ChiTietSanPham newCTSP : chiTietSanPhamList2
        ) {
            if (newCTSP.getId() == id) {
                ctsp = newCTSP;
                break;
            }
        }
        for (ChiTietSanPham newCTSP : chiTietSanPhamList3
        ) {
            if (newCTSP.getId() == id) {
                ctsp = newCTSP;
                break;
            }
        }
        if (ctsp != null) {
            boolean productInCart = false;
            for (GioHang gh : gioHangList
            ) {
                if (gh.getId() == id) {
                    gh.setSoLuong(gh.getSoLuong() + 1);
                    productInCart = true;
                    break;
                }
            }
            if (!productInCart) {
                GioHang newGH = new GioHang(
                        ctsp.getId(),
                        ctsp.getSanPham().getMa(),
                        ctsp.getSanPham().getTen(),
                        ctsp.getGiaBan(),
                        ctsp.getNamBH(),
                        1
                );
                gioHangList.add(newGH);
            }
        }
        response
                .sendRedirect("/Java4_Demo_war_exploded/home/index");
    }

    public void cart(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("listSanPham", gioHangList);
        int tong = 0;
        for (GioHang gh : gioHangList
        ) {

            tong += (gh.getGia() * gh.getSoLuong());
        }
        request
                .setAttribute("tong", tong);
        request
                .getRequestDispatcher("/views/giohang/index.jsp")
                .forward(request, response);
    }

    public void invoice(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String diaChi = request.getParameter("diaChi");
        request
                .setAttribute("name", name);
        request
                .setAttribute("diaChi", diaChi);
        request
                .setAttribute("listSanPham", gioHangList);
        double tong = 0;
        for (GioHang gh : gioHangList
        ) {
            tong += gh.getGia() * gh.getSoLuong();
        }
        request
                .setAttribute("tong", tong);
        request
                .getRequestDispatcher("/views/giohang/invoice.jsp")
                .forward(request, response);
    }

    public void cashout(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String tong = request.getParameter("tong");
        request
                .setAttribute("tong", tong);
        request
                .getRequestDispatcher("/views/giohang/cashout.jsp")
                .forward(request, response);
    }

    public void index(HttpServletRequest request, HttpServletResponse response, int listIndex)
            throws IOException, ServletException {
        switch (listIndex) {
            case 1:
                request
                        .setAttribute("sanPhamChiTietList", chiTietSanPhamList1);
                break;
            case 2:
                request
                        .setAttribute("sanPhamChiTietList", chiTietSanPhamList2);
                break;
            case 3:
                request
                        .setAttribute("sanPhamChiTietList", chiTietSanPhamList3);
                break;
            default:

                return;
        }
        request
                .getRequestDispatcher("/views/home/index.jsp")
                .forward(request, response);
    }
}