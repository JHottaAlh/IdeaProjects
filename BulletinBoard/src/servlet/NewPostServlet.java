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

import model.Post;
import model.User;
import service.PostService;


@WebServlet("/newpost")
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("newPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();
		Post post = new Post();
		post.setTitle(request.getParameter("title"));
		post.setCategory(request.getParameter("category"));
		post.setText(request.getParameter("text"));	
		
		HttpSession session = request.getSession();
		if (isValid(request, messages)){
			
			User user = (User) session.getAttribute("loginUser");
			//Post(Beans)クラスのpostインスタンスを宣言
			
			post.setUser_id(user.getId());
			
			//post(Beans)を引数にPostServiceクラスのregisterメソッドを実行
			new PostService().register(post);

			//一連の処理を終えたらhome.jspに移動
			response.sendRedirect("./");
		}else{
			List<String> contents = new ArrayList<String>();
			contents.add(request.getParameter("title"));
			contents.add(request.getParameter("text"));
			contents.add(request.getParameter("category"));
			request.setAttribute("contents", contents);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newPost.jsp").forward(request, response);
		}
	}
	
	private boolean isValid(HttpServletRequest request, List<String>messages){
		String title = request.getParameter("title");
		String text = request.getParameter("text");

		if(StringUtils.isEmpty(title) == true){
			messages.add("件名が未入力です");
		}
		if(50 < title.length()){
			messages.add("件名は50文字以内で入力してください");
		}
		if(StringUtils.isEmpty(text) == true){
			messages.add("本文が未入力です");
		}
		if(1000 < text.length()){
			messages.add("本文は1000文字以内で入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
