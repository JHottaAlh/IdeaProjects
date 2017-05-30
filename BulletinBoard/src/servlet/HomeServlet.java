package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import model.UserMessage;
import service.CommentService;
import service.PostService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//記事の投稿一覧を取得
		List<UserMessage> messages = new PostService().getMessage();
		request.setAttribute("messages", messages);
		
		//コメントの一覧を取得
		List<Comment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	
	//記事を削除するためのPostメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		PostService postService = new PostService();
		postService.delete(id, user_id);
		//記事を削除したときにその記事に対するコメントもDBから消す
		postService.commentDelete(id);
		
		response.sendRedirect("home");
		
	}
}
