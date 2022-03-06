<%-- 
    Document   : categories
    Created on : 25.01.2022., 19:20:49
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
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">
                        <a href="./categories.jsp" class="list-group-item list-group-item-action active">Categories</a>
                        <a href="./products.jsp" class="list-group-item list-group-item-action">Products</a>
                        <a href="./logs.jsp" class="list-group-item list-group-item-action">Login logs</a>
                        <a href="./orders.jsp" class="list-group-item list-group-item-action">Orders</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <c:if test="${param.page=='notEmpty'}"><div class="alert alert-danger" role="alert">Category is not empty!</div></c:if>
                    <c:if test="${param.page=='succDeleted'}"><div class="alert alert-success" role="alert">Category successfully deleted!</div></c:if>
                    <c:if test="${param.page=='succUpdated'}"><div class="alert alert-success" role="alert">Category successfully updated!</div></c:if>
                    <c:if test="${param.page=='succAdded'}"><div class="alert alert-success" role="alert">Category successfully added!</div></c:if>
                    <div class="table-responsive scrollbar">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Category</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${categories}" var="category">
                                    <tr>
                                        <th scope="row">${category.id}</th>
                                        <td>${category.name}</td>
                                        <td class="text-end">
                                            <div>
                                                <a class="btn p-0" type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit" href="./editCategory.jsp?id=${category.id}"">
                                                    <span class="text-500 fas fa-edit"></span>
                                                </a>
                                                <a class="btn p-0 ms-2" type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="Delete" href="./deleteCategory?id=${category.id}">
                                                    <span class="text-500 fas fa-trash-alt"></span>
                                                </a>
                                            </div>
                                        </td>
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <a class="btn btn-outline-secondary" type="button" href="./addCategory.jsp">Add category</a>
                </div>
            </div>
        </div>
    </body>
</html>
