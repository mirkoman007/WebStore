<%-- 
    Document   : cart
    Created on : 05.01.2022., 14:17:53
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
        <jsp:include page="header.jsp?page=shoppingCart"/>
        <div class="container">
            <div class="row">
                <!-- List of items-->
                <section class="col-lg-8">
                    
                    <c:if test="${empty cartItems}"><h2>Your shopping cart is empty</h2></c:if>
                    
                    <c:forEach items="${cartItems}" var="item">
                        <!-- Item-->
                        <form class="d-sm-flex justify-content-between align-items-center my-2 pb-3 border-bottom" method="post" action="cart">
                          <div class="d-block d-sm-flex align-items-center text-center text-sm-start"><a class="d-inline-block flex-shrink-0 mx-auto me-sm-4" href="./product.jsp?id=${item.id}"><img src="${item.imageUrl}" width="160" alt="Product"></a>
                            <div class="pt-2">
                              <input type="hidden" name="itemId" value="${item.id}">
                              <h3 class="product-title fs-base mb-2"><a href="./product.jsp?id=${item.id}">${item.name}</a></h3>
                              <div class="fs-lg text-accent pt-2"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol=""/> kn</div>
                            </div>
                          </div>
                          <div class="pt-2 pt-sm-0 ps-sm-3 mx-auto mx-sm-0 text-center text-sm-start" style="max-width: 9rem;">
                            <label class="form-label" for="quantity1">Quantity</label>
                            <input class="form-control" type="number" name="quantity" id="quantity1" min="1" value="${item.quantity}">
                            <button class="btn btn-link px-0 text-dark" href="cart" type="submit"><i class="fas fa-edit"></i><span class="fs-sm">Update</span></button>
                            <a class="btn btn-link px-0 text-danger" href="cart?id=${item.id}" type="button"><i class="fas fa-times-circle"></i><span class="fs-sm">Remove</span></a>
                          </div>
                        </form>
                    </c:forEach>
                    <c:if test="${not empty cartItems}">
                        <div class="d-grid gap-2">
                            <a type="button" href="cart?clear=true" class="btn btn-outline-primary">Clear shopping cart</a>
                        </div>
                    </c:if>
                </section>
                <!-- Sidebar-->
                <aside class="col-lg-4 pt-4 pt-lg-0 ps-xl-5">
                    <div class="bg-white rounded-3 shadow-lg p-4">
                      <div class="py-2 px-xl-2">
                        <div class="text-center mb-4 pb-3 border-bottom">
                          <h2 class="h6 mb-3 pb-1">Subtotal</h2>
                          <h3 class="fw-normal"><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="" /> kn</h3>
                        </div>

                        <a class="btn btn-primary btn-shadow d-block w-100 mt-4 <c:if test="${empty cartItems}">disabled</c:if>" href="./checkout.jsp"><i class="ci-card fs-lg me-2"></i>Proceed to Checkout</a>
                      </div>
                    </div>
                </aside>
            </div>
        </div>
</html>
