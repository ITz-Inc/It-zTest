package dev.test.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
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

        // データベースに対する処理

	    // フォームデータより名前を取得
	    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
	    String responseJsonString = "";
	    int responseStatus = HttpServletResponse.SC_NO_CONTENT;

        if(br != null) {
            json = br.readLine();
            System.out.println("received! : " + json);
            // ダミーデータのセット
    		responseJsonString = "{\"name\":\"たまちゃん\"}";
    		responseStatus = HttpServletResponse.SC_OK;
        } else {
	    	System.out.println("no data received.");
        }

	    // レスポンス
	    response.setStatus(responseStatus);
		PrintWriter out = response.getWriter();
		out.print(responseJsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
