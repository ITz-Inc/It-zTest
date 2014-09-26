/**クラス名： IBaseController.java
 *説明： 機能毎に実装するコントローラはこのインターフェースを使用して実装して下さい
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */



package business.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hiroki Ebina
 *
 */
public interface IBaseController {
	//TODO コントローラとして必要なインターフェースはここに追加して下さい by ebina
	void excute(String json, HttpServletResponse response) throws Exception;
}
