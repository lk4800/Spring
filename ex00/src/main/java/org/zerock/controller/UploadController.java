package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@GetMapping("uploadForm") //get으로 접근하는 매핑주소 처리. 
	//uploadForm 매핑주소 처리
	public String uploadForm() {
		return "uploadForm";//뷰페이지 경로=> /WEB-INF/views/uploadFor
				//m.jsp
	}//uploadForm()
	
	@PostMapping("uploadFormAction") //uploadFormAction 매핑주소등록
	//post로 접근하는 매핑주소 처리
	public void uploadFormAction(MultipartFile[] uploadFile) {
/* 스프링 API에서 MultipartFile 타입을 제공해서 업로드 되는 파일 데이터를 쉽게 처리.
 * 다중 업로드 파일은 배열로 받는다. <input type="file" name="uploadFile"
 *   네임피라미터이름을 매개변수명으로 지정해서 처리한다. 네임피라미터 이름과 매개변수
 *   명을 같게 한다.		
 */
		String uploadFolder = "C:\\upload";//업로드 서버 경로
		
		for(MultipartFile multipartFile : uploadFile) {
			System.out.println("-------------------------");
System.out.println("Upload file name : "+
			multipartFile.getOriginalFilename());//업로드 원본 파일명
System.out.println("Upload file size :"+
			multipartFile.getSize());//업로드 파일 크기

            File saveFile=new File(uploadFolder,
            		multipartFile.getOriginalFilename());
//파일과 폴더를 다루는 File클래스 생성자 인자값으로 업로드 서버경로와 첨부된 원본파일명
//을 가지고 File클래스 객체 생성
            
            try {
             multipartFile.transferTo(saveFile);//업로드 된 원래 파일명
//으로 c드라이브  upload폴더에 업로드 함.             
            }catch(Exception e) {e.printStackTrace();}
		}//자바 5버전이후부터 배열이나 컬렉션의 복수개의 데이터를 쉽게 처리하기위해서
		//향상된 확장 for반복문을 사용한다.
	}//uploadFormAction()
	
	//비동기식 아작스 뷰페이지
	@GetMapping("/uploadAjax") 
	//get방식으로 접근하는 매핑주소 처리=> uploadAjax 매핑주소 등록
	public void uploadAjax() {
		//리턴타입이 없는 void형이면 매핑주소 uploadAjax가 뷰페이지 파일명이 된다
		//뷰페이지 경로 => /WEB-INF/views/updatAjax.jsp
	}//uploadAjax()
	
	@PostMapping("/uploadAjaxAction")
	//post방식으로 접근하는 매핑주소 처리
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		System.out.println("uptade ajax post...");
		String uploadFolder = "C://upload";
		
		for(MultipartFile multipartFile:uploadFile) {
			System.out.println("------------------");
			System.out.println("upload File Name: "+multipartFile.getOriginalFilename());
			System.out.println("upload File Size: "+multipartFile.getSize());
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			/*IE인 경우는 첨부한 전체 파일 경로를 반환한다
			 *그러므로 마지막 경로 구분 \ 를 맨 오른쪽부터 찾아서 가장먼저 나오는 \ 의
			 *위치번호를 맨 왼쪽부터 0부터 시작해서 위치번호값을 반환한다.
			 *여기에 +1을 하면 결국 마지막 경로 이후 첨부한 파일명을 구함
			 *=>첨부한 파일경로가 C:\downloads\a.jpg라고 한다면
			 *마지막 경로\이후 첨부한 이진파일명 a.jpg를 구함 
			 */
			System.out.println("only file name: "+uploadFileName);
			File saveFile = new File(uploadFolder, uploadFileName);
			//생성자 인자값으로 업로드 서버경로, 첨부파일명을 가진 파일객체 saveFile을 생성
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {e.printStackTrace();}

		}//확장 for
	}//uploadAjaxAction()
}

















