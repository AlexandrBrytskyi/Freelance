<%@ page import="app.model.Otdel" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Otdel ${otdel.otdelName}</title>
    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
    </style>

    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.css"%>
    </style>


    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--<script>--%>
    <%--<%@ include file="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js" %>--%>
    <%--</script>--%>
    <%--<script>--%>
    <%--<%@ include file="../../assets/bootstrap-3.3.7-dist/js/bootstrap.min.js" %>--%>
    <%--</script>--%>
    <%--<script>--%>
    <%--<%@ include file="../../assets/bootstrap-3.3.7-dist/js/npm.js" %>--%>
    <%--</script>--%>

</head>

<body>

<c:set var="otdel" value="${otdel}"/>

<c:set var="error" value="${errorMessage}"/>
<c:if test="${not empty error}">
    <div class="alert alert-dismissible alert-danger" style="width: 30%; margin: auto">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${error}!</strong> <a href="#" class="alert-link"></a>
    </div>
</c:if>

<div class="panel panel-default" style="width: 50%; margin: auto">
    <div class="panel-body">
        <table class="table table-striped table-hover" style="width: 90%">
            <tbody>
            <tr>
                <td width="25%">Otdel name:</td>
                <td width="80%">${otdel.otdelName}</td>
            </tr>

            <tr>
                <td width="25%">Otdel description:</td>
                <td width="80%">${otdel.otdelDesc}</td>
            </tr>

            <tr>
                <td width="25%">Parent otdel:</td>
                <td width="80%">${otdel.parent.otdelName}</td>
            </tr>


            <tr>
                <td width="25%">Rukovoditel:</td>
                <td width="80%">${otdel.mentor.userInfo.userSurname}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>


<%--podotdels--%>
<div class="panel panel-primary" style="width: 50%; margin: auto">
    <div class="panel-heading">
        <h3 class="Potodtely">Podotdely</h3>
    </div>
    <div class="panel-body" style="width: 100%">
        <ul class="nav nav-pills nav-stacked">
            <c:forEach items="${otdel.podOtdel}" var="item">
                <li><a href="/otdel?otdelID=${item.id}">${item.otdelName},
                    mentor:${item.mentor.userInfo.userName}, ${item.otdelDesc}</a></li>
            </c:forEach>
        </ul>
        <div class="btn-group btn-group-justified" style="width: 50%; margin: auto">
            <a href="/add_podotdel?parentOtdelID=${otdel.id}" class="btn btn-default">Add Podotdel</a>
        </div>
    </div>
</div>


<c:set var="users" value="${otdel.workers}"/>


<%--users and Vacancies--%>
<ul class="nav nav-tabs" style="width: 50%; margin: auto">
    <li class=""><a href="#users" data-toggle="tab" aria-expanded="false">Users</a></li>
    <li class="active"><a href="#posadas" data-toggle="tab"
                          aria-expanded="true">Posadas</a></li>
</ul>

