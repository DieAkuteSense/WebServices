<%@ page import="FuelPriceService.FuelPriceClient" %>
<%@ page import="FuelPriceService.FuelPriceService" %>
<%@ page import="static FuelPriceService.FuelPriceClient.*" %><%--
  Created by IntelliJ IDEA.
  User: Olli
  Date: 10.03.2016
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Price Service</title>
</head>
<body>
    <p>Hello from the other side!</p>
    <% new FuelPriceService().getPriceInCity("123"); %>
    <form method="get" action="/requestData">
        <input type="text" name="lat" />
        <input type="text" name="lon" />
        <input type="submit" />
    </form>
</body>
</html>
