package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;


public class FileUtil {

	//파일 업로드(multipart/form-data 요청) 처리
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		try {
			
			//파일 업로드				
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8"); // 인코딩 방식을 "UTF-8"로 지정해줬다
	
		}catch(Exception e) {
			
			//업로드 실패
			e.printStackTrace();
			
			return null;
		}
	} //컨트롤러에서 글쓰기 파일 첨부 시

	
	
	//명시한 파일을 찾아 다운로드합니다
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName ) {
		
		String saveDirectory = req.getServletContext().getRealPath(directory); // 다운로드 받을 파일이 있는 주소
		
		try {
			//파일을 찾아 입력 스트림 생성
			File file = new File(saveDirectory,sfileName);
			InputStream inStream = new FileInputStream(file);
			
			//한글 파일명 깨짐 방지
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1){
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			}else{
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			} // (한글에 대한 파일 이름을 처리하는 공식)
			
			//파일 다운로드용 응답 헤더 설정(다운로드 시 다운로드창의 정보를 보여주는 역할)
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length());
	
			//out.clear();
			
			//response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream outStream = resp.getOutputStream();
			
			//출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = inStream.read(b)) > 0){
				outStream.write(b,0,readBuffer);
			}
			
			// 입/출력 스트림 닫음
			inStream.close();
			outStream.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
		
	}

	
	//파일을 삭제하는 기능
	public static void deleteFile(HttpServletRequest req, String directory, String filename) { 
		
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	
	
	}
	
	
}//c
