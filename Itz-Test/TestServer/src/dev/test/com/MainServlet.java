package dev.test.com;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコーディング
	    response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");

	    // フォームデータより名前を取得
	    String name = request.getParameter("user_name");
	    String pass = request.getParameter("user_pass");

	    // サーブレットコンテキストを取得
	    ServletContext servletContext = getServletContext();

	    // 次に遷移するページ(初期値)
	    String nextPage = "/WEB-INF/JSP/showdata.jsp";
	    
	    // 未入力チェック
	    if (name == null || pass == null) {
	    	nextPage = "/index.html";
	    }
	    
	    //メッセージをセット
	    request.setAttribute("user_name", name);
	    request.setAttribute("user_pass", pass);

	    // ページ遷移
	    servletContext.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
