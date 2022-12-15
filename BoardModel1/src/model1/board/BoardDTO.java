package model1.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO { // DTO: 계층 간 데이터 교환을 하기 위해 사용하는 객체로, 
	                    //      DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)
	
	private String num;
	private String title;
	private String content;
	private String id;
	private java.sql.Date postdate;
	private String visitcount;
	private String name;

	
}
