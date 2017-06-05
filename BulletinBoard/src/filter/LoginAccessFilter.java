//ユーザーのis_stopped情報が1だった場合にアクセス・ログイン不可にするフィルター
package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.LoginService;

@WebFilter(filterName="LoginAccessFilter", urlPatterns="/*")
public class LoginAccessFilter implements Filter {
	public void init(FilterConfig config){
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		//Servlet(Request/Response)をHttpServlet型にキャスト
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		if(!uri.matches(".*/login.*")){
		
			User userCheck = (User) req.getSession().getAttribute("loginUser");
			//セッション呼び出し
			HttpSession session = req.getSession();
			//ユーザーがログインしていたらそのユーザーの情報を取得
			if(userCheck != null){
				String login_id = userCheck.getLogin_id().toString();
				String password = userCheck.getPassword().toString();
			
				//該当ユーザーの情報をアクセス毎に受け取る(状態を更新)
				LoginService loginServiceCheck = new LoginService();
				User user = loginServiceCheck.filter(login_id, password);
			
				//アカウントが停止状態だった場合ログイン画面にGo
				if(user.getIs_stopped() == 1){
					List<String> messages = new ArrayList<String>();
					messages.add("該当するアカウントは管理者によって停止されています");
					session.setAttribute("errorMessages", messages);
					req.getRequestDispatcher("login").forward(request, response);
					session.invalidate();//セッションの無効化
					return;
				}else{
					session.setAttribute("loginUser", user);
					chain.doFilter(req, res);
				}
				//そもそもログイン状態じゃなかったらログイン画面にGo(エラーなし)
			}else{
				res.sendRedirect("login");
				return;
			}
		}else{
			chain.doFilter(req, res);
		}
	}
	
	public void destroy(){
		
	}

}
