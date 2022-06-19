<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Основная страница</title>
    <link href="mainStyle.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <div1>
        <jsp:include page="header.jsp"/>
        <c:catch var="Exception">
            <c:set var="currentUser" value="${user}" scope="page"/>
            <c:if test="${not empty currentUser and currentUser eq 'user'}">
                Your role: user
            </c:if>
        </c:catch>
        <br/>
    </div1>
</div>
<form>
    <a href="register.jsp">Регистрация</a>
    <a href="welcome.jsp">Авторизация</a>
</form>
<center>
    <div1 >
        <h3 style="color: red">${error}</h3>
        <table border="2" cellpadding="4">
            <thead>
            <tr>
                <th> Название нации </th>
                <th> Численость (млн чел.) </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="nation" items="${nations}">
                <tr>
                    <td>${nation.getName()}</td>
                    <td>${nation.getNumber()}</td>
                </tr>
            </c:forEach>
            <br>
            </tbody>

        </table>
    </div1>


    <fieldset style="width: 30%; margin: 2%">
        <legend>Добавить</legend>
        <form action="${pageContext.request.contextPath}/controller?command=addNation" method="post"   >
            <h3>Название народа:</h3>
            <input type="text" name="name"/>
            <h3>Численность в миллионах:</h3>
            <input type="text" name="country"/>
            <br/><br/>
            <input type="submit" value="Добавить" ><br/>
        </form>
    </fieldset>


    <fieldset style="width: 30%; margin: 2%">
        <legend>Удалить</legend>
        <form action="${pageContext.request.contextPath}/controller?command=delNation" method="post" >
            <h3>Удалить по названию:</h3>
            <input type="text" name="namedelet"/>
            <input type="submit" value="Удалить" ><br/>
        </form>
    </fieldset>


    <fieldset style="width: 30%; margin: 2%">
        <legend>Изменить</legend>
        <form action="${pageContext.request.contextPath}/controller?command=updNation" method="POST">
            <h3>Название народа:</h3>
            <input name="name" type="text"/> <br/>
            <h3>Измените его численность:</h3>
            <input name="number" type="text" />
            <br/><br/>
            <input type="submit" value="Изменить"/>
        </form>
    </fieldset>
</center>
<%--<jsp:include page="footer.jsp" />--%>
</body>
</html>