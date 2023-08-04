package step01_board_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import step01_board_dto.BoardDTO;

public class BoardDAO {
	
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private void getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");       // lookup 메서드를 통해 context.xml 파일에 접근하여 자바환경 코드를 검색
			DataSource ds = (DataSource) envctx.lookup("jdbc/board"); 		  // <Context>태그안의 <Resource> 환경설정의 name이 jdbc/board인 것을 검색
			conn = ds.getConnection();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void getClose() {
		if (rs!=null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if (pstmt!=null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
		if (conn!=null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void insertBoard(BoardDTO boardDTO) {
		
		try {
			getConnection();
			
			String sql = "INSERT INTO BOARD(WRITER,EMAIL, SUBJECT,PASSWORD,CONTENT,READ_CNT,ENROLL_DT)";
			 	   sql+= "VALUES(?,?,?,?,?,0,NOW())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getWriter());
			pstmt.setString(2, boardDTO.getEmail());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getPassword());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
	public ArrayList<BoardDTO> getBoardList(){
		ArrayList<BoardDTO> boardList = new ArrayList<>();
		try {
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO temp = new BoardDTO();
				temp.setBoardId(rs.getLong("BOARD_ID"));
				temp.setWriter(rs.getString("WRITER"));
				temp.setSubject(rs.getString("SUBJECT"));
				temp.setEnrollDt(rs.getDate("ENROLL_DT"));
				temp.setReadCnt(rs.getLong("READ_CNT"));
				
				boardList.add(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		//System.out.println(boardList);
		return boardList;
	}
	
	public BoardDTO getBoardDetail(long boardId) {
		BoardDTO boardDTO = new BoardDTO();
		
		try {
			getConnection();
			
			pstmt = conn.prepareStatement("SELETE * FROM BOARD WHERE BOARD_ID =?");
			pstmt.setLong(1, boardId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDTO.setBoardId(rs.getLong("BOARD_ID"));
				boardDTO.setReadCnt(rs.getLong("READ_CNT"));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setEnrollDt(rs.getDate("ENROLL_DT"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		System.out.println(boardDTO);
		return boardDTO;
	}
}
