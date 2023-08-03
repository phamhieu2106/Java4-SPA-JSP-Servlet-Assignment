<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 7/1/2023
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="f" uri="jakarta.tags.functions" %>--%>
<%--<%@ taglib prefix="c1" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Chi Tiết Các Sản Phẩm</title>

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
<h1 class="text-center">Chi Tiết Các Sản Phẩm</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Mã Sản Phẩm</th>
        <th scope="col">Mã Nhà Sản Xuất</th>
        <th scope="col">Mã Màu Sắc</th>
        <th scope="col">Mã Dòng Sản Phẩm</th>
        <th scope="col">Năm Bảo Hành</th>
        <th scope="col">Số Lượng Tồn</th>
        <th scope="col">Giá Nhập</th>
        <th scope="col">Giá Bán</th>
        <th scope="col">Mô Tả</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="chiTietSP" items="${chiTietSanPhamList}">
        <tr>
            <th scope="row">${chiTietSP.id}</th>
            <td>${chiTietSP.sanPham.ma}</td>
            <td>${chiTietSP.nhaSanXuat.ma}</td>
            <td>${chiTietSP.mauSac.ma}</td>
            <td>${chiTietSP.dongSanPham.ma}</td>
            <td>${chiTietSP.namBH}</td>
            <td>${chiTietSP.soLuongTon}</td>
            <td>${chiTietSP.giaNhap}</td>
            <td>${chiTietSP.giaBan}</td>
            <td>${chiTietSP.moTa}</td>
            <td>
                <a href="/Java4_Demo_war_exploded/chi-tiet-san-pham/edit?id=${chiTietSP.id}">
                    <button class="btn btn-primary">Update</button>
                </a>
                <a href="/Java4_Demo_war_exploded/chi-tiet-san-pham/delete?id=${chiTietSP.id}">
                    <button class="btn btn-warning">Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<div class="text-center">
    <a href="/Java4_Demo_war_exploded/chi-tiet-san-pham/create">
        <button class="btn btn-success">Thêm</button>
    </a>
</div>
</body>
</html>
