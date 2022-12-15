package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import common.JDBConnect;
import common.JDBConnect;

public class BoardDAO extends JDBConnect{ //DB생성자를 호출해 DB와 연결
	// DAO : DataBase에 접근 하기 위한 로직 & 비지니스 로직을 분리하기 위해 사용하는 객체(MVC 패턴의 Model에서 이와 같은 일을 수행한다.)
	
	public BoardDAO(ServletContext application) {
		super(application);	
	}

	
	public int selectCount(Map<String, Object> map) {
	
		int totalCount = 0; //게시물의 갯수
		String query = "select count(*) from board";

		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " "      // '+=' 는 쿼리문의 뒷문장을 연결하기 위한 기호 
					+ "like '%" + map.get("searchWord") + "%'";   //   (" where " 앞,뒤에 공백 신경쓰기!!)
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); // 1= 첫번째 컬럼을 가르키는 숫자 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	
	
	public List<BoardDTO> selectList(Map<String, Object> map) {
		
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		
		String query = "select * from board";
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " "
					+ "like '%" + map.get("searchWord") + "%'";
		}
		
		query += " order by num desc";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return bbs;
	}

	
	public int insertWrite(BoardDTO dto) {
		
		int result = 0;
		String query = "insert into board(num,title,content,id,visitcount)"
				+ " values(seq_board_num.nextval,?,?,?,0)";
		
		System.out.println(query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public BoardDTO selectView(String num) {
		
		String query = "select b.*,m.name"
				+ " from member m inner join board b"
				+ " on m.id = b.id"
				+ " where num = ?";
		
		BoardDTO dto = new BoardDTO();
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setVisitcount(rs.getString("visitcount"));
				dto.setName(rs.getString("name"));    // 컬럼명을 순서 숫자로 기입하거나 컬럼명 자체를 쓰거나 원하는 방법으로~
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return dto;
	}
	
	
	
	public void updateVisitCount(String num) {
		
		String query = "update board set"
				+ " visitcount = visitcount + 1"
				+ " where num = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int updateEdit(BoardDTO dto) {
		
		int result = 0;
				
		
		try {
			
			String query = "update board set"
					+ " title = ?, content = ?"
					+ " where num = ?";
					
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int deletePost(BoardDTO dto) {

		int result = 0;
				
		String query = "delete from board where num = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}//class
