package mvcboard;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{

	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "insert into mvcboard("
					+ " idx, name, title, content, ofile, sfile, pass"
					+ " values("
					+ " sql_board_num.nextval,?,?,?,?,?,?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getIdx());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
		System.out.println("게시물 입력 중 예외 발생");
		e.printStackTrace();
			
		}
		return result;
	}
}
