package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：ログアウトする
 */
public class LogoutThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public LogoutThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("ログアウトしています…");
		// ログアウト
		navigate(site.getLogoutPage());
		// ログ
		log("ログアウトしました。");
		// ステータス：正常終了
		setSiteStatus(SiteStatus.FINISHED);
	}
}
