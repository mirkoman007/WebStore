<%-- 
    Document   : checkout-complete
    Created on : 23.01.2022., 23:59:05
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
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        
        <div class="container pb-5 mb-sm-4">
            <div class="pt-5">
              <div class="card py-3 mt-sm-3">
                <div class="card-body text-center">
                  <h2 class="h4 pb-3">Thank you for your order!</h2>
                  <p class="mb-2">Your order has been placed and will be processed as soon as possible.</p>
                  <p class="mb-2">Your order number is #${param.orderId}</p>
                  <a class="btn btn-secondary mt-3 me-3" href="./index.jsp">Go back shopping</a><a class="btn btn-primary mt-3" href="./orders.jsp"><i class="ci-location"></i>&nbsp;Orders</a>
                </div>
              </div>
            </div>
        </div>
        
        
        
    </body>
</html>
