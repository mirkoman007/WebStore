<%-- 
    Document   : add_category
    Created on : 31.01.2022., 16:16:16
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../head.jsp" %>
    </head>
    <body>
        <jsp:include page="../header.jsp?page=admin"/>
        
        <div class="container">
            
            <h3 class="mb-5">Add category</h3>
            <form method="post" action="addCategory">
                <div class="col-12 mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="col-12 mb-3">
                    <label for="imageUrl" class="form-label">Image (url)</label>
                    <input type="url" class="form-control" id="imageUrl" name="imageUrl" required>
                </div>
                <button class="btn btn-primary" type="submit">Add</button>
            </form>
        </div>
    </body>
</html>
