<%@ page import="controller.root.RegistrationController" %>
<%@ page import="controller.root.LoginController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<form action="<%=request.getContextPath( )%>/root/login" method="post">
    <c:if test="${not empty requestScope[LoginController.VALIDATION_ERROR_ATTRIBUTE]}">
        <c:out value="${requestScope[LoginController.VALIDATION_ERROR_ATTRIBUTE]}"/>
    </c:if>
    <p><strong>Login</strong></p>
    <p><input type="text" name="login" style="color: #777;" value="your login"
              onfocus="if (this.value == 'your login') {this.value = ''; this.style.color = '#000';}"
              onblur="if (this.value == '') {this.value = 'your login'; this.style.color = '#777';}"/></p>
    <p><strong>Password</strong></p>
    <p><input type="password" name="password" style="color: #777;" value="your password"
              onfocus="if (this.value == 'your password') {this.value = ''; this.style.color = '#000';}"
              onblur="if (this.value == '') {this.value = 'your password'; this.style.color = '#777';}"></p>
    <p><input type="submit" name="login" value="Login"></p>
</form>
</body>
</html>
