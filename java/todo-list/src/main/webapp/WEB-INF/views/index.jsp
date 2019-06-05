<%@ page import="java.util.Collection" %>
<%--
  Created by IntelliJ IDEA.
  User: daisukh
  Date: 2019-05-30
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
  </head>
  <body>

  <form method="post" action="./todo">
    <input type="text" name="data">
    <button type="submit">送信</button>
  </form>

  <ul>
    <% for (String item: (Collection<String>)request.getAttribute("items")) { %>
    <li><%= item %></li>
    <% } %>
  </ul>
  </body>
</html>
