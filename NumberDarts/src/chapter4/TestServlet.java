package chapter4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/test" })
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String parameter = request.getParameter("param");

		System.out.println(parameter);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write("<html>");
		writer.write("<head>");
		writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.write("<title>ただパラメータを表示するだけノサンプル</title>");
		writer.write("</head>");
		writer.write("<body>");
		writer.write(parameter);
		writer.write("</body>");
		writer.write("</html>");
	}

}
