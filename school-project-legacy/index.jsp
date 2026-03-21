<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="h-100">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>books or smth</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">

    

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/index_files/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/index_files/cover.css' />" rel="stylesheet">
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="https://getbootstrap.com/docs/5.0/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">

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
    </style>

    
    <!-- Custom styles for this template -->
    <link href="index_files/cover.css" rel="stylesheet">
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
    
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  <c:set var="pageTitle" value="Home" scope="request" />
  <%@ include file='include/header.jsp'%>

  <main class="px-3">
    <h1>books i guess</h1>
    <p class="lead">get textbooks or smth</p>
    <p class="lead">
      <a href="<c:url value='/shop' />" class="btn btn-lg btn-secondary fw-bold border-white bg-white">Shop Now</a>
    </p>
  </main>

  <%@ include file='include/footer.jsp'%>
</div>

<c:if test="${not empty sessionScope.toastMessage}">
  <div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 1050;">
    <div id="successToast" class="toast show align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
            ${sessionScope.toastMessage}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" onclick="document.getElementById('successToast').classList.remove('show');" aria-label="Close"></button>
      </div>
    </div>
  </div>

  <script>
    setTimeout(function() {
      var toast = document.getElementById('successToast');
        if (toast) { 
          toast.classList.remove('show'); 
        }
      }, 3000);
  </script>
  <c:remove var="toastMessage" scope="session" />
</c:if>    
  

</body>
</html>