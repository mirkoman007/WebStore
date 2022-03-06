<%-- 
    Document   : category 
    Created on : 04.01.2022., 22:23:28
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
        <%@include file="head.jsp" %>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">
                        <c:forEach items="${categories}" var="category">
                            <a href="./category.jsp?id=${category.id}" class="list-group-item list-group-item-action <c:if test="${category.id==param.id}">active</c:if>">${category.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-9">
                    <c:if test="${empty products}"><h2>There are no products in this category.</h2></c:if>
                    <div class="row">
                        
                        <c:forEach items="${products}" var="product">
                            <div class="col-lg-3 col-md-6 col-12">
                                <div class="card  mb-4 card-hover">
                                    <a href="./product.jsp?id=${product.id}" class="card-img-top"><img src="${product.imageUrl}" alt="" class="card-img-top rounded-top-md"></a>
                                    <div class="card-body">
                                        <h3 class="h6 mb-2 text-truncate-line-2 "><a href="./product.jsp?id=${product.id}" class="text-inherit">${product.name}</a></h3>
                                        <div class="mb-3">
                                            <span>${product.description}</span>
                                        </div>
                                    </div>

                                    <div class="card-footer">
                                        <div class="row align-items-center g-0">
                                            <div class="col">
                                                <span><fmt:formatNumber value="${product.price}" type="currency" currencySymbol=""/> kn</span>
                                            </div>
                                            <div class="col-auto">
                                                <a href="./product?id=${product.id}">
                                                    <i class="fas fa-shopping-cart me-1"></i><span>Add to cart</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
