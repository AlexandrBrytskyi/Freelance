<%@include file="include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add user</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="/assets/bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">


    <script src="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>

<c:set var="jobs" value="${jobs}"/>

<form action="/hire" method="post" class="form-horizontal" style="width: 50%; margin: auto">


    <fieldset>
        <legend>Choosing job for user</legend>

        <div class="form-group">
            <label for="select" class="col-lg-2 control-label">Available vacations</label>
            <div class="col-lg-10">
                <select class="form-control" name="job" id="select">
                    <c:forEach items="${jobs}" var="item">
                        <option value=${item.id}>${item.id}, ${item.posadaName}, ${item.mentor.userInfo.userName}, ${item.description} </option>
                    </c:forEach>
                </select>
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
        <strong>${error}!</strong> <a href="#" class="alert-link">Change input</a> and try again.
    </div>
</c:if>

</body>
</html>
