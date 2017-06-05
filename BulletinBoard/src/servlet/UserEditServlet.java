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
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		
		//入力された文字が数値変換できない場合(数字以外の文字を入力された場合)にエラー処理
		boolean judge;
		try{
			Integer.parseInt(request.getParameter("id"));
			judge = true;
		}catch(NumberFormatException e){
			judge = false;
		}
		if(!judge){
			messages.add("入力されたIDのフォーマットが不正です");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("usercontrol");
		}else{

			List<UserControl> personalData =
					new UserControlService().personalData(Integer.parseInt(request.getParameter("id")));
		
			//アドレス直打ちで存在しないID打たれた時にエラーを吐くようにする
		
			if(personalData.isEmpty()){
				messages.add("存在しないIDが入力されました");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("usercontrol");
			}else{
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
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
