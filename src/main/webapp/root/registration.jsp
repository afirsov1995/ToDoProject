<%@ page import="service.root.RegistrationService" %>
<%@ page import="controller.root.RegistrationController" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
Hello dear anonymous, if you want to register - please fill out these fields
<form action="<%=request.getContextPath( )%>/root/registration" method="post">
    <c:if test="${not empty requestScope[RegistrationController.VALIDATION_ERROR_ATTRIBUTE]}">
        <c:out value="${requestScope[RegistrationController.VALIDATION_ERROR_ATTRIBUTE]}"/>
    </c:if>
    <p><strong>Login</strong></p>
    <p><input type="text" name="login" style="color: #777;" value="your login"
              onfocus="if (this.value == 'your login') {this.value = ''; this.style.color = '#000';}"
              onblur="if (this.value == '') {this.value = 'your login'; this.style.color = '#777';}"/></p>
    <p><strong>Password</strong></p>
    <p><input type="password" name="password" style="color: #777;" value="your password"
              onfocus="if (this.value == 'your password') {this.value = ''; this.style.color = '#000';}"
              onblur="if (this.value == '') {this.value = 'your password'; this.style.color = '#777';}"></p>
    <p><strong>FIO</strong></p>
    <p><input type="text" name="fio" style="color: #777;" value="your fio"
              onfocus="if (this.value == 'your fio') {this.value = ''; this.style.color = '#000';}"
              onblur="if (this.value == '') {this.value = 'your fio'; this.style.color = '#777';}"/></p>
    <p><input type="submit" name="register" value="To Register"></p>
</form>
</body>
</html>