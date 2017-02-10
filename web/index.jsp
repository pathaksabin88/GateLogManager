        <% String message = null;
          String displaymessage = null;%><%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/6/2017
  Time: 4:14 PM
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
  <script src="js/main.js"></script>
  <link href="css/sweetalert.css" rel="stylesheet" type="text/css" />
  <script src="js/sweetalert.min.js"></script>
  <script src="js/jquery.js"></script>
  <script src="js/Noty/js/noty/packaged/jquery.noty.packaged.js"></script>
  <script>
    $(document).ready(function(){
      if('${register}') {
        showNoty('success',"User Successfully registered!");
      }
      if('${logout}') {
        showNoty('error',"Logout Successful!");
      }
      if('${loginerror}') {
        showNoty('error',"Invalid Username, Password!");
      }
    });
    function showNoty(type,message){
      var n = noty({
        layout: 'topCenter',
        theme: 'relax',
        type: type,
        text: message,
        animation: {
          open: {height: 'toggle'},
          close: {height: 'toggle'},
          easing: 'swing', // easing
          speed: 500
        },
        timeout: 2500
      })
    }

  </script>
</head>
<body role="document" background="img/backgroundimage6.jpg">
<%@include file="include/header.jsp" %>
<div class="container">
  <div class="col-xs-6 col-md-4 col-md-offset-4">
    <form role="form" style="background-color:lightpink;" action="login" method="POST">

      <div class="form-group alert alert-danger">
        <h2 class="text-center" style=""><strong>Login Panel</strong></h2>
        <%--<% displaymessage = (String) request.getAttribute("message");
          if(displaymessage!=null){
            out.print(displaymessage);
          }

        %>--%>

         <%-- <% if(session.getAttribute("registeredUser")!= null) { %><h4>New User successfully registered </h4><% session.removeAttribute("registeredUser"); } %>
        <h5>${message}</h5>--%>
      </div>
      <div class="form-group">
        <input type="hidden" name="page" value="login">
        <br>
        <div style="text-align:center;font-size:20px;"><label for="inputUsername">Username</label>
          <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Username" style="height:38px; width:300px; text-align:center; margin:auto;"></div>
      </div>
      <div class="form-group">
        <br>
        <div style="text-align:center;font-size:20px;"><label for="inputPassword">Password</label></div>
        <div style="text-align:center"><input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" style="height:38px;width:300px;text-align:center; margin:auto;"></div>
      </div>
      <br>
      <div style="text-align:center"><input type="submit" value="Login" name="login" class="btn btn-primary" style="font-size:20px; height:60px; width:200px"></div>
      <br><br>
    </form>
  </div>
</div>
<%@include file="include/footer.jsp" %>
</body>

</html>
