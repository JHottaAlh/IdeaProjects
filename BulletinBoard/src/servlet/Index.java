//サーブレット	ここから
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新規登録をクリックされた時の処理
		//ユーザー登録画面に遷移
		RequestDispatcher d = request.getRequestDispatcher("signup.jsp");
		d.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//index.jspから値を受け取ってログイン可能かチェック
		String id = request.getParameter("id");
		String password = request.getParameter("password");
	}

}
