package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：ログインページを開く
 */
public class OpenLoginPageThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public OpenLoginPageThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("ログインページを開いています…");
		// ログインページを開く
		navigate(site.getLoginPage());
		// ログ
		log("ログインページを開きました。");
		// ステータス：ログインする
		setSiteStatus(SiteStatus.LOGIN);
	}
}
