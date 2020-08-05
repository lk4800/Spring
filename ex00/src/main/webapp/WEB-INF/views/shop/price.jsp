<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
상품명 : ${p.name}<hr/>
<%-- p.name을 자바코드로 표현하면 p.getName()과 같은 기능역할을 한다 --%>
상품가격 : ${p.price}<hr/>
</body>
</html>