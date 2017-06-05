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

import model.Category;
import model.Post;
import model.User;
import service.CategoryService;
import service.PostService;


@WebServlet("/newpost")
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//カテゴリ一覧の取得
		List<Category> categories = new CategoryService().getCategory();
		request.setAttribute("categories", categories);


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
			//カテゴリ一覧の取得
			List<Category> categories = new CategoryService().getCategory();
			request.setAttribute("categories", categories);

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
		String category = request.getParameter("category");

		if(StringUtils.isEmpty(title)){
			messages.add("件名が未入力です");
		}else if(StringUtils.isBlank(title)){
			messages.add("件名が空白のみです");
		}
		if(50 < title.length()){
			messages.add("件名は50文字以内で入力してください");
		}

		if(category.isEmpty()){
			messages.add("カテゴリが未選択です");
		}

		if(StringUtils.isEmpty(text)){
			messages.add("本文が未入力です");
		}else if(StringUtils.isBlank(text)){
			messages.add("本文が空白のみです");
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
