package fileupload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyFileDTO {

	private String idx;
	private String name;    	//작성자 이름
	private String title;		//제목
	private String cate;		//카테고리
	private String ofile;	 	//원본파일명
	private String sfile;		//저장된 파일명
	private String postdate;  	//등록날짜
	
	
	
	
}//c
