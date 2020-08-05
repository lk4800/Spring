<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 아작스로 파일첨부 기능 만들기</title>
<script src="./resources/js/jquery.js"></script>
<script>
 $(document).ready(function() {
	//문서가 준비되어있으면
	$('#uploadBtn').on("click",function(e){
		//버틀 클릭시 실행
		var formData = new FormData();
/* 1.첨부파일을 업로드 하는 또 다른 방식은 jQuery 아작스를 이용해서 파일 데이터만을
 	 전송한다
   2.아작스를 이용하는 첨부파일 처리는 FormData라는 객체를 이용하는데 
   	IE의 웹 브라우저인 경우는 10이후 버전부터 지원되므로 브라우저의 제약이 있을 수 있다. */
		var inputFile = $("input[name='uploadFile']");
		//input 태그 네임 파아미터 이름이  uploadFile인 경우에 접근
		var files=inputFile[0].files; //inputFile[0]은 첫번째 input type="file"을 가르킨다
		/*다중 첨부한 파일은 files 배열로 받는다
		  자바스크립트에서는 엄격한 형검사를 하지 않아서 var변수 정의 키워드로 한개값을
		  저장하는 변수도 사용할 수 있고, 복수개의 값을 저장하는 배열도 사용할 수 있다.*/
		console.log(files); //콘솔모드 출력
		
		//첨부파일을 formData객체에 추가
		for(var i=0; i<files.length; i++){
			formData.append("uploadFile",files[i]);
		} //for
		$.ajax({
			url : "/controller/uploadAjaxAction", //서버 매핑주소
			processData:false, //데이터를 컨텐트 타입에 맞게 변환 => false로 지정해야 전송
			contentType:false, //컨텐터 타입 지정
			data:formData, //formData 객체를 전송=>formData에 첨부한 파일이 있다
			type:'POST', //보내는 방식
			success:function(result){
				//받아오는 것이 성공시 호출되는 콜백 함수
				alert('Uploaded OK!');
			}
		})
	})
})
</script>
</head>
<body>
 <h1>Upload with Ajax</h1>
 <input type="file" name="uploadFile" multiple/>
 <hr/>
 <button id="uploadBtn">Upload</button>
</body>
</html>