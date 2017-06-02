package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/commentdelete")
public class CommentDeleteServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("./");
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//コメントを削除するためのPostメソッド
		int id = Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
			
		CommentService commentService = new CommentService();
		commentService.delete(id, user_id);
			
		response.sendRedirect("./");
	}

}
