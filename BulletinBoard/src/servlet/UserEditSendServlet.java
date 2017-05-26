//ユーザー編集画面での入力されたものを処理するサーブレット
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

@WebServlet("/UserEditSendServlet")
public class UserEditSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		
		if (isValid(request, messages) == true){
		
			//User型のeditUserインスタンス(Beans)を作成
			User editUser = new User();
			editUser.setId(Integer.parseInt(request.getParameter("id")));
			editUser.setLogin_id(request.getParameter("login_id"));
			if(request.getParameter("password") != null){
				editUser.setPassword(request.getParameter("password"));
			}else{
				//editUser.setPassword();		もともとのパスワードを格納したい
			}
			editUser.setName(request.getParameter("name"));
			editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			editUser.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
			
			//UserService型のupdateメソッドを実行
			new UserService().update(editUser);
		
			response.sendRedirect("userControl.jsp");
		}else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userControl.jsp");
		}
		
	}
	private boolean isValid(HttpServletRequest request, List<String>messages){
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		
		if(!login_id.matches("^[a-zA-Z0-9]$")){
			messages.add("ログインIDは半角英数字で入力してください");
		}
		if(StringUtils.isEmpty(login_id) == true){
			messages.add("ログインIDを入力してください");
		}
		if(6 > login_id.length() || 20 < login_id.length()){
			messages.add("ログインIDは6文字以上20文字以下で入力してください");
		}
		
		if(6 > password.length() || 255 < password.length()){
			messages.add("パスワードは6文字以上255文字以下で入力してください");
		}
		if(password != password1){
			messages.add("パスワードが一致しません");
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
