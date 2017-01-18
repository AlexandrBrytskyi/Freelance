<%@include file="include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Change personal info</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="/assets/bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">


    <script src="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>


<form action="/changeInfo" method="post" class="form-horizontal" style="width: 50%; margin: auto">
    <fieldset>
        <legend>Changing own info</legend>
        <div class="form-group">
            <label for="userId" class="col-lg-2 control-label">Otdel Number</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userPersonalNum}" disabled="true" name="id" class="form-control"
                       id="userId"
                       placeholder="pass">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Name</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userInfo.userName}" name="name" class="form-control" id="inputName"
                       placeholder="new">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Surname</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userInfo.userSurname}" name="surname" class="form-control" id="inputSurname"
                       placeholder="again">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Phone</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userInfo.userPhone}" name="phone" class="form-control" id="phone"
                       placeholder="Name">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Address</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userInfo.userAddress}" name="address" class="form-control" id="address"
                       placeholder="Name">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Passport</label>
            <div class="col-lg-10">
                <input type="text" value="${user.userInfo.userPassport}" name="passport" class="form-control" id="passport"
                       placeholder="Name">
            </div>
        </div>


        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>

    </fieldset>
</form>

<c:set var="error" value="${errorMessage}"/>
<c:if test="${not empty error}">

    <div class="alert alert-dismissible alert-danger" style="width: 30%; margin: auto">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${error}!</strong> <a href="#" class="alert-link"></a>
    </div>
</c:if>

</body>
</html>
