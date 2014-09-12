package dev.test.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SPMainServlet
 */
@WebServlet("/SPMainServlet")
public class SPMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SPMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコーディング
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

	    // フォームデータより名前を取得
	    String id = request.getParameterValues("user_id")[0];
	    String pass = request.getParameterValues("user_pass")[0];
	    System.out.println("receiving");
	    
	    String responseJsonString = "";
	    int responseStatus = HttpServletResponse.SC_NO_CONTENT;
	    
	    // 未入力チェック
	    if (id != null && pass != null) {
	    	System.out.println("received! : " + id + ", " + pass);
	    	// ダミーデータのセット
	    	if (id.equals("itz") && pass.equals("memoryz")) {
	    		responseJsonString = "{\"name\":\"たまちゃん\"}";
	    		responseStatus = HttpServletResponse.SC_OK;
	    	}
	    } else {
	    	System.out.println("no data received.");
	    }
	    
	    // レスポンス
	    response.setStatus(responseStatus);
		PrintWriter out = response.getWriter();
		out.print(responseJsonString);
	}
}
