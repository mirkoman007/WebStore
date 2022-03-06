<%-- 
    Document   : orders
    Created on : 24.01.2022., 16:59:37
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="dtf" uri="http://mirkozaper.from.hr/jsp/jctl/dtf" %>
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
            
            <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">Order #</th>
                    <th scope="col">Payment type</th>
                    <th scope="col">Date purchased</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orders}" var="item">
                        <tr>
                          <th scope="row"><a href="./order.jsp?id=${item.id}">${item.id}</a></th>
                          <c:if test="${item.paymentType==1}"><td>Cash on delivery</td></c:if>
                          <c:if test="${item.paymentType==2}"><td>Paypal</td></c:if>
                          <td><dtf:date datetime="${item.dateTime}"/> - <dtf:time datetime="${item.dateTime}"/></td>
                        </tr>            
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${empty orders}"><h4>You haven't ordered yet</h4></c:if>

        </div>
        
    </body>
</html>
