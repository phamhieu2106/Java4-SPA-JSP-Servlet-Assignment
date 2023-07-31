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
    <title>Danh Sách Nhân Viên</title>

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
<h1 class="text-center">Danh Sách Nhân Viên</h1>
<div class="col col-md-12 d-flex">
    <div class="col col-md-6">
        <form action="http://localhost:8080/Java4_Demo_war_exploded/nhan-vien/search?maSearch=${maSearch}" method="get">
            <div class="mb-3">
                <label for="exampleInputMa" class="form-label">Mã:</label>
                <input type="text" class="form-control" id="exampleInputMa" required name="maSearch">
                <div class="text-end">
                    <button type="submit" class="btn btn-success">Tìm</button>
                </div>
            </div>
        </form>
    </div>
    <div class="col col-md-6">
        <form action="http://localhost:8080/Java4_Demo_war_exploded/nhan-vien/loc?trangThai=${locTrangThai}"
              method="get">
            <div class="mb-3">
                <label class="form-label">Trạng Thái:</label>
                <select class="form-select" aria-label="Default select example" name="locTrangThai">
                    <option disabled>Open this select menu</option>
                    <option value="Hoạt Động">Hoạt Động</option>
                    <option value="Nghỉ Việc">Nghỉ Việc</option>
                </select>
                <div class="text-end">
                    <button type="submit" class="btn btn-success">Lọc</button>
                </div>
            </div>
        </form>
    </div>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Mã</th>
        <th scope="col">Tên</th>
        <th scope="col">Tên Đệm</th>
        <th scope="col">Họ</th>
        <th scope="col">Giới Tính</th>
        <th scope="col">Ngày Sinh</th>
        <th scope="col">Địa Chỉ</th>
        <th scope="col">Số Điện Thoại</th>
        <th scope="col">Mật Khẩu</th>
        <th scope="col">Cửa Hàng</th>
        <th scope="col">Chức Vụ</th>
        <th scope="col">Trạng Thái</th>
        <th scope="col" colspan="2">Functions</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="nhanVien" items="${nhanVienList}">
        <tr>
            <th scope="row">${nhanVien.id}</th>
            <td>${nhanVien.ma}</td>
            <td>${nhanVien.ten}</td>
            <td>${nhanVien.tenDem}</td>
            <td>${nhanVien.ho}</td>
            <td>${nhanVien.gioiTinh == false ? "Nữ" : "Nam"}</td>
            <td>${nhanVien.ngaySinh}</td>
            <td>${nhanVien.diaChi}</td>
            <td>${nhanVien.sdt}</td>
            <td>${nhanVien.matKhau}</td>
            <td>${nhanVien.cuaHang.ma}</td>
            <td>${nhanVien.chucVu.ma}</td>
            <td>${nhanVien.trangThai == true ? "Hoạt Động" : "Nghỉ Việc"}</td>
            <td>
                <a href="/Java4_Demo_war_exploded/nhan-vien/edit?id=${nhanVien.id}">
                    <button class="btn btn-primary">Update</button>
                </a>
                <a href="/Java4_Demo_war_exploded/nhan-vien/delete?id=${nhanVien.id}">
                    <button class="btn btn-warning">Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-center">
    <a href="/Java4_Demo_war_exploded/nhan-vien/create">
        <button class="btn btn-success">Thêm</button>
    </a>
</div>
</body>
</html>
