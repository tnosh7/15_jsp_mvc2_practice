package step01_board_controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step01_board_dao.BoardDAO;
import step01_board_dto.BoardDTO;


@WebServlet("/bWrite")
public class WriteBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("step01_boardEx/bWrite.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setWriter(request.getParameter("writer"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setEmail(request.getParameter("email"));
		boardDTO.setPassword(request.getParameter("password"));
		boardDTO.setContent(request.getParameter("content"));
		
		BoardDAO.getInstance().insertBoard(boardDTO);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String jsScript = "<script>";
			   jsScript +="alert('게시물이 등록되었습니다.');";	
			   jsScript +="location.href='bList';";	
			   jsScript +="</script>";	
		out.print(jsScript);
	}

}
