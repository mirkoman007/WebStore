<%-- 
    Document   : login
    Created on : 06.01.2022., 23:49:57
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="head.jsp" %>
        <link href="./css/auth.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <jsp:include page="header.jsp"/>
        <div class="form-auth login">
            <form method="post" action="login">
            <h1 class="h3 mb-3 fw-normal">Please log in</h1>

            <div class="form-floating">
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="username" required>
                <label for="floatingInput">Username</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="password" required>
              <label for="floatingPassword">Password</label>
            </div>
            
            <button class="w-100 btn btn-lg btn-primary" type="submit">Log in</button>
            <p class="mt-3 mb-3 text-muted">Don't have an account? <a href="./register.jsp" class="text-muted ms-1"><b>Create account</b></a></p>
          </form>
        </div>
    </body>
</html>
