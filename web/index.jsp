<%-- 
    Document   : index
    Created on : 04.01.2022., 00:03:53
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
        <jsp:include page="header.jsp?page=home"/>
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                
                <c:forEach items="${categories}" var="category">
                    <div class="col">
                        <a href="./category.jsp?id=${category.id}">
                            <div class="home-category-block m-4 text-center row">
                                <div class="w-100">
                                    <figure class="figure">
                                        <img src="${category.imageUrl}" title="${category.name}" alt="${category.name}" class="figure-img img-fluid rounded p-4">
                                        <figcaption>${category.name}</figcaption>
                                    </figure>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
                
            </div>
        </div>
        
  
    </body>
</html>
