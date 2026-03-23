<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Checkout</title>

    <link href="<c:url value='/index_files/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/index_files/cover.css' />" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      /* Widen the central container slightly so the table doesn't squish */
      .cover-container {
        max-width: 800px !important;
      }
    </style>
</head>

<body class="d-flex min-vh-100 text-center text-white bg-dark">
    
<div class="cover-container d-flex w-100 min-vh-100 p-3 mx-auto flex-column">
  
  <c:set var="pageTitle" value="cart" scope="request" />
  <%@ include file='include/header.jsp'%>

  <main class="px-3 mb-5 mt-4">
    <h1 class="fw-bold mb-4">Review Your Order</h1>
    
    <c:choose>
        <c:when test="${not empty cartItems}">
            
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover text-start mt-4 align-middle border-secondary">
                    <thead>
                        <tr class="text-white-50">
                            <th>Book Title</th>
                            <th>Author</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Qty</th>
                            <th class="text-end">Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td class="fw-bold">${item.title}</td>
                                <td>${item.author}</td>
                                <td class="text-center">$${item.price}</td>
                                <td class="text-center">${item.qty}</td>
                                <td class="text-end text-success fw-bold">$${item.price * item.qty}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-flex justify-content-between align-items-center mt-4 p-3 bg-secondary bg-opacity-25 rounded border border-secondary">
                <h4 class="mb-0">Grand Total:</h4>
                <h3 class="mb-0 text-success fw-bold">$${totalPrice}</h3>
            </div>

            <form action="<c:url value='/order' />" method="POST" class="text-end mt-5">
                <a href="<c:url value='/shop' />" class="btn btn-outline-light btn-lg me-3">Back to Shop</a>
                <button type="submit" class="btn btn-success btn-lg fw-bold px-5">Confirm & Pay</button>
            </form>

        </c:when>
        
        <c:otherwise>
            <div class="mt-5">
                <h3 class="text-warning mb-3">empty cart</h3>
                <a href="<c:url value='/shop' />" class="btn btn-outline-light btn-lg fw-bold">go shop</a>
            </div>
        </c:otherwise>
    </c:choose>

  </main>

  <%@ include file='include/footer.jsp'%>
</div>

<script src="<c:url value='/index_files/bootstrap.bundle.min.js' />"></script>
</body>
</html>