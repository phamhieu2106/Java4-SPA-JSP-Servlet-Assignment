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
    <title>Thêm Cửa Hàng</title>
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
<h1 class="text-center">Thêm Cửa Hàng</h1>
<form method="post" action="/Java4_Demo_war_exploded/cua-hang/store">
    <div class="mb-3">
        <label for="exampleInputMa" class="form-label">Mã:</label>
        <input type="text" class="form-control" id="exampleInputMa" required name="ma">
    </div>
    <div class="mb-3">
        <label for="exampleInputTen" class="form-label">Tên:</label>
        <input type="text" class="form-control" id="exampleInputTen" required name="ten">
    </div>
    <div class="mb-3">
        <label for="exampleInputDiaChi" class="form-label">Địa Chỉ:</label>
        <input type="text" class="form-control" id="exampleInputDiaChi" required name="diaChi">
    </div>
    <div class="mb-3">
        <label for="exampleInputThanhPho" class="form-label">Thành Phố:</label>
        <input type="text" class="form-control" id="exampleInputThanhPho" required name="thanhPho">
    </div>
    <div class="mb-3">
        <label for="exampleInputQuocGia" class="form-label">Quốc Gia:</label>
        <input type="text" class="form-control" id="exampleInputQuocGia" required name="quocGia">
    </div>
    <div class="text-center">
        <button type="submit" class="btn btn-success">Submit</button>
    </div>
</form>


</body>
</html>
