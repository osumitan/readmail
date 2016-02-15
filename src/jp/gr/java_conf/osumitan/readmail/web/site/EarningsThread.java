package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：ログアウトする
 */
public class EarningsThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public EarningsThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("報酬明細を開いています…");
		// 報酬明細を開く
		navigate(site.getEarningsPage());
		// ページ読み込み完了を待つ
		waitLoaded();
		//TODO xmlpath：//*[contains(text(),'合計ポイント')]/ancestor::tr[1]/td[2]//text()
		// ステータス：ログアウトする
		setSiteStatus(SiteStatus.LOGOUT);
	}
}
