<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email"/>
    </div>

    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"/>
    </div>

    <div style="margin-top: 10px;">
        <button type="submit">Sign in</button>
    </div>
</form>

<p>
    <a href="${pageContext.request.contextPath}/register">Create a new account</a><br/>
    <a href="${pageContext.request.contextPath}/">Back to home</a>
</p>

</body>
</html>
