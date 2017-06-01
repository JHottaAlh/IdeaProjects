package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

/**
 * Servlet implementation class IsStoppedServlet
 */
@WebServlet("/isstopped")
public class IsStoppedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int is_stopped = Integer.parseInt(request.getParameter("is_stopped"));
		new UserService().isStopped(id, is_stopped);
		
		response.sendRedirect("usercontrol");
	}

}
