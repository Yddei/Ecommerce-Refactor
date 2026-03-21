<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<header class="mb-auto">
  <div>
    <h3 class="float-md-start mb-0">books or smth</h3>
    <nav class="nav nav-masthead justify-content-center float-md-end">
      <a class="nav-link fw-bold py-1 px-0 ${pageTitle == 'Home' ? 'active' : ''}" aria-current="page" href="<c:url value='/home' />">Home</a>
      <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
          <a class="nav-link fw-bold py-1 px-0 ms-3 ${pageTitle == 'Orders' ? 'active' : ''}" href="<c:url value='/orders' />" title="View Order History">
            ${sessionScope.loggedInUser}'s Orders
          </a>
            
          <a class="nav-link fw-bold py-1 px-0 ms-3" href="<c:url value='/logout' />">Logout</a>
        </c:when>
        
        <c:otherwise>
          <a class="nav-link fw-bold py-1 px-0 ms-3 ${pageTitle == 'Login' ? 'active' : ''}" href="<c:url value='/login' />">Login</a>
          <a class="nav-link fw-bold py-1 px-0 ms-3 ${pageTitle == 'Register' ? 'active' : ''}" href="<c:url value='/register' />">Register</a>
        </c:otherwise>
      </c:choose>
    </nav>
  </div>
</header>