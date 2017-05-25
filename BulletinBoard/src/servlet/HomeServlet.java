package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserMessage;
import service.PostService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//テキストエリアの表示条件
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if(user != null){
			isShowMessageForm = true;
		}else{
			isShowMessageForm = false;
		}
		
		List<UserMessage> messages = new PostService().getMessage();
		
		request.setAttribute("messages", messages);
		request.setAttribute("isShowMessageForm", isShowMessageForm);
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
