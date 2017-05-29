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
			response.sendRedirect("./");
		}else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup.jsp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages){
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		//初回登録時はプライマリキーがないので0を初期値とする
		boolean idCheck = new UserService().idCheck(login_id, 0);
		
		if(!idCheck){
			messages.add("ログインIDが重複しています");
		}
		
		if(!login_id.matches("[a-zA-Z0-9]+")){
			messages.add("ログインIDは半角英数字で入力してください");
		}
		if(StringUtils.isEmpty(login_id) == true){
			messages.add("ログインIDを入力してください");
		}
		if(6 > login_id.length() || 20 < login_id.length()){
			messages.add("ログインIDは6文字以上20文字以下で入力してください");
		}
		
		if(password.length() != 0 && (6 > password.length() || 255 < password.length())){
			messages.add("パスワードは6文字以上255文字以下で入力してください");
		}
		
		if(StringUtils.isEmpty(name) == true){
			messages.add("ユーザー名を入力してください");
		}
		if(10 < name.length()){
			messages.add("ユーザー名は10文字以下で入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
