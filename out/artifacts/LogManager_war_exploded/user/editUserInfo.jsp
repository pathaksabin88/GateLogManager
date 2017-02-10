<%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/9/2017
  Time: 1:22 PM
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
<body role="document" background="img/backgroundimage1.jpg">
<%@include file="../include/loggedInHeader.jsp" %>
<h1 style="text-align:center;color:yellow;">Welcome To Log Manager</h1><hr>
<div class="container">
  <div class="col-xs-6 col-md-4 col-md-offset-4">
    <div style="text-align:center;font-size:40px;">
      <h2>Edit Details Below</h2>
    </div>
    <div style="text-align:center;font-size:20px;">
      <form role="form" style="background-color:lightgrey;" action="updateEditUserInfo" method="POST">
        <input type="hidden" name="page" value="updateEditUserInfo">
        <div class="form-group">
          <br>
          <div style="text-align:center;font-size:20px;"><label for="id">User ID</label>
            <input type="text" class="form-control" name="id" id="id" value="${user.getId()}" placeholder="ID" style="height:42px; width:300px; text-align:center; margin:auto;" readonly>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="fname">First Name</label>
            <input type="text" class="form-control" name="fname" id="fname" value="${user.getFname()}" placeholder="First Name" style="height:42px; width:300px; text-align:center; margin:auto;" readonly>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="mname">Middle Name</label>
            <input type="text" class="form-control" name="mname" id="mname" value="${user.getMname()}" placeholder="Middle Name" style="height:42px; width:300px; text-align:center; margin:auto;">
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="lname">Last Name</label>
            <input type="text" class="form-control" name="lname" id="lname" value="${user.getLname()}" placeholder="Last Name" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="email">User Email ID</label>
            <input type="email" class="form-control" name="email" id="email" value="${user.getEmail()}" placeholder="Email ID" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="contact">Contact No.</label>
            <input type="number" class="form-control" name="contact" id="contact" value="${user.getContact()}" placeholder="Contact Number" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="fname">Category</label>
            <input type="text" class="form-control" name="category" id="category" value="${user.getCategory()}" placeholder="Category" style="height:42px; width:300px; text-align:center; margin:auto;" readonly>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" value="${user.getUsername()}" placeholder="User Name" style="height:42px; width:300px; text-align:center; margin:auto;" readonly>
          </div>
          <br><br>
          <div style="text-align:center;font-size:20px;"><label for="password">Password</label>
            <input type="text" class="form-control" name="password" id="password" value="${user.getPassword()}" placeholder="Password" style="height:42px; width:300px; text-align:center; margin:auto;" required>
          </div>
          <hr>
          <div style="text-align:center;font-size:20px;">
            <input type="submit" value="Update" name="proceed" class="btn btn-primary" style="font-size:20px; height:40px; width:200px">
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

