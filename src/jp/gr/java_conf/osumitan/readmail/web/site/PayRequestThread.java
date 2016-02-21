package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：支払請求
 */
public class PayRequestThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public PayRequestThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("支払請求を開いています…");
		// 支払明細を開く
		navigate(site.getPayRequestPage());
		// 支払請求可否を設定する
		site.setPayable(existsElement(site.getPayRequestButtonSelector()));
		mainThread.getFrame().repaint();
		// ログ
		log("支払請求を確認しました。");
		// ステータス：ログアウトする
		setSiteStatus(SiteStatus.LOGOUT);
	}
}
