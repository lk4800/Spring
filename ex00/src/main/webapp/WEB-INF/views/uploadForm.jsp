<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 파일첨부 실습 </title>
</head>
<body>
 <form method="post" action="uploadFormAction" 
 enctype="multipart/form-data">
  <%--
   1.파일을 첨부해서 서버에 업로드 할려면 method="post"방식으로 지정해야 한다. get
   은 안된다. method속성을 생략하면 기본값이 get이기 때문에 생략하면 안된다.
   2.첨부한 파일을 이진파일이라고 한다. 이 이진파일을 서버에 업로드 할려면 반드시
   enctype="multipart/form-data"를 지정해야 한다.
   --%>
   <input type="file" name="uploadFile" multiple />
<%-- type="file"은 파일 첨부기능을 만들어 준다. multiple속성을 지원하는데 이를
이용하면 하나의 input type="file"로 다중 이진파일을 동시에 서버에 업로드 할수 있다.
이 속성은 IE10 이상에서만 사용할 수 있다. --%>   
   <input type="submit" value="파일업로드" />   
 </form>
</body>
</html>







