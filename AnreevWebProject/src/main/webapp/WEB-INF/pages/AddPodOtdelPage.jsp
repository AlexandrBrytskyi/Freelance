<%@include file="include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add otdel</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="/assets/bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">


    <script src="../../assets/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>


<form action="/add_podotdel" method="post" class="form-horizontal" style="width: 50%; margin: auto">
    <fieldset>
        <legend>Adding new PodOtdel</legend>
        <div class="form-group">
            <label for="inputName" class="col-lg-2 control-label">Otdel Name</label>
            <div class="col-lg-10">
                <input type="text" value="${otdelName}" name="otdelName" class="form-control" id="inputName"
                       placeholder="Name">
            </div>
        </div>

        <div class="form-group">
            <label for="textArea" class="col-lg-2 control-label">Description</label>
            <div class="col-lg-10">
                <textarea name="otdelDesc" class="form-control" rows="3" id="textArea"
                          style="margin-top: 0px; margin-bottom: 0px; height: 131px;">${otdelDesc}</textarea>

            </div>
        </div>

        <input name="parentOtdelID" type="text" style="display: none" value="${parentOtdelID}">

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
