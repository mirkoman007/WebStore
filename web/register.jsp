<%-- 
    Document   : register
    Created on : 07.01.2022., 00:22:18
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="form-auth register">
            <form method="POST" action="register">
                <c:if test="${param.page=='passnotmatch'}"><div class="alert alert-danger" role="alert">Passwords must match!</div></c:if>
                <c:if test="${param.page=='already'}"><div class="alert alert-danger" role="alert">User with that username already exist!</div></c:if>
                <h1 class="h3 mb-3 fw-normal">Please register</h1>

                <div class="row g-2 mb-3">
                  <div class="col-md">
                    <div class="form-floating">
                        <input type="text" name="firstName" class="form-control" id="firstName" placeholder="First name" required>
                      <label for="firstName">First name</label>
                    </div>
                  </div>
                  <div class="col-md">
                    <div class="form-floating">
                      <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last name" required>
                      <label for="lastName">Last name</label>
                    </div>
                  </div>
                </div>   

                <div class="form-floating">
                  <input type="text" name="username" class="form-control" id="username" placeholder="Username" required>
                  <label for="username">Username</label>
                </div>

                <div class="form-floating">
                  <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
                  <label for="password">Password</label>
                </div>

                <div class="form-floating mb-3">
                  <input type="password" name="rePassword" class="form-control" id="rePassword" placeholder="Re-enter password" required>
                  <label for="rePassword">Re-enter password</label>
                </div>

                <div class="row g-2 mb-3">
                  <div class="col-md-9">
                    <div class="form-floating">
                      <input type="text" name="address" class="form-control" id="address" placeholder="Address" required>
                      <label for="address">Address</label>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-floating">
                      <input type="text" name="zip" class="form-control" id="zip" placeholder="Zip" required>
                      <label for="zip">Zip</label>
                    </div>
                  </div>
                </div>   

                <div class="form-floating">
                  <input type="text" name="city" class="form-control" id="city" placeholder="City" required>
                  <label for="city">City</label>
                </div>

                <div class="form-floating mb-3">
                  <input type="text" name="country" class="form-control" id="country" placeholder="Country" required>
                  <label for="country">Country</label>
                </div>

                <div class="checkbox mb-3">
                  <label>
                      <input type="checkbox" value="remember-me" required> I accept Terms and Conditions
                  </label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Create free account</button>
                <p class="mt-3 mb-3 text-muted">Already have account? <a href="./login.jsp" class="text-muted ms-1"><b>Log in</b></a></p>
          </form>
        </div>
    </body>
</html>
