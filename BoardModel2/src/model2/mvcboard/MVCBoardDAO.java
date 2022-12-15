package model2.mvcboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class MVCBoardDAO extends DBConnPool{ //모델

	public int selectCount(Map<String,Object> map) {
		
		int totalCount = 0;
		String query = "select count(*) from mvcboard";
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " " 
					+ " like '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	
	
	public List<MVCBoardDTO> selectListPage(Map<String,Object> map){
	      
	      List<MVCBoardDTO> board = new ArrayList<>();
	      
	      String query = " "
	                + " SELECT * FROM ( "
	                + " SELECT Tb.*, ROWNUM rNum FROM ( "
	                + " SELECT * FROM mvcboard ";

	      if (map.get("searchWord") != null)
	      {
	          query += " WHERE " + map.get("searchField")
	                 + " LIKE '%" + map.get("searchWord") + "%' ";
	      }
	   
	      query += " ORDER BY idx DESC "
	             + " ) Tb "
	             + " ) "
	             + " WHERE rNum BETWEEN ? AND ?";
	               
	      try {
	          psmt = con.prepareStatement(query);
	          psmt.setString(1, map.get("start").toString());
	          psmt.setString(2, map.get("end").toString());
	          rs = psmt.executeQuery();
	   
	          while (rs.next()) {
	              MVCBoardDTO dto = new MVCBoardDTO();
	   
	              dto.setIdx(rs.getString(1));
	              dto.setName(rs.getString(2));
	              dto.setTitle(rs.getString(3));
	              dto.setContent(rs.getString(4));
	              dto.setPostdate(rs.getDate(5));
	              dto.setOfile(rs.getString(6));
	              dto.setSfile(rs.getString(7));
	              dto.setDowncount(rs.getInt(8));
	              dto.setPass(rs.getString(9));
	              dto.setVisitcount(rs.getInt(10));
	   
	              board.add(dto);
	          }
	      }
	      catch (Exception e) {
	          System.out.println("게시물 조회 중 예외 발생");
	          e.printStackTrace();
	      }
	      return board;
	   }

	
	//게시글 데이터를 받아 DB에 추가합니다.(파일 업로드 지원)
	public int insertWrite(MVCBoardDTO dto) {		
		
		int result =0;
		
		String query = "insert into mvcboard(idx,name,title,content,ofile,sfile,pass)"
				+ " values(seq_board_num.nextval,?,?,?,?,?,?)";
				 
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());

			result = psmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	//주어진 일련번호에 해당하는 게시물의 조회수를 1 증사시킵니다.
	public void updateVisitCount(String idx) {
		
		String query = "update mvcboard"
				+" set visitCount = visitcount + 1" 
				+ " where idx = ?";
	
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	
	//주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환합니다.
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO(); //DTO객체 생성
		String query = "select * from mvcboard where idx = ?"; //쿼리문 템플릿 준비
		
		try {
			
			psmt = con.prepareStatement(query);  //쿼리문 준비
			psmt.setString(1, idx);   //인파라미터 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			if(rs.next()) { //결과를 DTO객체에 저장
				
				  dto.setIdx(rs.getString(1));
				  dto.setName(rs.getString(2));
				  dto.setTitle(rs.getString(3));
				  dto.setContent(rs.getString(4));
				  dto.setPostdate(rs.getDate(5));
				  dto.setOfile(rs.getString(6));
				  dto.setSfile(rs.getString(7));
				  dto.setDowncount(rs.getInt(8));
				  dto.setPass(rs.getString(9));
				  dto.setVisitcount(rs.getInt(10));
								
			}
		}catch (SQLException e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;  //결과 반환
	}
	
	
	
	//다운로드 횟수를 1 증가시킴
	public void downCountplus(int idx) { //String idx로 진행해도 됨
		
		String query = "update mvcboard"
				+ " set downcount = downcount + 1"
				+ " where idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, idx);   // 숫자 타입의 컬럼이 업데이트 되므로 setString이 아닌 setLong으로~
			psmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("게시물 다운로드 중 예외 발생");
			e.printStackTrace();
		}	
	}
	
	
	
	// 수정,삭제 시 글번호와 비밀번호 매칭하여 확인
	public boolean confirmPassword(String pass, String idx) {
		
		boolean isCorr = true;
		
		String query = "select count(*) from mvcboard where pass =? and idx= ?"; //패스워드와 글번호가 일치할 경우는 하나의 경우 밖에 없기 때문에 [idx(글번호)가 유일하기 때문]
																				 //결국 옳은 패스워드를 count해서 나온 값은 1(하나)뿐이다.
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			
			rs.next(); //결과값이
			if(rs.getInt(1) == 0) { // 첫번째 컬럼[rs.getInt(1)]의 값이 결국 pass와 mode가 일치하는 경우의 카운트 값인데 이게 0일경우 false를 반환(값이 1이여야 true) 
				isCorr = false;		//  [ getInt(1) = 1번 컬럼(count)을 가르킴 ]
			}
		}catch (SQLException e) { //쿼리문에 오류가 있을 경우 false를 반환
			isCorr = false;
			e.printStackTrace();
		}	
		
		return isCorr;
	}
	
	
	public int deletePost(String idx) { // 글 삭제 메소드
		int result = 0;
		
		String query = "delete from mvcboard where idx = ?";
				
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			
		}catch (SQLException e) { 
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}			
		return result;
	}
	
	//게시물 수정 메소드
	public int updatePost(MVCBoardDTO dto) {
		int result =0;
		
		String query = "update mvcboard"
				+ " set title=?, name=?, content=?, ofile=?, sfile=?"
				+ " where idx=? and pass=?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			result = psmt.executeUpdate();
			
		}catch (SQLException e) { 
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}			
		
		
		return result;
	}
	
	
}//c
