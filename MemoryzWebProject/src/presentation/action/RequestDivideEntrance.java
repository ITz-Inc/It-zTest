/**クラス名： ReauestDivideEntrance.java
 *説明： Android側から送信されたリクエストに基づいて起動コントローラを制御するクラス
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */

package presentation.action;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.service.IBaseController;
import business.util.ControllerFactory;

import common.util.HttpSettingUtil;
import common.util.PropertiesManager;


/*
 * @author H.Ebina
 */
@WebServlet("/MainServlet")
public class RequestDivideEntrance extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestDivideEntrance() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");

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


        String configPath ="config.properties";

		try{
			HttpSettingUtil httpSettingUtil = new HttpSettingUtil();
			httpSettingUtil.setServletMetaInfo(request, response);

			myLog.info("--RequestDivideEntrance.Start--");

			PropertiesManager propertiesManager = new PropertiesManager(new String[]{configPath});
			Properties conf = propertiesManager.getProperties();

			//コントローラに振り分けるパラメータを指定してください。configにはコントローラのクラス名を定義。 by ebina
			//String controllerName = conf.getProperty(request.getParameter("ControllerName"));
			//とりあえずWeb単体で確認する為に直接指定 ↑クライアントと結合するときは上をアクティブ
			String controllerName = conf.getProperty("ControllerName");

			//コントローラを取得するクラス
			ControllerFactory cf = new ControllerFactory();
			Class<IBaseController> controllerClass = cf.getController(controllerName);
			IBaseController controller = controllerClass != null ?
					controllerClass.newInstance() : null;

			//config.propertiesにコントローラが定義されている かつコントローラの基底インタフェースを実装している
			if(controller!=null && controller instanceof IBaseController){
				myLog.info("--Controller.excute.Start--");
				//各コントローラ毎の処理を行う。クライアント側に返却する値はrequest、responseに詰めてください。
				controller.excute(json, response);
				myLog.info("--Controller.excute.End--");
			}
			myLog.info("--RequestDivideEntrance.End--");
		}catch (Exception e) {
			myLog.severe("--RequestDivideEntrance Fatal Error--");
			e.printStackTrace();
		}


	}

	/**
	 * @param HttpServletRequest
	 *            request クライアント送信用リクエストパラメータ
	 * @param HttpServletRequest
	 *            response クライアント返却用レスポンスパラメータ
	 * @author Hiroki Ebina
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
