<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 6/30/2023
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <h2>username: ${username}</h2>
    <h2>password: ${password}</h2>


    <form action="/Java4_Demo_war_exploded/login" method="get">
        <label>username:</label>
        <input name="username" type="text"> <br>
        <label>password:</label>
        <input name="password" type="password"> <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
