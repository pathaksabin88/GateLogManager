<%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/6/2017
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="main.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <style>
    /* Sticky footer styles
    -------------------------------------------------- */
    html {
      position: relative;
      min-height: 100%;
    }
    body {
      /* Margin bottom by footer height */
      margin-bottom: 40px;
    }
    .navbar-default {
      /*background-color: #51ef63;*/
      background-color: #000000;
      border-color: #030033;
    }

  </style>
</head>
<body role="document">
<!-- Fixed navbar -->
<div class="navbar navbar-default" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand brand-name" href="#" style="color:white;">
        <strong>
          L M
        </strong>
      </a>
    </div>
    <div class="navbar-collapse navbar-left collapse">
      <div class="row">
        <form action="userHome" method="GET">
          <input type="hidden" name="page" value="userHome">
          <div style="text-align:center;font-size:20px;">
            <input type="submit" value="Home" name="proceed" class="btn btn-info" style="font-size:20px; height:50px; width:120px">
          </div>
        </form>
      </div>
    </div>
    <div class="navbar-collapse navbar-right collapse">
      <%--<button class="btn-lg"><a href="register?page=register">Register</a></button>--%>
        <form action="register" method="GET">
          <input type="hidden" name="page" value="register">
          <div style="text-align:center;font-size:20px;">
            <input type="submit" value="Register" name="proceed" class="btn btn-success" style="font-size:20px; height:50px; width:120px">
          </div>
        </form>
    </div>
  </div>
</div>

</body>
</html>