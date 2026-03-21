<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>

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
    </style>
</head>

<body class="d-flex min-vh-100 text-center text-white bg-dark">
    
<div class="cover-container d-flex w-100 min-vh-100 p-3 mx-auto flex-column">
  
  <c:set var="pageTitle" value="Login" scope="request" />
  <%@ include file='include/header.jsp'%>

  <main class="px-3 mb-5">
    <h1>Login</h1>
    
    <form action="<c:url value='/login' />" method="POST" class="text-start mt-4">
        
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-lg btn-secondary fw-bold border-white bg-white w-100 mt-4">
            Log In
        </button>
    </form>
    
    <p class="mt-3 text-white-50">
        Don't have an account? <a href="<c:url value='/register' />" class="text-white">Register here</a>.
    </p>

  </main>

  <%@ include file='include/footer.jsp'%>
</div>

</body>
</html>