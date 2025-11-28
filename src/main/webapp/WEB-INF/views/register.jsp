<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
	<title>Register</title>
</head>
<body>
<h1>User Registration</h1>

<c:if test="${not empty error}">
	<p style="color:red">${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/register" method="post">
	<div>
		<label for="fullName">Full name:</label>
		<input type ="text" id="fullName" name="fullName" value="${param.fullName}"/>
	</div>
	
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"
               value="${param.email}"/>
    </div>

    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"/>
    </div>

    <div style="margin-top: 10px;">
        <button type="submit">Create account</button>
    </div>
</form>

<p>
    <a href="${pageContext.request.contextPath}/">Back to home</a>
</p>

</body>
</html>