<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--JSTL 코어 태그립 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
 <h1>Access Denied Page</h1>
 <h2><c:out value="${msg}"/></h2>
 <%--c:out은 jstl에서 출력문  --%>
</body>
</html>