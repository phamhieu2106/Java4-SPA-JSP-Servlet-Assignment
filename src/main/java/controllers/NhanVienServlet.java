package controllers;

import entities.ChucVu;
import entities.CuaHang;
import entities.GioHang;
import entities.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.ChucVuRepository;
import repositories.CuaHangRepository;
import repositories.GioHangRepository;
import repositories.NhanVienRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@WebServlet(
        {"/nhan-vien/index", "/nhan-vien/create", "/nhan-vien/store", "/nhan-vien/edit", "/nhan-vien/update"
                , "/nhan-vien/delete", "/nhan-vien/search", "/nhan-vien/loc"}
)
public class NhanVienServlet extends HttpServlet {
    private ArrayList<NhanVien> nhanVienList;
    private NhanVienRepository repository;
    private ChucVuRepository chucVuRepository;
    private CuaHangRepository cuaHangRepository;
    private GioHangRepository gioHangRepository;

    public NhanVienServlet() {
        this.nhanVienList = new ArrayList<>();
        this.repository = new NhanVienRepository();
        this.chucVuRepository = new ChucVuRepository();
        this.cuaHangRepository = new CuaHangRepository();
        this.gioHangRepository = new GioHangRepository();
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
        boolean status = false;
        String locTrangThai = request.getParameter("locTrangThai");
        if (locTrangThai.equalsIgnoreCase("Hoạt Động")) {
            status = true;
        }
        ArrayList<NhanVien> newList = new ArrayList<>();

        for (NhanVien nv : repository.getAll()
        ) {
            if (nv.isTrangThai() == status) {
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
        for (NhanVien ch : repository.getAll()
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
                .setAttribute("listChucVu", chucVuRepository.getAll());
        request
                .setAttribute("listCuaHang", cuaHangRepository.getAll());
        request
                .getRequestDispatcher("/views/nhanvien/create.jsp")
                .forward(request, response);

    }

    public void store(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        boolean gioiTinh = true;
        boolean trangThaiStatus = true;

        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");
        String gioiTinhString = request.getParameter("gioiTinh");
        if (!gioiTinhString.equals("Nữ")) {
            gioiTinh = false;
        }
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");

        UUID cuaHang = UUID.fromString(request.getParameter("cuaHang"));
        CuaHang ch = cuaHangRepository.getById(cuaHang);
        UUID chucVu = UUID.fromString(request.getParameter("chucVu"));
        ChucVu cv = chucVuRepository.getById(chucVu);

        NhanVien nv = new NhanVien(null, ma, ten, tenDem, ho, gioiTinh, ngaySinh, diaChi, sdt, matKhau, ch, cv, trangThaiStatus);

        repository.insert(nv);

        GioHang gioHang = new GioHang(null, null, nv, null, null, null, nv.getTen(), nv.getDiaChi(), 0);
        gioHangRepository.add(gioHang);

        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }

    public void index(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request
                .setAttribute("nhanVienList", repository.getAll());
        request
                .getRequestDispatcher("/views/nhanvien/index.jsp")
                .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        repository.delete(repository.getById(id));
        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        request
                .setAttribute("data", repository.getById(id));
//        request
//                .setAttribute("cuaHangList", cuaHangRepository.getAll());
//        request
//                .setAttribute("chucVuList", chucVuRepository.getAll());
        request
                .getRequestDispatcher("/views/nhanvien/edit.jsp")
                .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        boolean gioiTinh = true;
        boolean trangThaiStatus = true;
        String ten = request.getParameter("ten");
        String ma = request.getParameter("ma");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");
        String gioiTinhString = request.getParameter("gioiTinh");
        if (!gioiTinhString.equals("Nữ")) {
            gioiTinh = false;
        }
        String ngaySinh = request.getParameter("ngaySinh");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");

//        UUID cuaHang = UUID.fromString(request.getParameter("cuaHang"));
//        CuaHang ch = cuaHangRepository.getById(cuaHang);
//        UUID chucVu = UUID.fromString(request.getParameter("chucVu"));
//        ChucVu cv = chucVuRepository.getById(chucVu);

        NhanVien nv = repository.getById(id);
        CuaHang ch = nv.getCuaHang();
        ChucVu cv = nv.getChucVu();

        if (!request.getParameter("trangThai").equalsIgnoreCase("Hoạt Động")) {
            trangThaiStatus = false;
        }
        repository.update(new NhanVien(id, ma, ten, tenDem, ho, gioiTinh, ngaySinh, diaChi, sdt, matKhau, ch, cv, trangThaiStatus));

        response
                .sendRedirect("/Java4_Demo_war_exploded/nhan-vien/index");
    }
}
