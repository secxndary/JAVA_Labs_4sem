<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2><%= "Sign in" %></h2>
    <form method="post" action="user-servlet">
        Login <input type="text" name="login"/> <br/> <br/>
        Password <input type="text" name="password"/> <br/> <br/>
        <input type="submit" value="Войти"/>
    </form>
</body>
</html>
