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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<form action="test/getPriceInCity" method="post">
    <input type="text" name="name" />
    <input type="submit" value="Invoke" />
</form>
</body>
</html>
