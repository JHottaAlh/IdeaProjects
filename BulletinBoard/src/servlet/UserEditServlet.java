package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Branch;
import model.Department;
import model.UserControl;
import service.BranchService;
import service.DepartmentService;
import service.UserControlService;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/useredit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<UserControl> personalData =
				new UserControlService().personalData(Integer.parseInt(request.getParameter("id")));

		//支店リストと部署・役職リストを取得
		List<Branch> branchList = new BranchService().getBranch();
		request.setAttribute("branchList", branchList);

		List<Department> departmentList = new DepartmentService().getDepartment();
		request.setAttribute("departmentList", departmentList);

		//Beansに格納するところから開始
		request.setAttribute("personalData", personalData);

		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

}
