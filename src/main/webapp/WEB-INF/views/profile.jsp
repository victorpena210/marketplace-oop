<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Your profile</title>
</head>
<body>

<c:if test="${empty sessionScope.currentUser}">
    <!-- In theory ProfileServlet already protects this, but just in case -->
    <p>You are not logged in. <a href="${pageContext.request.contextPath}/login">Login</a></p>
</c:if>

<c:if test="${not empty sessionScope.currentUser}">
    <h1>Hi, ${sessionScope.currentUser.fullName} 👋</h1>

    <h3>Account details</h3>
    <ul>
        <li><strong>ID:</strong> ${sessionScope.currentUser.id}</li>
        <li><strong>Email:</strong> ${sessionScope.currentUser.email}</li>
        <li><strong>Full name:</strong> ${sessionScope.currentUser.fullName}</li>
    </ul>

    <p>
        <a href="${pageContext.request.contextPath}/products">View products</a> |
        <a href="${pageContext.request.contextPath}/logout">Logout</a> |
        <a href="${pageContext.request.contextPath}/">Home</a>
    </p>
</c:if>

</body>
</html>
