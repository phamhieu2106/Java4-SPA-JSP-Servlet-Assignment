<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 7/25/2023
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hoá Đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="card">
    <div class="card-body mx-4">
        <div class="container">
            <p class="my-5 mx-5" style="font-size: 30px;">Thank for your purchase</p>
            <div class="row">
                <ul class="list-unstyled">
                    <li class="text-black">${name}</li>
                    <%--                    <li class="text-muted mt-1"><span class="text-black">Invoice</span> #12345</li>--%>
                    <li class="text-black mt-1">${diaChi}</li>
                </ul>
                <hr>
            </div>
            <c:forEach var="sanPham" items="${listSanPham}">
                <div class="row">
                    <div class="col-xl-8">
                        <p>${sanPham.tenSanPham}</p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-center">SL: ${sanPham.soLuong}
                        </p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-end">${sanPham.gia}VNĐ
                        </p>
                    </div>
                    <hr>
                </div>
            </c:forEach>
            <div class="row text-black">

                <div class="col-xl-12">
                    <p class="float-end fw-bold">Total: ${tong}VNĐ
                    </p>
                </div>
                <hr style="border: 2px solid black;">
            </div>
            <div class="text-center" style="margin-top: 90px;">
                <a href="http://localhost:8080/Java4_Demo_war_exploded/home/index"><u class="text-info">View in
                    browser</u></a>
                <p>Rất Hân Hạnh Được Phục Vụ Bạn!. </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
