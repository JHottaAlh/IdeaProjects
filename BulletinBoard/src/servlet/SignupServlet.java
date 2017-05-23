//signupに関するサーブレット

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

import model.User;
import service.UserService;
/**
 * Servlet implementation class Signup
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true){
			//User(Beans)クラスのuserインスタンスを宣言
			User user = new User();
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));

			//user(Beans)を引数にUserServiceクラスのregisterメソッドを実行
			new UserService().register(user);

			//一連の処理を終えたらhome.jspに移動
			response.sendRedirect("/home.jsp");
		}else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup.jsp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages){
		String id = request.getParameter("login_id");
		String password = request.getParameter("password");

		if(StringUtils.isEmpty(id) == true){
			messages.add("IDを入力してください");
		}
		if(StringUtils.isEmpty(password) == true){
			messages.add("パスワードを入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
