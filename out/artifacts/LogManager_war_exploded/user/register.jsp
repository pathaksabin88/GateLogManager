<%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/6/2017
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Log Manager</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="main.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <style>
  </style>
</head>
<body role="document" background="img/backgroundimage6.jpg">
<%@include file="../include/header.jsp" %>
<h1 style="text-align:center;color:yellow;">Welcome To Log Manager</h1><hr>
<div class="container">
  <div class="col-xs-6 col-md-4 col-md-offset-4">
    <div style="text-align:center;font-size:40px;">
      <h2>Fill Details Below</h2>
    </div>
    <div style="text-align:center;font-size:20px;">
      <form role="form" style="background-color:lightgrey;" action="processRegister" method="POST">
        <input type="hidden" name="page" value="processRegister">
        <div class="form-group">
          <br>
          <div style="text-align:center;font-size:20px;">
            <input type="text" class="form-control" name="id" id="id" value="${newId}" placeholder="ID" style="height:42px; width:300px; text-align:center; margin:auto;" readonly>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <input type="text" class="form-control" name="fname" id="fname" placeholder="First Name" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <input type="text" class="form-control" name="mname" id="mname" placeholder="Middle Name" style="height:42px; width:300px; text-align:center; margin:auto;">
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <input type="text" class="form-control" name="lname" id="lname" placeholder="Last Name" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <input type="email" class="form-control" name="email" id="email" placeholder="Email ID" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <input type="number" class="form-control" name="contact" id="contact" placeholder="Contact Number" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;">
            <select name="category" style="text-align:center;height:42px; width:300px;">
              <option value="DWIT Student">DWIT Member</option>
              <option value="Deerwalk Employee">Deerwalk Employee</option>
              <option value="DFS Staff">DFS Staff</option>
              <option value="Guest Members">Visitors</option>
            </select>
          </div>
          <hr>
          <div style="text-align:center;font-size:20px;">
            <input type="submit" value="Register" name="proceed" class="btn btn-primary" style="font-size:20px; height:40px; width:200px">
          </div>
          <br><br>
        </div>
    </form>
    </div>
   </div>
</div>
<%@include file="../include/footer.jsp" %>
</body>
</html>
