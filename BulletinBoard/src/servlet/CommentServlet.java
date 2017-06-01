package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

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

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request, messages) == true){

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
		}else{
			//コメントが未入力、文字数に不正があったとき記入情報を保持して再入力できるようにする(途中)
			//Comment comment = new Comment();
			//comment.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			//comment.setText(request.getParameter("text"));

			//request.setAttribute("text", comment);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("./").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages){
		String text = request.getParameter("text");

		if(StringUtils.isEmpty(text)){
			messages.add("コメントが未入力です");
		}
		if(500 < text.length()){
			messages.add("コメントは500文字以内で入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
