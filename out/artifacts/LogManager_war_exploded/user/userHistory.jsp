<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Babu Sabin
  Date: 2/8/2017
  Time: 9:49 PM
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
</head>
<body role="document" background="img/backgroundimage1.jpg">
<%@include file="../include/loggedInHeader.jsp" %>

<h1 style="text-align:center;color:yellow;"><strong>Your History</strong></h1>
<%--<div style="background-color: #3e8f3e;height: auto;"></div>--%>
<hr>
<div class="container">
  <div class="row">
    <div class="col-sm-1"></div>
    <div class="col-sm-10">
      <div class="table-responsive">
        <table class="table" border="2">
          <tr style="background-color: #ffff00;font-size: 30px;">
            <th>Your ID</th>
            <th>Entry Date-Time</th>
            <th>Exit Date-Time</th>
            <th>Reason</th>
          </tr>
          <c:forEach items="${userHistoryList}" var="activityLog">
            <tr style="background-color: #e6e6e6; font-size: 20px; ">
              <td>${activityLog.id}</td>
              <td>${activityLog.entryDateTime}</td>
              <td>${activityLog.exitDateTime}</td>
              <td>${activityLog.reason}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
    <div class="col-sm-1"></div>
  </div>
</div>
<%@include file="../include/footer.jsp" %>
</body>
</html>

