//アクセスされたらlogin.jspにつなぐためのサーブレット
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

import model.User;
import service.LoginService;		//ログイン可能なID、パスワードかのチェック

/**
 * Servlet implementation class Index
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログイン画面で入力されたIDとパスワードを受け取る
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		
		//LoginServiceクラスのインスタンスを生成
		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password);
		
		//セッションを作成
		HttpSession session = request.getSession();
		//ユーザーが存在した場合(ログインIDとパスワードが一致した場合)
		if(user != null){
			if(user.getIs_stopped() == 1){
				//ユーザーは存在したがアカウントが停止状態だった場合
				List<String> messages = new ArrayList<String>();
				messages.add("該当するアカウントは管理者によって停止されています");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("login.jsp");
			}else{
				//セッションにユーザー情報を追加
				//loginUserキーにuserの値を格納
				session.setAttribute("loginUser", user);
				response.sendRedirect("./");
			}
		}else{
			//ユーザーが存在しなかった場合エラーメッセージをセッションに追加
			//ログイン画面に戻す
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login.jsp");
		}
	}
}

