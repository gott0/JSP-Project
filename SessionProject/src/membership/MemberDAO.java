package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	//명시된 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDAO(String driver, String url, String id, String pwd) {
		super(driver, url, id, pwd);
	}

	//명시한 아이디/패스워드와  일치하는 회원 정보를 반환합니다.
	public MemberDTO getMemberDTO(String uid, String upass) {
		
		// 1. 조회할 데이터를 담을 객체 생성
		MemberDTO dto = new MemberDTO();
		
		// 2. 쿼리문 구성
		String query = "select * from member where id = ? and pass = ?";
		
		// 3. 쿼리문 실행
		try {
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, uid);  //쿼리문 첫번째 인파라미터("?")에 값을 설정
			psmt.setString(2, upass); //쿼리문 두번째 인파라미터("?")에 값을 설정
			rs = psmt.executeQuery();
			
			if(rs.next()) { //결과 처리
				//쿼리 결과로 얻은 회원정보를 DTO객체에 저장
				dto.setId(rs.getString("id")); 
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return dto; // DTO객체 반환
	}
	
}//class
