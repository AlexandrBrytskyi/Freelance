<%@include file="include.jsp" %>
<html>
<head>
    <title>Register</title>
    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
    </style>

    <style>
        <%@ include file="../../assets/bootstrap-3.3.7-dist/css/bootstrap.css"%>
    </style>
</head>
<body>

<c:set var="error" value="${errorMessage}"/>
<c:if test="${not empty error}">
    <div id="error_message">
            ${error}
    </div>
</c:if>

<form name="registerForm" action="/register" enctype="multipart/form-data" method="post" style="width: 40%; margin: auto">
    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Name</label>
        <div class="col-lg-10">
            <input type="text" value="" name="name" class="form-control"
                   id="name"
                   placeholder="name">
        </div>
    </div>

    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Surname</label>
        <div class="col-lg-10">
            <input type="text" value="" name="surname" class="form-control"
                   id="surname"
                   placeholder="Surname">
        </div>
    </div>


    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Phone</label>
        <div class="col-lg-10">
            <input type="tel" value="" name="phone" class="form-control"
                   id="phone"
                   placeholder="Phone">
        </div>
    </div>

    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Address</label>
        <div class="col-lg-10">
            <input type="tel" value="" name="phone" class="form-control"
                   id="address"
                   placeholder="Address">
        </div>
    </div>

    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Passport</label>
        <div class="col-lg-10">
            <input type="text" value=""  name="passport" class="form-control"
                   id="passport"
                   placeholder="Passport">
        </div>
    </div>

    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Password</label>
        <div class="col-lg-10">
            <input type="text" value=""  name="password" class="form-control"
                   id="password"
                   placeholder="Password">
        </div>
    </div>





    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"> Password</label>
        <div class="col-lg-10">
            <input type="file" accept="image/jpeg" value=""  name="avatar" class="form-control"
                   id="file">
        </div>
    </div>


    <div class="form-group">
        <div class="col-lg-10 col-lg-offset-2">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>

</form>

</body>
</html>
