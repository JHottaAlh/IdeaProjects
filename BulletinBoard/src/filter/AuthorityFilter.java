//管理者権限が無いのにユーザー編集、管理画面に行こうとした時のフィルター
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

@WebFilter(filterName = "AuthorityFilter", urlPatterns = {"/useredit", "/usercontrol", "/userControl.jsp", "/userEdit.jsp"})
public class AuthorityFilter implements Filter {
	
	public void init(FilterConfig config){
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		//Servlet(Request/Response)をHttpServlet型にキャスト
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		User authority = (User) req.getSession().getAttribute("loginUser"); 
		HttpSession session = req.getSession();
		int authorityCheck = authority.getDepartment_id();
		//役職が管理者(department_idが1)の人以外は該当ページにアクセスできなくする
		if(authorityCheck != 1){
			List<String> messages = new ArrayList<String>();
			messages.add("該当するページへ移動する権限がありません");
			session.setAttribute("errorMessages", messages);
			req.getRequestDispatcher("./").forward(req, res);
		//管理者の人であるならば処理を継続
		}else{
			chain.doFilter(req, res);
		}
	}
	
	public void destroy(){
	}

}
