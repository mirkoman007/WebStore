<%-- 
    Document   : products
    Created on : 25.01.2022., 19:21:36
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <a href="./categories.jsp" class="list-group-item list-group-item-action">Categories</a>
                        <a href="./products.jsp" class="list-group-item list-group-item-action active">Products</a>
                        <a href="./logs.jsp" class="list-group-item list-group-item-action">Login logs</a>
                        <a href="./orders.jsp" class="list-group-item list-group-item-action">Orders</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <c:if test="${param.page=='succDeleted'}"><div class="alert alert-success" role="alert">Product successfully deleted!</div></c:if>
                    <c:if test="${param.page=='succUpdated'}"><div class="alert alert-success" role="alert">Product successfully updated!</div></c:if>
                    <c:if test="${param.page=='succAdded'}"><div class="alert alert-success" role="alert">Product successfully added!</div></c:if>
                    <div class="table-responsive scrollbar">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Product</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${adminProducts}" var="product">
                                    <tr>
                                        <th scope="row">${product.id}</th>
                                        <td>${product.name}</td>
                                        <td>${product.category}</td>
                                        <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol=""/> kn</td>
                                        <td class="text-end">
                                            <div>
                                                <a class="btn p-0" type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit" href="./editProduct.jsp?id=${product.id}">
                                                    <span class="text-500 fas fa-edit"></span>
                                                </a>
                                                <a class="btn p-0 ms-2" type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="Delete" href="./deleteProduct?id=${product.id}">
                                                    <span class="text-500 fas fa-trash-alt"></span>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <a class="btn btn-outline-secondary" type="button" href="./addProduct.jsp">Add product</a>
                </div>
            </div>
        </div>
    </body>
</html>
