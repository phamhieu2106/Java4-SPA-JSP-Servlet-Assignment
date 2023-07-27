<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 7/1/2023
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Chi Tiết Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<header class="row">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/Java4_Demo_war_exploded/home/index">Home</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="/Java4_Demo_war_exploded/home/index">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/chuc-vu/index">Quản Lý Chức
                                Vụ</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/cua-hang/index">Quản Lý Cửa
                                Hàng</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/dong-san-pham/index">Quản Lý
                                Dòng Sản Phẩm</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/khach-hang/index">Quản Lý
                                Khách Hàng</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/mau-sac/index">Quản Lý Màu
                                Sắc</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/nhan-vien/index">Quản Lý
                                Nhân Viên</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/nha-san-xuat/index">Quản Lý
                                Nhà Sản Xuất</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/san-pham/index">Quản Lý Sản
                                Phẩm</a></li>
                            <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/chi-tiet-san-pham/index">Quản Lý
                                Chi Tiết Sản
                                Phẩm</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>
<h1 class="text-center">Thêm Chi Tiết Sản Phẩm</h1>
<form method="post" action="/Java4_Demo_war_exploded/chi-tiet-san-pham/update?id=${data.id}">

    <div class="mb-3">
        <label class="form-label">Sản Phẩm:</label>
        <select class="form-select" aria-label="Default select example" required name="idSanPham">
            <option selected value="1">Sản Phẩm 1</option>
            <option value="2">Sản Phẩm 2</option>

            <%--            <c:forEach var="tenSanPham" items="listTenSanPham">--%>
            <%--                <option value="${tenSanPham.ten}">${tenSanPham.ten}</option>--%>
            <%--            </c:forEach>--%>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Nhà Sản Xuất:</label>
        <select class="form-select" aria-label="Default select example" required name="idNhaSanXuat">
            <option selected value="1">Nhà Sản Xuất 1</option>
            <option value="2">Nhà Sản Xuất 2</option>

            <%--            <c:forEach var="tenSanPham" items="listTenSanPham">--%>
            <%--                <option value="${tenSanPham.ten}">${tenSanPham.ten}</option>--%>
            <%--            </c:forEach>--%>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Màu Sắc:</label>
        <select class="form-select" aria-label="Default select example" required name="idMauSac">
            <option selected value="1">Màu Sắc 1</option>
            <option value="2">Màu Sắc 2</option>

            <%--            <c:forEach var="tenSanPham" items="listTenSanPham">--%>
            <%--                <option value="${tenSanPham.ten}">${tenSanPham.ten}</option>--%>
            <%--            </c:forEach>--%>
        </select>
    </div>
    <div class="mb-3">
        <label class="form-label">Dòng Sản Phẩm:</label>
        <select class="form-select" aria-label="Default select example" required name="idDongSanPham">
            <option selected value="1">Dòng Sản Phẩm 1</option>
            <option value="2">Dòng Sản Phẩm 2</option>

            <%--            <c:forEach var="tenSanPham" items="listTenSanPham">--%>
            <%--                <option value="${tenSanPham.ten}">${tenSanPham.ten}</option>--%>
            <%--            </c:forEach>--%>
        </select>
    </div>

    <div class="mb-3">
        <label for="exampleInputNamBH" class="form-label">Năm Bảo Hành:</label>
        <input type="number" class="form-control" id="exampleInputNamBH" required name="namBaohanh"
               value="${data.namBH}">
    </div>
    <div class="mb-3">
        <label for="exampleInputSLT" class="form-label">Số Lượng Tồn:</label>
        <input type="number" class="form-control" id="exampleInputSLT" required name="soLuongTon"
               value="${data.soLuongTon}">
    </div>
    <div class="mb-3">
        <label for="exampleInputGN" class="form-label">Giá Nhập:</label>
        <input type="number" class="form-control" id="exampleInputGN" required name="giaNhap" value="${data.giaNhap}">
    </div>
    <div class="mb-3">
        <label for="exampleInputGB" class="form-label">Giá Bán:</label>
        <input type="number" class="form-control" id="exampleInputGB" required name="giaBan" value="${data.giaBan}">
    </div>
    <div class="mb-3">
        <label for="exampleInputMT" class="form-label">Mô Tả:</label>
        <input type="text" class="form-control" id="exampleInputMT" required name="moTa" value="${data.moTa}">
    </div>
    <div class="text-center">
        <button type="submit" class="btn btn-success">Submit</button>
    </div>
</form>


</body>
</html>
