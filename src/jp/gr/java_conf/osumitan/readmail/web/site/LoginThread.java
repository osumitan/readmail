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
		log("ログインします…");
		// ユーザ名
		setValue("input[name=username]", site.getUserName());
		// パスワード
		setValue("input[name=password]", site.getPassword());
		// オートログインOFF
		setChecked("input[name=autologin]", false);
		// セキュリティ強化OFF
		setChecked("input[name=ipsec]", false);
		// ログインボタン押下
		click("input[name=submit]");
		// ログ
		log("ログインしました。");
		// ステータス：ログアウトする
		setSiteStatus(SiteStatus.LOGOUT);
	}
}
