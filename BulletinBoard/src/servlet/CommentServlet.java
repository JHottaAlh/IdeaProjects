package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import service.CommentService;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		String text = request.getParameter("text");
		
		Comment comment = new Comment();
		comment.setUser_id(user_id);
		comment.setPost_id(post_id);
		comment.setText(text);
		
		CommentService commentService = new CommentService();
		commentService.register(comment);
		
		response.sendRedirect("./");
		
	}

}
