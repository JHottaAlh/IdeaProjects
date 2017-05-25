//セッションが切れた時、未ログイン時にログイン画面に遷移するようにするフィルタ
//URLの直打ちへの対策

//わからなすぎて中断
/*
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter("/*")
public class SessionTimeoutFilter implements Filter {
		
	//doFilter
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getContextPath();	//(/BulletinBoard)という文字列を格納
		
		System.out.println(uri);
		
		//loginUserの情報をuserに格納
		User user = (User) req.getSession().getAttribute("loginUser");
		
		if(uri.matches("/Login")){
			if(user == null){
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			chain.doFilter(request, response);	
		}
	}
	
	//init
		@Override
		public void init(FilterConfig config){		
		}
		
	//destroy
	@Override
	public void destroy(){
	}

}*/
