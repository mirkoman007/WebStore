<%-- 
    Document   : error404
    Created on : 02.02.2022., 00:24:24
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="head.jsp" %>
    </head>
    <body>
        <div class="container mt-5">
            <div class="p-5 mb-4 bg-light rounded-3">
              <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold">Page not found</h1>
                <p class="col-md-8 fs-4">The page you're looking at doesn't exist.</p>
                <a class="btn btn-primary btn-lg" type="button" href="<%=request.getContextPath()%>/index.jsp">Go to homepage</a>
              </div>
            </div>
        </div>
    </body>
</html>
