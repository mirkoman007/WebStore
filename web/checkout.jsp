<%-- 
    Document   : checkout
    Created on : 21.01.2022., 23:42:23
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
        
        <script src="https://www.paypal.com/sdk/js?client-id=AeG6WpVuwZ40twGNZqaAGcI9pS0WiaAXGprP5ieJys4wE2NTqluPj41MgZ8sD9Kyc4tMpHI4C4oY7Elg&currency=EUR"></script>

        
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="container mb-5">
            <div class="row g-5">
              <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                  <span class="text-primary">Your cart</span>
                  <span class="badge bg-primary rounded-pill">3</span>
                </h4>
                <ul class="list-group mb-3">
                    <c:forEach items="${cartItems}" var="item">
                        <li class="list-group-item d-flex justify-content-between lh-sm">
                          <div>
                            <h6 class="my-0">${item.name}</h6>
                            <small class="text-muted">x${item.quantity}</small>
                          </div>
                          <span class="text-muted"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol=""/> kn</span>
                        </li>
                    </c:forEach>
                    <li class="list-group-item d-flex justify-content-between">
                    <span>Total:</span>
                    <strong><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol=""/> kn</strong>
                    </li>
                </ul>
              </div>
                
              <div class="col-md-7 col-lg-8">
                <h4 class="mb-3">Shipping address</h4>
                <form id="payForm" method="post" action="checkout">
                  <div class="row g-3">
                    <div class="col-sm-6">
                      <label for="firstName" class="form-label">First name</label>
                      <input type="text" class="form-control" name="firstName" id="firstName" value="${loggedInUser.firstName}" required>
                    </div>

                    <div class="col-sm-6">
                      <label for="lastName" class="form-label">Last name</label>
                      <input type="text" class="form-control" name="lastName" id="lastName" value="${loggedInUser.lastName}" required>
                    </div>

                    <div class="col-9">
                      <label for="address" class="form-label">Address</label>
                      <input type="text" class="form-control" name="address" id="address" value="${loggedInUser.address}" required>
                    </div>
                      
                    <div class="col-md-3">
                      <label for="zip" class="form-label">Zip</label>
                      <input type="text" class="form-control" name="zip" id="zip" value="${loggedInUser.zipCode}" required>
                    </div>

                    <div class="col-md-12">
                      <label for="city" class="form-label">City</label>
                      <input type="text" class="form-control" name="city" id="city" value="${loggedInUser.city}" required>
                    </div>
                      
                    <div class="col-md-12">
                      <label for="country" class="form-label">Country</label>
                      <input type="text" class="form-control" name="country" id="country" value="${loggedInUser.country}" required>
                    </div>


                  </div>

                  <hr class="my-4">

                  <h4 class="mb-3">Payment</h4>

                  <div class="my-3">

                      
                    <input type="hidden" id="paymentMethod" name="paymentMethod" value="credit">
                    <input type="hidden" id="paypalPrice" value="${subtotal}">
                    <div id="paypalbutton"></div>
                  </div>

                  <hr class="my-4">

                  <button class="w-100 btn btn-primary btn-lg" type="submit">Cash on delivery</button>
                </form>
              </div>
            </div>   
        </div>     

                <script>
                    
                    const amountElement=document.getElementById("paypalPrice")
paypal.Buttons({

    // Sets up the transaction when a payment button is clicked
    createOrder: function(data, actions) {
      return actions.order.create({
        purchase_units: [{
          amount: {
            value: amountElement.value*0.13 // Can reference variables or functions. Example: `value: document.getElementById('...').value`
          }
        }]
      });
    },

    // Finalize the transaction after payer approval
    onApprove: function(data, actions) {
      return actions.order.capture().then(function(orderData) {
        document.getElementById("paymentMethod").value = "paypal";
        document.getElementById("payForm").submit();
      });
    }
}).render('#paypalbutton');</script>

   </body>
</html>
