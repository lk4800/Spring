/**
 * board.js
 */

function check() {
	//javascript 예약어 function으로 check()함수 정의
	if($.trim($('#writer').val())==''){
		//$는 jQuery란 뜻이고 trim()은 jQuery함수로 양쪽 공백 제거
		//val() jQuery함수로 입력박스 입력값을 가져옴 == 자바스크립트 같다 연산
		//if는 자바스크립트 조건문
		alert('글쓴이를 입력하세요')
		//alert는 자바스크립트 내장함수로써 경고메시지 창을 띄운다
		//자바와는 다르게 자바스크립트에서는 문자열을 ""또는 ''으로 처리한다
		$('#writer').val('').focus();
		//val('')은 입력박스 초기화, focus()는 자바스크립트 메서드로 입력박스 포커스 이동
		return false; //폼전송 안함
	}
	if($.trim($('#title').val())==''){
		alert('제목을 입력하세요')
		$('#title').val('').focus();
		return false;
	}
	if($.trim($('#content').val())==''){
		alert('내용을 입력하세요')
		$('#content').val('').focus();
		return false;
	}
	
}









