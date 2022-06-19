<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Formatting</title>
</head>
<body>
<h1>FORMAT TAGS</h1>

<h3>Форматирование даты\времени</h3>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="en-EN"/>
Вывод даты в формате English<br/>
Сегодня: <fmt:formatDate value="${now}" /><br/>
<fmt:setLocale value="ru-RU"/>
<fmt:timeZone value="GMT+3:00">
    Вывод даты в формате Russian<br/>
    Сегодня: <fmt:formatDate value="${now}"  type="both"
                             dateStyle="full" timeStyle="full"/> <br/>
</fmt:timeZone>
<c:set var="currentDate" value="12-05-2006" />
<fmt:parseDate value="${currentDate}" var="parsedCurrentDate" pattern="dd-MM-yyyy" />
Парсинг строковой даты: <c:out value="${parsedCurrentDate}" />
<br/>
Стиль времени:
<br/>
(short): <fmt:formatDate value="${now}"
                         type="time" timeStyle="short" /><br/>
(medium):<fmt:formatDate value="${now}"
                         type="time" timeStyle="medium" /><br/>
(long): <fmt:formatDate value="${now}"
                        type="time" timeStyle="long" /><br/><br/>




<h3>Форматирование чисел</h3>
<c:set var="currentNumber" value="322"/>
<c:out value="Вывод формата числа : ${currentNumber}"/> <br/>
Формат (по умолчанию) :
<fmt:formatNumber value="${currentNumber}" /><br/>
Процентный формат :
<fmt:formatNumber value="${currentNumber}"
                  type="percent"/><br/>
<fmt:setLocale value="be-BY"/>
Белорусские рубли :
<fmt:formatNumber value="${currentNumber}"
                  type="currency"/><br/>
Французская валюта :
<fmt:setLocale value="fr-FR"/>
<fmt:formatNumber value= "${currentNumber}" type="currency"/><br/>



</body>
</html>
