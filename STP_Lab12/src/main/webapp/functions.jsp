<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Functions</title>
</head>
<body>
<h1>FUNCTIONS TAGS</h1>
<c:set var="string" value="I love java"/>


<c:out value="Input string: "/><c:out value="${string}"/>
<br/><br/>


<c:if test="${fn:contains(string, 'java')}">
    String contains 'java'
</c:if>
<br/>


<c:if test = "${fn:containsIgnoreCase(string, 'h')}">
    String contains 'h'
</c:if>
<br/>


<c:if test = "${fn:endsWith(string, 'a')}">
    String ends with 'a'
</c:if>
<br/><br/>


<c:set var = "string1" value = "I love studiyng Java."/>
<c:set var = "string2" value = "I actually dont like studiyng Java."/>
Index of 'java': ${fn:indexOf(string1, "Java")}<br/>
Index of 'dont': ${fn:indexOf(string2, "dont")}<br/>

<c:set var = "string1" value = "I love studiyng Java."/>
<c:set var = "string2" value = "${fn:split(string1, ' ')}" />
<c:set var = "string3" value = "${fn:join(string2, '-')}" />
${string3}<br/><br/>

<c:set var = "string1" value = "I love studiyng Java."/>
Length of this string: ${fn:length(string1)}<br/><br/>

<c:set var = "string1" value = "I love studiyng Java."/>
<c:set var = "string2" value = "${fn:replace(string1, 'Java', 'C#')}" />
${string2}<br>
</body>
</html>
