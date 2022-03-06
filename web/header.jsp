<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  <header>
    <div class="px-3 py-2 bg-dark text-white">
      <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <a href="<%=request.getContextPath()%>/" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
            <span class="fs-4">WebStore</span>
          </a>

          <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
            <li>
                
              <a href="<%=request.getContextPath()%>/" class="nav-link <c:if test="${param.page!='home'}">text-white</c:if><c:if test="${param.page=='home'}">text-secondary</c:if>">
                <i class="fas fa-home fa-2x d-block mb-1"></i>
                Home
              </a>
            </li>
            <c:if test="${loggedInUser.userType==1}">
                <li>
                  <a href="<%=request.getContextPath()%>/admin/categories.jsp" class="nav-link <c:if test="${param.page!='admin'}">text-white</c:if><c:if test="${param.page=='admin'}">text-secondary</c:if>">
                    <i class="fas fa-user-cog fa-2x d-block mb-1"></i>
                    Admin
                  </a>
                </li>
            </c:if>
            <li>
              <a href="<%=request.getContextPath()%>/orders.jsp" class="nav-link <c:if test="${param.page!='orders'}">text-white</c:if><c:if test="${param.page=='orders'}">text-secondary</c:if>">
                <i class="fas fa-list fa-2x d-block mb-1"></i>
                Orders
              </a>
            </li>
            <li>
              <a href="<%=request.getContextPath()%>/cart.jsp" class="nav-link <c:if test="${param.page!='shoppingCart'}">text-white</c:if><c:if test="${param.page=='shoppingCart'}">text-secondary</c:if>">
                <i class="fas fa-shopping-cart fa-2x d-block mb-1"></i>
                Shopping cart
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="px-3 py-2 border-bottom mb-3">
        <div class="container d-flex flex-wrap justify-content-end">
            
            <c:if test="${loggedInUser==null}">
                <div class="text-end">
                    <a href="<%=request.getContextPath()%>/login.jsp" type="button" class="btn btn-light text-dark me-2">Login</a>
                    <a href="<%=request.getContextPath()%>/register.jsp" type="button" class="btn btn-primary">Register</a>
                </div>
            </c:if>
            
            <c:if test="${loggedInUser!=null}">
                <div class="dropdown text-end">
                  <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                      <strong>${loggedInUser.username}</strong>
                  </a>
                  <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Log out</a></li>
                  </ul>
                </div>
            </c:if>


        </div>
    </div>
  </header>
  

    <style>

.bi {
  fill: currentColor;
}

.text-small {
  font-size: 85%;
}

    </style>