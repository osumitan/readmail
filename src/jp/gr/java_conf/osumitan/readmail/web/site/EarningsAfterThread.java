package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：処理後報酬を取得する
 */
public class EarningsAfterThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public EarningsAfterThread(MainThread mainThread) {
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
		// 報酬を取得
		WebElement element = findElement(By.xpath(site.getEarningsPath()));
		Pattern p = Pattern.compile(site.getEarningsRegexp());
		Matcher m = p.matcher(element.getText());
		site.setEarningsAfter(m.find() ? Double.valueOf(m.group(1)) : null);
		mainThread.getFrame().getSiteTable().repaint();
		// ログ
		log("処理後報酬を取得しました。");
		// ステータス：ログアウトする
		setSiteStatus(SiteStatus.LOGOUT);
	}
}
