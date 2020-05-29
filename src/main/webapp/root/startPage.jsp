<html>
<head>
    <title>Start page</title>
</head>
<body>
Hello dear user
<form action="<%=request.getContextPath( )%>/root/startPage" method="get">
    <p><input type="button" onclick="window.location.href = '/ToDoProject_war/root/login';" name="login" value="Login">
        <input type="button" onclick="window.location.href = '/ToDoProject_war/root/registration';" name="registration"
               value="Registration"></p>
</form>
</body>
</html>
