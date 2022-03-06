<%-- 
    Document   : addProduct
    Created on : 31.01.2022., 16:19:44
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
            
            <h3 class="mb-5">Add product</h3>
            <form method="post" action="addProduct">
                <div class="col-12 mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="col-12 mb-3">
                    <label for="imageUrl" class="form-label">Image (url)</label>
                    <input type="url" class="form-control" id="imageUrl" name="imageUrl" required>
                </div>
                <div class="col-12 mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" class="form-control" id="price" name="price" min="0" step=".01" required>
                </div>
                <div class="col-12 mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description" required>
                </div>
                <div class="col-12 mb-3">
                    <label for="category" class="form-label">Category</label>
                    <select class="form-select" id="category" name="categoryId" required>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-primary" type="submit">Add</button>
            </form>
        </div>
    </body>
</html>
