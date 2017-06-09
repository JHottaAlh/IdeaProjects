package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Comment;
import model.UserMessage;
import service.CategoryService;
import service.CommentService;
import service.PostService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns ={"/index.jsp"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = request.getParameter("category");
		String oldDate = request.getParameter("oldDate");
		String latestDate = request.getParameter("latestDate");

		//初回ホーム移動時
		if(category == null && oldDate == null && latestDate == null || category == null){
			List<UserMessage> sortMessages = new PostService().getMessage();
			request.setAttribute("messages", sortMessages);
		//初回以降の処理
		}else{
			request.setAttribute("category", category);
			request.setAttribute("oldDate", oldDate);
			request.setAttribute("latestDate", latestDate);
			
			//終了日のみ入力されたら、その日以前までの投稿を表示
			if(oldDate.isEmpty() && !latestDate.isEmpty()){
				oldDate = "1000-01-01";
			}
			//開始日のみ入力されたら、その日以降の日付を表示
			if(!oldDate.isEmpty() && latestDate.isEmpty()){
				latestDate = "9999-99-99";
			}

			//oldDateが未選択だった場合一番古い投稿の日付を取得する
			if(oldDate == null || oldDate.isEmpty()){
				UserMessage oldDateBeans = new PostService().oldDate();
				oldDate = oldDateBeans.getTimed_at().toString();
			}

			//latestDateが未選択だった場合本日の日付を取得する
			if(latestDate == null || latestDate.isEmpty()){
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				latestDate = sdf.format(c.getTime());
			}

			//日付を比較し、過去～未来の順に入れ替えてDaoに渡す
			int ret = oldDate.compareTo(latestDate);
			if(ret > 0){
				oldDate += " 23:59:59";
				//カテゴリーが選択された場合
				if(!category.isEmpty()){
					List<UserMessage> sortMessages = new PostService().postSort(category, latestDate, oldDate);
					request.setAttribute("messages", sortMessages);

				//カテゴリー選択が無く、日付のみのソート
				}else if(category.isEmpty()){
					List<UserMessage> sortMessages = new PostService().postSortDate(latestDate, oldDate);
					request.setAttribute("messages", sortMessages);
				}

			}else{
				latestDate += " 23:59:59";
				//カテゴリーが選択された場合
				if(!category.isEmpty()){
					List<UserMessage> sortMessages = new PostService().postSort(category, oldDate, latestDate);
					request.setAttribute("messages", sortMessages);

				//カテゴリー選択が無く、日付のみのソート
				}else if(category.isEmpty()){
					List<UserMessage> sortMessages = new PostService().postSortDate(oldDate, latestDate);
					request.setAttribute("messages", sortMessages);
				}
			}
		}
		//コメントの一覧を取得
		List<Comment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		//カテゴリ一覧の取得
		List<Category> categories = new CategoryService().getCategory();
		request.setAttribute("categories", categories);


		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

	//記事を削除するためのPostメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));

		PostService postService = new PostService();
		postService.delete(id, user_id);
		//記事を削除したときにその記事に対するコメントもDBから消す
		postService.commentDelete(id);

		response.sendRedirect("./");

	}
}
