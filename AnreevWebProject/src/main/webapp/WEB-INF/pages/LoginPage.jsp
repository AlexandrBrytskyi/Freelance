<%@include file="include.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
    </style>

    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.css"%>
    </style>


    <!-- Custom styles for this template -->
    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/signin.css"%>
    </style>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>

<body>

<div class="container">

    <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Please sign in <%= request.getContextPath().toString()%>
        </h2>
        <label for="inputLogin" class="sr-only">Uniq number</label>
        <input type="text" id="inputLogin" name="loginInput" class="form-control" placeholder="uniq user number" required
               autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="passwordInput" class="form-control" placeholder="Password"
               required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>


</div> <!-- /container -->
<c:set var="error" value="${errorMessage}"/>
<c:if test="${not empty error}">

    <div class="alert alert-dismissible alert-danger" style="width: 30%; margin: auto">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${error}!</strong> <a href="#" class="alert-link">Change input</a> and try again.
    </div>
</c:if>

</body>
</html>
