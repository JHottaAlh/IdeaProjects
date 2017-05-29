package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserControl;
import service.UserControlService;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEdit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<UserControl> personalData = 
				new UserControlService().personalData(Integer.parseInt(request.getParameter("id")));
		
		//Beansに格納するところから開始
		request.setAttribute("personalData", personalData);
		
		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

}
