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
import model.UserControl;
import service.UserControlService;
import service.UserService;
import utils.CipherUtil;

@WebServlet("/usereditsend")
public class UserEditSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		
		if (isValid(request, messages)){
		
			//User型のeditUserインスタンス(Beans)を作成
			User editUser = new User();
			editUser.setId(Integer.parseInt(request.getParameter("id")));
			editUser.setLogin_id(request.getParameter("login_id"));
			if(!StringUtils.isEmpty(request.getParameter("password"))){
				//新規で登録される場合は暗号化して送信
				editUser.setPassword(request.getParameter("password"));
				String encPassword = CipherUtil.encrypt(editUser.getPassword());
				editUser.setPassword(encPassword);
			}else{
				//パスワード未入力の場合もともとのパスワードをセット
				//既に暗号化されているものを引っ張っているので再度暗号化はNG
				editUser.setPassword(request.getParameter("password2"));
			}
			editUser.setName(request.getParameter("name"));
			editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			editUser.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
			
			//UserService型のupdateメソッドを実行
			new UserService().update(editUser);
		
			response.sendRedirect("usercontrol");
		}else{
			session.setAttribute("errorMessages", messages);
			List<UserControl> personalData = 
					new UserControlService().personalData(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("personalData", personalData);
			request.getRequestDispatcher("userEdit.jsp").forward(request, response);
		}
		
	}
	private boolean isValid(HttpServletRequest request, List<String>messages){
		int id = Integer.parseInt(request.getParameter("id"));
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		boolean idCheck = new UserService().idCheck(login_id, id);
		
		if(!idCheck){
			messages.add("ログインIDが重複しています");
		}
		if(login_id.isEmpty()){
			messages.add("ログインIDを入力してください");
		}else{
			if(!login_id.matches("[a-zA-Z0-9]+")){
				messages.add("ログインIDは半角英数字で入力してください");
			}
			if(6 > login_id.length() || 20 < login_id.length()){
				messages.add("ログインIDは6文字以上20文字以下で入力してください");
			}
		}
		
		if(name.isEmpty()){
			messages.add("ユーザー名を入力してください");
		}else{
			if(10 < name.length()){
				messages.add("ユーザー名は10文字以下で入力してください");
			}
		}
		
		if(password.length() != 0 && password != password1){
			messages.add("パスワードが一致しません");
		}
		if(password.length() != 0 && (6 > password.length() || 255 < password.length())){
			messages.add("パスワードは6文字以上255文字以下で入力してください");
		}
		
		if(branch_id == 0 && (department_id != 0 && department_id != 1)){
			messages.add("支店と部署・役職が不正の組み合わせです");
		}
		if(branch_id != 0 && (department_id ==0 || department_id == 1)){
			messages.add("支店と部署・役職が不正の組み合わせです");
		}
		
		
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}