<div id="myTabContent" class="tab-content" style="width: 50%; margin: auto">
    <div class="tab-pane fade" id="users">
        <%--users--%>
        <table class="table table-striped table-hover">
            <tbody>
            <%--foreach user--%>
            <c:forEach items="${users}" var="user">
                <tr style="width: 80%; margin: auto">
                    <td style="width: 50%"><%--user--%>
                        <ul class="nav nav-pills">
                            <li class="dropdown" style="width: 40%;">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                                        <%--user name, id, surname--%>
                                    #${user.userPersonalNum}, ${user.userInfo.userName} ${user.userInfo.userSurname}<span
                                        class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" style="width: 300%">
                                    <li><a disabled="true" href="">
                                            <%--userInfo--%>
                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User avatar</h3>
                                            </div>
                                            <div class="panel-body">
                                                <img src="/getAvatar" style="align: center; max-width: 100%">
                                            </div>
                                        </div>


                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User name:</h3>
                                            </div>
                                            <div class="panel-body">
                                                    ${user.userInfo.userName}
                                            </div>
                                        </div>

                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User surname:</h3>
                                            </div>
                                            <div class="panel-body">
                                                    ${user.userInfo.userSurname}
                                            </div>
                                        </div>


                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User phone:</h3>
                                            </div>
                                            <div class="panel-body">
                                                    ${user.userInfo.userPhone}
                                            </div>
                                        </div>

                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User address:</h3>
                                            </div>
                                            <div class="panel-body">
                                                    ${user.userInfo.userAddress}
                                            </div>
                                        </div>

                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">User passport:</h3>
                                            </div>
                                            <div class="panel-body">
                                                    ${user.userInfo.userPassport}
                                            </div>
                                        </div>


                                        <c:if test="${fn:length(user.workHistory.changesInWork) gt 0}">
                                            <table class="table table-striped table-hover ">
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

                                            <table class="table table-striped table-hover ">
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
                                    </a></li>
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Hire user</h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="btn-group btn-group-justified">
                                                <a href="/hire?userToHireId=${user.userPersonalNum}&otdelId=${otdel.id}"
                                                   class="btn btn-default">Hire${user.userInfo.userSurname}</a>
                                            </div>
                                        </div>
                                    </div>
                                </ul>
                            </li>
                        </ul>
                    </td>
                    <td style="width: 50%"><%--his posadas--%>
                        <ul class="nav nav-pills">
                            <li class="dropdown" style="width: 40%">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                                    Posadas<span class="caret"></span>
                                </a>
                                <c:set var="posadas" value="${user.posadas}"/>
                                    <%--for each posada--%>


                                <ul class="dropdown-menu" style="width: 400%">

                                    <c:forEach items="${posadas}" var="posada">
                                        <li>
                                                <%--menuForEveryPosada (grow, disch, fire)--%>

                                        <li class="divider"></li>

                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">#${posada.id}, ${posada.posadaName}</h3>
                                            </div>
                                            <div class="panel-body">
                                                <div class="btn-group btn-group-justified" style="width: 100%">
                                                    <a href="/growdischarge?userTochangeId=${user.userPersonalNum}&grow=grow&posada=${posada.id}&otdel=${otdel.id}"
                                                       class="btn btn-default">Grow salary</a>
                                                    <a href="/growdischarge?userTochangeId=${user.userPersonalNum}&grow=no&posada=${posada.id}&otdel=${otdel.id}"
                                                       class="btn btn-default">Discharge salary</a>
                                                    <a href="/fire?userToFireId=${user.userPersonalNum}&posada=${posada.id}&otdel=${otdel.id}"
                                                       class="btn btn-default">Fire
                                                        worker ${user.userInfo.userName}</a>
                                                </div>
                                            </div>
                                        </div>

                                        </li>
                                    </c:forEach>
                                </ul>
                                    <%--end for each posada--%>

                            </li>
                        </ul>
                    </td>
                </tr>
                <%--end foreach--%>
            </c:forEach>

            </tbody>
            <%--add userButton--%>

        </table>
        <div class="btn-group btn-group-justified">
            <a href="/addUser?otdelId=${otdel.id}"
               class="btn btn-default">Add new User</a>
        </div>
    </div>


    <div class="tab-pane fade active in" id="posadas">
        <c:set var="posadas" value="${otdel.posadasInOtdel}"/>
        <%--<div class="tab-pane fade" id="dropdown2">--%>
        <%--posadas--%>
        <table class="table table-striped table-hover ">
            <caption>Posadas</caption>
            <thead>
            <tr>
                <th>Posada name</th>
                <th>Mentor</th>
                <th>Description</th>
                <th>Posada otdel</th>
                <th>Salary</th>
                <th>Worker</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${posadas}" var="posada">
                <tr class="info">
                    <td>${posada.posadaName}</td>
                    <td>${posada.mentor.userInfo.userSurname}</td>
                    <td>${posada.description}</td>
                    <td>${posada.posadaOtdel.otdelName}</td>
                    <td>${posada.salary}</td>
                    <td>#${posada.userWorking.userPersonalNum}, ${posada.userWorking.userInfo.userSurname}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%--add posada--%>

        <div class="btn-group btn-group-justified">
            <a href="/addPosada?otdel=${otdel.id}"
               class="btn btn-default">Create posada</a>
        </div>

        <%--</div>--%>
    </div>
</div>


</body>
</html>
