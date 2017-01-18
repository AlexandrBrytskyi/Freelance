<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>User ${user.userInfo.userName}</title>
    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
    </style>

    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.css"%>
    </style>

    <script src="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>


<body>

<c:set var="error" value="${errorMessage}"/>
<c:set var="user" value="${user}"/>
<c:if test="${not empty error}">
    <div id="error_message">
            ${error}
    </div>
</c:if>

<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User avatar</h3>
    </div>
    <div class="panel-body">
        <img src="/getAvatar" style="align: center; max-width: 100%">
    </div>
</div>


<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User name:</h3>
    </div>
    <div class="panel-body">
        ${user.userInfo.userName}
    </div>
</div>

<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User surname:</h3>
    </div>
    <div class="panel-body">
        ${user.userInfo.userSurname}
    </div>
</div>


<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User phone:</h3>
    </div>
    <div class="panel-body">
        ${user.userInfo.userPhone}
    </div>
</div>

<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User address:</h3>
    </div>
    <div class="panel-body">
        ${user.userInfo.userAddress}
    </div>
</div>

<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User passport:</h3>
    </div>
    <div class="panel-body">
        ${user.userInfo.userPassport}
    </div>
</div>

<div class="panel panel-info" style="width: 30%; margin: auto">
    <div class="panel-heading">
        <h3 class="panel-title">User otdel:</h3>
    </div>
    <div class="panel-body">
        <ul class="nav nav-pills nav-stacked">
            <li class="disabled"><a href="" disabled="true">Name: ${user.otdel.otdelName}</a></li>
            <li class="disabled" style="height: auto" disabled="true"><a
                    href="">Description: ${user.otdel.otdelDesc}</a>
            </li>
            <li class="disabled"><a href="" disabled="true">Nachalnik: ${user.otdel.mentor.userInfo.userName}</a></li>
        </ul>
    </div>
</div>


<c:if test="${fn:length(user.posadas) gt 0}">
    <table class="table table-striped table-hover " style="width: 40%; margin: auto">
        <thead>
        <tr>
            <th>Name</th>
            <th>Salary</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.posadas}" var="item">
            <tr class="info">
                <td>${item.posadaName}</td>
                <td>${item.salary}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


<c:if test="${fn:length(user.workHistory.changesInWork) gt 0}">
    <table class="table table-striped table-hover " style="width: 40%; margin: auto">
        <caption>Changes in Vacancies</caption>
        <thead>
        <tr>
            <th>Previous salary</th>
            <th>Difference</th>
            <th>Changed salary</th>
            <th>Date</th>
            <th>Description</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.workHistory.changesInWork}" var="item">
            <tr class="info">
                <td>${item.previousSalary}</td>
                <td>${item.difference}</td>
                <td>${item.changedSalary}</td>
                <td>${item.dateSalaryChanged}</td>
                <td>${item.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${fn:length(user.workHistory.changesInPosada) gt 0}">

    <table class="table table-striped table-hover " style="width: 40%; margin: auto">
        <caption>Changes in posada</caption>
        <thead>
        <tr>
            <th>Previous posada</th>
            <th>New Posada</th>
            <th>Date</th>
            <th>Description</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.workHistory.changesInPosada}" var="item">
            <tr class="info">
                <td>${item.previousPosada.posadaName}</td>
                <td>${item.newPosada.posadaName}</td>
                <td>${item.datePosadaChanged}</td>
                <td>${item.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


<div class="btn-group btn-group-justified" style="margin: auto; width: 50%;">
    <c:if test="${user.canCreateOtdel}">
        <a href="/otdel?otdelID=${user.otdel.id}" class="btn btn-default">Go to otdel</a>
    </c:if>
    <a href="/changeInfo" class="btn btn-default">Change info</a>
    <a href="/changePass" class="btn btn-default">Change pass</a>
    <a href="/changePhoto" class="btn btn-default">Change photo</a>
</div>

</body>
</html>
