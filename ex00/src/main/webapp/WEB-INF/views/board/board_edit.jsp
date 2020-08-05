<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 수정</title>
<script src="../resources/js/jquery.js"></script>
<%--jQuery라이브러리 읽어오기 => 자바스크립트가 들어가는 경로는 src/main/webapp/resources이다
resources폴더 하위에 js폴더를 만든다 --%>
<script src="../resources/js/board.js"></script>
<%--유효성 검증 자바스크립트 & jQuery  --%>
</head>
<body>
 <form method="post" action="board_edit_ok" onsubmit="return check();">
 <%--히든으로 번호값과 페이지 번호(쪽번호)를 전달 --%>
 <input type="hidden" name="bno" value="${b.bno}"/>
 <input type="hidden" name="page" value="${page}"/>
 <%--히든으로 쪽번호를 전달하는 이유는 내가본 페이지 번호 즉 쪽번호를 이동하기 위한 책갈피 기능때문이다.--%>
  <table border="1">
   <tr>
    <th colspan="2">스프링 MVC 게시판 수정</th>
   </tr>
   <tr>
    <th>글쓴이</th>
    <td><input type="text" name="writer" id="writer" size="14" value="${b.writer}"/></td>
   </tr><%--name파라미터 이름인 name에 입력한 글쓴이가 저장되어 전달된다 --%>
   <tr>
    <th>제목</th>
    <td><input name="title" id="title" size="36" value="${b.title}"/></td>
    <%--type속성을 생략하면 기본값이 text이다. 
    	폼태그에서 method속성을 생략하면 기본값인 get방식이다 size속성은 입력박스 길이이다 --%>
   </tr>
   <tr>
    <th>내용</th>
    <td><textarea name="content" id="content" rows="10" cols="36">${b.content}</textarea></td>
    					<%-- textarea는 value속성이 없다 --%>
    <%--textarea는 한줄이상 입력할 수 있는 입력박스를 만든다 
    	네임파라미터 이름과 BoardVO.java 변수명은 반드시 일치시켜야 한다 
    	rows는 행, cols는 열--%>
   </tr>
   <tr>
    <th colspan="2">
    <input type="submit" value="저장"/>
    <%--submit은 버튼을 만들고 자료를 서버로 전송. type="image" 이미지 버튼을 만들고, 자료도 전송한다 --%>
    <input type="reset" value="취소" onclick="$('#writer').focus();"/>
    <%--reset은 버튼을 만들고 입력박스를 초기화 onclick은 클릭이벤트를 처리하는 이벤트 속성으로 
    	이벤트 핸들러(사건처리기) 라고 한다 글쓴이 입력박스로 포커스 이동(focus());은 생략해도 된다 --%>
    <input type="button" value="목록" onclick="location='/controller/board/board_list?page=${page}'"/>
   </tr>
  </table>
  </form>
</body>
</html>