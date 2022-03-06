<%-- 
    Document   : product
    Created on : 05.01.2022., 00:01:02
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
              <!-- Product gallery-->
              <div class="col-lg-7 pe-lg-0 pt-lg-4">
                <img src="${product.imageUrl}" class="img-fluid mx-auto d-block" alt="...">
              </div>
              <!-- Product details-->
              <div class="col-lg-5 pt-4 pt-lg-0">
                <div class="product-details ms-auto pb-3">
                  <h2>${product.name}</h2>
                  <div class="mb-3">
                      <span class="h4 fw-normal"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol=""/> kn</span>
                  </div>
                  <form class="mb-grid-gutter" method="post" action="product">
                    <div class="mb-3 d-flex align-items-center">
                        <input type="hidden" name="productId" value="<%= request.getParameter("id")%>">
                        <input type="number" name="quantity" class="form-control me-3" style="width: 5rem;" value="1" min="1">
                        <button class="btn btn-primary btn-shadow d-block w-100" type="submit"><i class="ci-cart fs-lg me-2"></i>Add to Cart</button>
                    </div>
                  </form>
                  <!-- Product panels-->
                  <div class="accordion mb-4" id="productPanels">
                    <div class="accordion-item">
                      <h3 class="accordion-header">
                          <a class="accordion-button" href="#productInfo" role="button" data-bs-toggle="collapse" aria-expanded="true" aria-controls="productInfo">
                              <i class="ci-announcement text-muted fs-lg align-middle mt-n1 me-2"></i>Product info
                          </a>
                      </h3>
                      <div class="accordion-collapse collapse show" id="productInfo" data-bs-parent="#productPanels" style="">
                        <div class="accordion-body">
                          ${product.description}
                        </div>
                      </div>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
        </div>
    </body>
</html>
