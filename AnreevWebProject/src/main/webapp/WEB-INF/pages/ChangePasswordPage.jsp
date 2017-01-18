<%@include file="include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Change password</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="/assets/bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">


    <script src="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>


<form action="/changePass" method="post" class="form-horizontal" style="width: 50%; margin: auto">
    <fieldset>
        <legend>Changing password</legend>

        <div class="form-group">
            <label for="old" class="col-lg-2 control-label">Old</label>
            <div class="col-lg-10">
                <input type="password" name="old" class="form-control" id="old"
                       placeholder="Name">
            </div>
        </div>

        <div class="form-group">
            <label for="new" class="col-lg-2 control-label">New</label>
            <div class="col-lg-10">
                <input type="password" name="new" class="form-control" id="new"
                       placeholder="Name">
            </div>
        </div>

        <div class="form-group">
            <label for="newSubmit" class="col-lg-2 control-label">Confirm</label>
            <div class="col-lg-10">
                <input type="password" value="" name="newSubmit" class="form-control" id="newSubmit"
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
