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
		// エンコーディング
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

	    // フォームデータより名前を取得
	    String name = request.getParameter("user_name");
	    String pass = request.getParameter("user_pass");
	    System.out.println("receiving");
	    
	    // 未入力チェック
	    if (name == null || pass == null) {
	    	System.out.println("received! : " + name + ", " + pass);
	    } else {
	    	System.out.println("no data received.");
	    }
	    
	    response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.print("test");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
