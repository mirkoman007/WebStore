<%-- 
    Document   : order
    Created on : 24.01.2022., 22:38:28
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
        <jsp:include page="header.jsp?page=orders"/>
        
        <div class="container">
            <h5 class="border-bottom pb-2">Order #${param.id}</h5>
            <c:forEach items="${orderItems}" var="item">
                <div class="d-sm-flex justify-content-between mb-4 pb-3 pb-sm-2 border-bottom">
                    <div class="d-sm-flex text-center text-sm-start"><a class="d-inline-block flex-shrink-0 mx-auto" href="./product.jsp?id=${item.productId}" style="width: 10rem;"><img src="${item.imageUrl}" width="160" alt="Product"></a>
                      <div class="ps-sm-4 pt-2">
                        <h3 class="product-title fs-base mb-2"><a href="./product.jsp?id=${item.productId}">${item.name}</a></h3>
                        <div class="fs-sm"><span class="text-muted me-2">Description</span>${item.description}</div>
                        <div class="fs-lg text-accent pt-2"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol=""/> kn</div>
                      </div>
                    </div>
                    <div class="pt-2 ps-sm-3 mx-auto mx-sm-0 text-center">
                      <div class="text-muted mb-2">Quantity:</div>${item.quantity}
                    </div>
                    <div class="pt-2 ps-sm-3 mx-auto mx-sm-0 text-center">
                      <div class="text-muted mb-2">Subtotal</div><fmt:formatNumber value="${item.price*item.quantity}" type="currency" currencySymbol=""/> kn
                    </div>
                </div>
            </c:forEach>
            <div class="px-2 py-1"><span class="text-muted">Total:&nbsp;</span><span class="fs-lg"><fmt:formatNumber value="${total}" type="currency" currencySymbol=""/> kn</span></div>
        </div>
    </body>
</html>
