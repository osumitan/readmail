package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：処理前報酬を取得する
 */
public class EarningsBeforeThread extends EarningsThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public EarningsBeforeThread(MainThread mainThread) {
		super(mainThread, Site::setEarningsBefore);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("報酬明細を開いています…");
		// 報酬明細を開く
		navigate(site.getEarningsPage());
		// 報酬を読み込む
		readEarnings();
		// ログ
		log("処理前報酬を取得しました。");
		// ステータス：クリックポイント
		setSiteStatus(SiteStatus.CLICK_POINT);
	}
}
