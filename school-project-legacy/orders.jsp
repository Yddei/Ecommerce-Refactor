<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Your Orders</title>

    <link href="<c:url value='/index_files/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/index_files/cover.css' />" rel="stylesheet">

    <style>
      .bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; user-select: none; }
      @media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }
      .cover-container { max-width: 900px !important; }
    </style>
</head>

<body class="d-flex min-vh-100 text-center text-white bg-dark">
    
<div class="cover-container d-flex w-100 min-vh-100 p-3 mx-auto flex-column">
  
  <c:set var="pageTitle" value="Orders" scope="request" />
  <%@ include file='include/header.jsp'%>

  <main class="px-3 mb-5 mt-4 text-start">
    <h1 class="fw-bold mb-4 text-center">Order History</h1>
    
    <c:choose>
        <c:when test="${not empty orderHistory}">
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover mt-4 align-middle border-secondary shadow">
                    <thead>
                        <tr class="text-white-50">
                            <th>Order #</th>
                            <th>Date</th>
                            <th>Delivery Address</th>
                            <th>Status</th>
                            <th class="text-end">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderHistory}">
                            <tr>
                                <td class="fw-bold">ORD-${order.id}</td>
                                <td>${order.orderDate}</td>
                                <td><small>${order.address}</small></td>
                                <td>
                                    <span class="badge ${order.status == 'Processing' ? 'bg-warning text-dark' : 'bg-success'}">
                                        ${order.status}
                                    </span>
                                </td>
                                <td class="text-end text-success fw-bold">$${order.totalPrice}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        
        <c:otherwise>
            <div class="mt-5 text-center">
                <h3 class="text-warning mb-3">No orders found.</h3>
                <p class="text-white-50 mb-4">You haven't made any purchases yet.</p>
                <a href="<c:url value='/shop' />" class="btn btn-outline-light btn-lg fw-bold">Browse the Shop</a>
            </div>
        </c:otherwise>
    </c:choose>

  </main>

  <%@ include file='include/footer.jsp'%>
</div>

<c:if test="${not empty sessionScope.toastMessage}">
  <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 1050;">
    <div id="successToast" class="toast show align-items-center text-white bg-success border-0" role="alert">
      <div class="d-flex">
        <div class="toast-body">${sessionScope.toastMessage}</div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" onclick="document.getElementById('successToast').classList.remove('show');"></button>
      </div>
    </div>
  </div>
  <c:remove var="toastMessage" scope="session" />
</c:if>

<script src="<c:url value='/index_files/bootstrap.bundle.min.js' />"></script>
</body>
</html>