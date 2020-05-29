<%--
  Created by IntelliJ IDEA.
  User: Артемиус
  Date: 27.04.2020
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="controller.user.TaskEditController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Edit</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<form action="<%=request.getContextPath()%>/user/taskEdit" method="post">
    Edit task
    <c:if test="${not empty requestScope[TaskEditController.VALIDATION_ERROR_ATTRIBUTE]}">
        <p><c:out value="${requestScope[TaskEditController.VALIDATION_ERROR_ATTRIBUTE]}"/></p>
    </c:if>
    <table border="1">
        <tr>
            <td>
                Task number
            </td>
            <td>
                <c:out value="${task.getId()}"/>
            </td>
            <td>
                <p><input type="text" value="Immutable" readonly></p>
            </td>
        </tr>
        <tr>
            <td>
                Task created
            </td>
            <td>
                <c:out value="${task.getCreated().toLocaleString()}"/>
            </td>
            <td>
                <p><input type="text" value="Immutable" readonly></p>
            </td>
        </tr>
        <tr>
            <td>
                Task is done
            </td>
            <td>
                <c:out value="${task.getIsDone()}"/>
            </td>
            <td>
                <p><input type="text" value="Immutable" readonly></p>
            </td>
        </tr>
        <tr>
            <td>
                Task description
            </td>
            <td>
                <c:out value="${task.getDescription()}"/>
            </td>
            <td>
                <p><input type="text" name="description"></p>
            </td>
        </tr>
        <tr>
            <td>
                Task deadline
            </td>
            <td>
                <c:out value="${task.getDeadline()}"/>
            </td>
            <td>
                Format: yyyy-mm-dd hh:mm:ss
                <p><input type="text" name="deadline"></p>
            </td>
        </tr>
    </table>
    <p><input type="hidden" name="taskId" value="${task.getId()}"></p>
    <p><input type="submit" name="edit" value="Edit"></p>
</form>
</body>
</html>
