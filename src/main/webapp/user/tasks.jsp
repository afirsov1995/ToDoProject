<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
WELCOME TO YOUR HOME, DEAR <c:out value="${login.getFio()}"/>!
<c:if test="${not empty validationError}">
    <p><c:out value="${validationError}"/></p>
</c:if>
<table border="1">
    <tr>
        <td>
            Task number
        </td>
        <td>
            Task Created
        </td>
        <td>
            Task is done
        </td>
        <td>
            Task description
        </td>
        <td>
            Task deadline
        </td>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>
                <c:out value="${task.getId()}"/>
            </td>
            <td>
                <c:out value="${task.getCreated()}"/>
            </td>
            <td bgcolor="aqua">
                <c:out value="${task.getIsDone()}"/>
            </td>
            <td>
                <c:out value="${task.getDescription()}"/>
            </td>
            <td>
                <c:out value="${task.getDeadline()}"/>
            </td>
            <td>
                <c:if test="${task.getIsDone() == false}">
                    <form action="<%=request.getContextPath()%>/user/taskComplete" method="post">
                        <p><input type="hidden" name="taskId" value="${task.getId()}"></p>
                        <p><input type="submit" name="complete" value="Complete"></p>
                    </form>
                </c:if>
            </td>
            <td>
                <c:if test="${task.getIsDone() == false}">
                    <form action="<%=request.getContextPath()%>/user/taskEdit" method="get">
                        <p><input type="hidden" name="taskId" value="${task.getId()}"></p>
                        <p><input type="submit" name="edit" value="Edit"></p>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    <form action="<%=request.getContextPath()%>/user/taskAdd" method="post">
    <tr>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
            <p><input type="text" name="description"></p>
        </td>
        <td>
            <p><input type="text" name="deadline"></p>
        </td>
        <td>
            <p><input type="submit" name="add" value="Add task"></p>
        </td>
    </tr>
    </form>
</table>
<form action="<%=request.getContextPath()%>/user/tasks" method="post">
    <input type="button" onclick="window.location.href = '/ToDoProject_war/user/logout';" name="logout"
           value="Logout"></p>
</form>
</body>
</html>
