<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shop - Books or Smth</title>

    <link href="<c:url value='/index_files/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/index_files/cover.css' />" rel="stylesheet">

    <style>
      .bd-placeholder-img { font-size: 1.125rem; text-anchor: middle; user-select: none; }
      @media (min-width: 768px) { .bd-placeholder-img-lg { font-size: 3.5rem; } }
      .card-img-top { height: 250px; object-fit: cover; }
      .cover-container { max-width: 900px !important; }
    </style>
</head>

<body class="d-flex min-vh-100 text-center text-white bg-dark">

<!--view cart mini icon that says total number of books-->
<c:set var="cartSize" value="0" />
<c:forEach var="book" items="${books}">
    <c:set var="qty" value="${sessionScope.cart[book.id]}" />
    <c:if test="${not empty qty}">
        <c:set var="cartSize" value="${cartSize + qty}" />
    </c:if>
</c:forEach>

<div class="cover-container d-flex w-100 min-vh-100 p-3 mx-auto flex-column">
    
    <c:set var="pageTitle" value="Shop" scope="request" />
    <%@ include file='include/header.jsp'%>

    <main class="px-3 mb-5">
    
        <h1 class="fw-bold mb-5">Shop</h1>
        
        <a href="<c:url value='/cart' />" class="btn btn-outline-light position-relative">
            View Cart
            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger ${cartSize == 0 ? 'd-none' : ''}">
                ${cartSize}
            </span>
        </a>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4 text-start mt-4">
            <c:forEach var="book" items="${books}">
                <div class="col">
                    <div class="card bg-dark text-light border-secondary h-100 shadow">
                        <img src="<c:url value='/images/${book.img}' />" class="card-img-top" alt="${book.title} cover">
                        
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title fw-bold">${book.title}</h5>
                            <h6 class="card-subtitle mb-3 text-white-50">${book.author}</h6>
                            
                            <div class="mt-auto">
                                <p class="fs-5 text-success mb-1">$${book.price}</p>
                                <p class="small ${book.qty > 0 ? 'text-white-50' : 'text-danger'}">
                                    ${book.qty > 0 ? 'In Stock: ' += book.qty : 'Out of Stock'}
                                </p>
                                
                                <form action="<c:url value='/AddToCart' />" method="POST" class="m-0">
                                    <input type="hidden" name="bookId" value="${book.id}">
                                    <button type="submit" class="btn btn-outline-light w-100 btn-sm" ${book.qty == 0 ? 'disabled' : ''}>
                                        ${book.qty == 0 ? 'Unavailable' : 'Add to Cart'}
                                    </button>
                                </form>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty books}">
            <p class="lead mt-4 text-warning">No books rn</p>
        </c:if>
    </main>

    <%@ include file='include/footer.jsp'%>
</div>

<!--bottom right green thing for UX-->
<c:if test="${not empty sessionScope.toastMessage}">
  <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 1050;">
    <div id="successToast" class="toast show align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
            ${sessionScope.toastMessage}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" onclick="document.getElementById('successToast').classList.remove('show');"></button>
      </div>
    </div>
  </div>
  <c:remove var="toastMessage" scope="session" />
</c:if>

<script src="<c:url value='/index_files/bootstrap.bundle.min.js' />"></script>

</body>
</html>