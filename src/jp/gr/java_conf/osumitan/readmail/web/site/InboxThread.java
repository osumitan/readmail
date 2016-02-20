package jp.gr.java_conf.osumitan.readmail.web.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：受信箱
 */
public class InboxThread extends PointThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public InboxThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("受信箱ページを開いています…");
		// 受信箱を開く
		navigate(site.getInboxPage());
		// ログ
		log("受信箱ページを開きました。");
		// クリックポイントリンクがあるか
		if(existsElement(String.format("a[href*='%s']", site.getInboxMailLink()))) {
			// ログ
			log("受信箱メールを開いています…");
			// 受信箱メールを開く
			WebElement mail = findElement(String.format("a[href*='%s']", site.getInboxMailLink()));
			get(mail.getAttribute("href"));
			// ログ
			log("受信箱メールを開きました。");
			// 受信箱ポイントリンクがあるか
			if(existsElement(String.format("a[href*='%s']", site.getInboxPointLink()))) {
				// ログ
				log("受信箱ポイントを開いています…");
				// 受信箱ポイントを開く
				WebElement point = findElement(String.format("a[href*='%s']", site.getInboxPointLink()));
				get(point.getAttribute("href"));
				// ログ
				log("受信箱ポイントを開きました。");
				// ポイント獲得まで待つ
				waitUntilPointGet();
			}
			// ログ
			log("受信箱ページを開いています…");
			// 受信箱を開く
			navigate(site.getInboxPage());
			// ログ
			log("受信箱メールを削除しています…");
			// 受信箱メールを削除する
			setChecked(By.xpath(String.format(site.getInboxMailDeleteCheck(), site.getInboxMailLink())), true);
			click(String.format("input[type='submit'][value='%s']", site.getInboxMailDeleteButton()));
			// ログ
			log("受信箱メールを削除しました。");
		} else {
			// ログ
			log("受信箱が終了しました。");
			// ステータス：処理後報酬を取得する
			setSiteStatus(SiteStatus.EARNINGS_AFTER);
		}
	}
}
