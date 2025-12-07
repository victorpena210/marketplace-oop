<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Products</title>
</head>
<body>

<h1>Products</h1>

<p>
    <a href="${pageContext.request.contextPath}/">Home</a> |
    <a href="${pageContext.request.contextPath}/cart">View cart</a>
    <c:if test="${not empty sessionScope.cartCount}">
        (Items: ${sessionScope.cartCount})
    </c:if>
</p>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add to cart</th>
    </tr>

    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cart/add" method="post">
                    <input type="hidden" name="productId" value="${p.id}" />
                    <input type="number" name="qty" value="1" min="1" style="width: 50px;" />
                    <button type="submit">Add</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
