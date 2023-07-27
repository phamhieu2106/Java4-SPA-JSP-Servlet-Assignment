<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 6/29/2023
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>HELLO ${yourName}!</h1>

<form method="get" action="/Java4_Demo_war_exploded/hello">
    <label>Your Name:</label>
    <input type="text" name="ten">
    <br>
    <button type="submit">Submit</button>
</form>

</body>
</html>
