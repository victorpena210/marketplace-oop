<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
<title>Products</title>
</head>
<body>
<h3>seeded products</h3>

    <c:if test="${empty products}">
        <p>No products found in the database.</p>
    </c:if>

    <c:if test="${not empty products}">
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price ($)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.category}</td>
                    <td>${p.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    
        <p>
        <a href="${pageContext.request.contextPath}/">Back to home</a>
    	</p>
</body>
</html>