package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserControl;
import service.UserControlService;

/**
 * Servlet implementation class userControl
 */
@WebServlet("/usercontrol")
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//UserControl型のuserDataリストを作成し、UserControlServiceクラスのgetControlメソッドでユーザー情報を取り出し、格納
		List<UserControl> userData = new UserControlService().getControl();
		
		
		//userDataというキーに上のuserDataリストを格納、userControl.jspに値を渡す
		request.setAttribute("userData", userData);
		
		
		request.getRequestDispatcher("/userControl.jsp").forward(request, response);
	}

}
