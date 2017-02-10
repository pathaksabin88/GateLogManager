<%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/8/2017
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Log Manager PunchIn Home Page</title>
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
      if('${update}') {
        showNoty('success',"User Info Updated Successfully");
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
  <script type="text/javascript">
   /* var myVar=setInterval(function () {myTimer()}, 1000);
    var counter = 0;
    function myTimer() {
      var date = new Date();
      document.getElementById("datetime").innerHTML = date.toISOString();
    }*/

   function display_c(){
     var refresh=1000; // Refresh rate in milli seconds
     mytime=setTimeout('display_ct()',refresh);
   }

   function display_ct() {
     var strcount;
     var x = new Date();
     var x1=x.toUTCString();
//     document.getElementById("datetime").innerHTML = x1;
     document.getElementById('t1').value = x;
     tt=display_c();
   }

  </script>
</head>
<body role="document" background="img/backgroundimage1.jpg" onload=display_ct();>
<%@include file="../include/loggedInHeader.jsp" %>

<h1 style="text-align:center;color:yellow;"><strong>Its Time To PUNCH-IN</strong></h1>
<%--<div style="background-color: #3e8f3e;height: auto;"></div>--%>
<hr>
<div class="container">
  <div style="text-align:center;font-size:20px;">
    <input type=text id='t1' style="height:60px; width:750px; text-align:center; margin:auto; background-color: #f5f5f5; font-size: 25px;">
  </div>
  <div class="row">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">

      <div style="text-align:center;font-size:20px;">
        <form role="form" style="background-color:lightgrey;" action="processPunchIn" method="GET">
          <input type="hidden" name="page" value="processPunchIn">
          <div style="text-align:center;font-size:40px;background-color: #ffff00"><label for="reason">Reason To be Here</label>
            <input type="text" class="form-control" name="reason" id="reason" placeholder="Reason" style="height:300px; width:750px; text-align:center; font-size:40px; margin:auto;" required>
          </div>
          <hr>
          <div style="text-align:center;font-size:20px;">
            <input type="submit" value="Punch In" name="proceed" class="btn btn-primary" style="font-size:40px; height:80px; width:300px">
          </div>
          <br><br>
        </form>
      </div>

    </div>
    <div class="col-sm-2"></div>
  </div>
  <div class="col-xs-6 col-md-4 col-md-offset-4">

  </div>
</div>
<%@include file="../include/footer.jsp" %>
</body>
</html>
