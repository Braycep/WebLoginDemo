<%--
  Created by IntelliJ IDEA.
  User: Braycep
  Date: 2018/3/16
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>谢谢观看</title>
  </head>
  <body>
      <form action="/myServlet" method="post">
          <input type="text" name="username" required><br>
          <input type="password" name="password" required><br>
          <input type="submit" value="登陆">
      </form>
  </body>
</html>
