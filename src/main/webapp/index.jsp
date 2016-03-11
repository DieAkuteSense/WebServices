<%--
  Created by IntelliJ IDEA.
  User: Olli
  Date: 10.03.2016
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Tranistional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">
<head>
    <title>Price Service</title>
</head>
<body>
<form action="services/rest/getPriceInCity" method="POST">
    <input name="lat" />
    <input name="lon" />
    <input type="submit" />
</form>
</body>
</html>
