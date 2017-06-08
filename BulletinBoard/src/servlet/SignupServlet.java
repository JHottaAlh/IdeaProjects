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

import model.Branch;
import model.Department;
import model.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;
/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//支店リストと部署・役職リストを取得
		List<Branch> branchList = new BranchService().getBranch();
		request.setAttribute("branchList", branchList);
		
		List<Department> departmentList = new DepartmentService().getDepartment();
		request.setAttribute("departmentList", departmentList);
		
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages)){
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
			response.sendRedirect("usercontrol");
		}else{
			session.setAttribute("errorMessages", messages);
			User signupInfo = new User();
			signupInfo.setLogin_id(request.getParameter("login_id"));
			signupInfo.setName(request.getParameter("name"));
			signupInfo.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			signupInfo.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
			session.setAttribute("info", signupInfo);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>messages){
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		//初回登録時はプライマリキーがないので0を初期値とする
		boolean idCheck = new UserService().idCheck(login_id, 0);
		
		if(!idCheck){
			messages.add("ログインIDが重複しています");
		}
		
		if(login_id.isEmpty()){
			messages.add("ログインIDを入力してください");
		}else if(!login_id.matches("[a-zA-Z0-9]+")){
			messages.add("ログインIDは半角英数字で入力してください");
		}else if(6 > login_id.length() || 20 < login_id.length()){
			messages.add("ログインIDは6文字以上20文字以下で入力してください");
		}
		
		if(name.isEmpty()){
			messages.add("ユーザー名を入力してください");
		}else{
			if(10 < name.length()){
				messages.add("ユーザー名は10文字以下で入力してください");
			}
		}
		
		if(password.isEmpty() && password1.isEmpty()){
			messages.add("パスワードを入力してください");
		}else{
			if(!password.equals(password1)){
				messages.add("パスワードが一致しません");
			}
			if(password.length() != 0 && (6 > password.length() || 20 < password.length())){
				messages.add("パスワードは6文字以上20文字以下で入力してください");
			}
		}
		if(branch_id == 999){
			messages.add("支店を選択してください");
		}
		if(department_id == 999){
			messages.add("部署・役職を選択してください");
		}
		
		if(branch_id == 1 && (department_id != 1 && department_id != 2)){
			messages.add("支店と部署・役職が不正の組み合わせです");
		}
		if(branch_id != 1 && (department_id ==1 || department_id == 2)){
			messages.add("支店と部署・役職が不正の組み合わせです");
		}
		
		
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
