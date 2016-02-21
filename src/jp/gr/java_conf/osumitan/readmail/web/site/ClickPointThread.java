package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：クリックポイント
 */
public class ClickPointThread extends PointThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public ClickPointThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("クリックポイントページを開いています…");
		// クリックポイントを開く
		navigate(site.getClickPointPage());
		// ログ
		log("クリックポイントページを開きました。");
		// クリックポイントリンクがあるか
		if(existsElement(String.format("a[href*='%s']", site.getClickPointLink()))) {
			// ログ
			log("クリックポイントを開いています…");
			// クリックポイントを開く
			navigatePointLink(site.getClickPointLink());
			// ポイント獲得まで待つ
			waitUntilPointGet();
		} else {
			// ログ
			log("クリックポイントが終了しました。");
			// ステータス：受信箱
			setSiteStatus(SiteStatus.INBOX);
		}
	}
}
