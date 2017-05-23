package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//LoginServiceに処理を移して入力されたユーザーが存在するかを確かめる
		LoginService loginService = new LoginService();
		User user = loginService.login(id, password);
		
		HttpSession session = request.getSession();
		//存在する場合はログイン成功
		if(user != null){
			session.setAttribute("loginUser", user);
			response.sendRedirect("home");	//home.jspに移るように
		}else{
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessage", messages);
			response.sendRedirect("index.jsp");
		
		
		
		}*/
	request.getRequestDispatcher("/home.jsp").forward(request, response);	//ログイン機能実装までの仮置き
	}

}
