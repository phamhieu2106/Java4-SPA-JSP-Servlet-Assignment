<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 7/16/2023
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trang Chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/407f72145a.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid m-0 p-0">
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
                                Quản Lý
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
                                <li><a class="dropdown-item" href="/Java4_Demo_war_exploded/chi-tiet-san-pham/index">Quản
                                    Lý
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
    <div class="main">
        <h1 class="text-center">Trang Chủ</h1>
        <div class="text-end">
            <a href="/Java4_Demo_war_exploded/home/cart">
                Giỏ Hàng:<i class="fa-solid fa-cart-shopping fa-2xl"></i>
            </a>
        </div>
        <h2>Danh Sách Sản Phẩm</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <c:forEach var="ctsp" items="${sanPhamChiTietList}">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${ctsp.sanPham.ten}</h5>
                            <p class="card-text">${ctsp.mauSac.ten}</p>
                            <p class="card-text">${ctsp.dongSanPham.ten}</p>
                            <p class="card-text">${ctsp.giaBan}VNĐ</p>
                            <a href="/Java4_Demo_war_exploded/home/add?id=${ctsp.id}" class="btn btn-primary">Thêm vào
                                giỏ
                                hàng</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="d-flex justify-content-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="/Java4_Demo_war_exploded/home/index1">1</a></li>
                    <li class="page-item"><a class="page-link" href="/Java4_Demo_war_exploded/home/index2">2</a></li>
                    <li class="page-item"><a class="page-link" href="/Java4_Demo_war_exploded/home/index3">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
