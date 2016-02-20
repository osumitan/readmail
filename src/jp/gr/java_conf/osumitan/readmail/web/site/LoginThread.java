package jp.gr.java_conf.osumitan.readmail.web.site;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：ログインする
 */
public class LoginThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public LoginThread(MainThread mainThread) {
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
		log("ログインしています…");
		// ユーザ名
		setValue("input[name='username']", site.getUserName());
		// パスワード
		setValue("input[name='password']", site.getPassword());
		// オートログインOFF
		setChecked("input[name='autologin']", false);
		// セキュリティ強化OFF
		setChecked("input[name='ipsec']", false);
		// ログインボタン押下
		click("input[name='submit']");
		// ページ読み込み完了を待つ
		waitLoaded();
		// 報酬明細リンクが存在すれば正常ログイン
		if(existsElement(String.format("a[href*='%s']", site.getEarningsPage()))) {
			// ログ
			log("ログイン成功しました。");
			// ステータス
			if(mainThread.getFrame().getLoginOnlyCheck().isSelected()) {
				// ステータス：正常終了
				setSiteStatus(SiteStatus.FINISHED);
			} else {
				// ステータス：処理前報酬を取得する
				setSiteStatus(SiteStatus.EARNINGS_BEFORE);
			}
		} else {
			// ログ
			log("ログイン失敗しました。");
			// ステータス：異常終了
			setSiteStatus(SiteStatus.ABENDED);
		}
	}
}
