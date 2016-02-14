package jp.gr.java_conf.osumitan.readmail.web.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッドの基底クラス
 */
public abstract class BaseSiteThread extends Thread {

	/** メインスレッド */
	protected MainThread mainThread;
	/** ドライバ */
	protected WebDriver driver;
	/** サイト情報 */
	protected Site site;

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public BaseSiteThread(MainThread mainThread) {
		// メインスレッド
		this.mainThread = mainThread;
		// ドライバ
		this.driver = mainThread.getDriver();
		// サイト情報
		this.site = this.mainThread.getCurrentSite();
	}

	/**
	 * ログ出力
	 * @param message メッセージ
	 */
	protected void log(String message) {
		this.mainThread.getFrame().log(String.format("(%s) %s", this.site.getName(), message));
	}

	/**
	 * サイトステータス設定
	 * @param siteStatus サイトステータス
	 */
	protected void setSiteStatus(SiteStatus siteStatus) {
		this.mainThread.setSiteStatus(siteStatus);
	}

	/**
	 * ページ遷移
	 * @param page ページ
	 */
	protected void navigate(String page) {
		this.driver.get(String.format("http://%s/%s", this.site.getDomain(), page));
	}

	/**
	 * エレメントを探す
	 * @param selector セレクタ
	 * @return エレメント
	 */
	protected WebElement findElement(String selector) {
		return this.driver.findElement(By.cssSelector(selector));
	}

	/**
	 * クリックする
	 * @param selector セレクタ
	 */
	protected void click(String selector) {
		findElement(selector).click();
	}

	/**
	 * 値を設定する
	 * @param selector セレクタ
	 * @param value 値
	 */
	protected void setValue(String selector, String value) {
		WebElement element = findElement(selector);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * チェック状態を設定する
	 * @param selector セレクタ
	 * @param checked チェック状態
	 */
	protected void setChecked(String selector, boolean checked) {
		WebElement element = findElement(selector);
		if(checked != element.isSelected()) {
			element.click();
		}
	}
//	WebElement elm2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("document-title")));
//	super.frame.showMessage("title", elm2.getText());
//	drv.quit();
}
