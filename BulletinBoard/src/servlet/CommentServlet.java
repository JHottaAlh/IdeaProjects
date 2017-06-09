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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request, messages)){

			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			String text = request.getParameter("text");
			int branch_id = Integer.parseInt(request.getParameter("branch_id"));
			int department_id = Integer.parseInt(request.getParameter("department_id"));

			Comment comment = new Comment();
			comment.setUser_id(user_id);
			comment.setPost_id(post_id);
			comment.setText(text);
			comment.setBranch_id(branch_id);
			comment.setDepartment_id(department_id);

			CommentService commentService = new CommentService();
			commentService.register(comment);

			response.sendRedirect("./");
		}else{
			//コメントが未入力、文字数に不正があったとき記入情報を保持して再入力できるようにする(途中)
			session.setAttribute("errorMessages", messages);

			String text = request.getParameter("text");
			session.setAttribute("text", text);

			int post_id = Integer.parseInt(request.getParameter("post_id"));
			session.setAttribute("post_id", post_id);


			response.sendRedirect("./");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages){
		String text = request.getParameter("text");

		if(text.isEmpty()){
			messages.add("コメントが未入力です");
		}else if(StringUtils.isBlank(text)){
			messages.add("コメントが空白のみです");
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
