package step01_board_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step01_board_dao.BoardDAO;
import step01_board_dto.BoardDTO;


@WebServlet("/bList")
public class ListBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<BoardDTO> boardList = BoardDAO.getInstance().getBoardList();
		request.setAttribute("boardList", boardList);
		
		RequestDispatcher dis = request.getRequestDispatcher("step01_boardEx/bList.jsp");
		dis.forward(request, response);
	}
}
