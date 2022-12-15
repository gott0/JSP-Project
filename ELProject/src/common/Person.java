package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 형식: 자바빈 클래스
// 역할: DOT 클래스

//롬복 단축기 : 컨르롤 + 쉽프트 + o
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
 	private String name;
 	private int age;
	
}
