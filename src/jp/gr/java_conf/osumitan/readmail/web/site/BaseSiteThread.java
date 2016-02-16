package jp.gr.java_conf.osumitan.readmail.web.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッドの基底クラス
 */
public abstract class BaseSiteThread extends Thread {

	/** メインスレッド */
	protected MainThread mainThread;
	/** ドライバ */
	protected RemoteWebDriver driver;
	/** サイト情報 */
	protected Site site;

	/** ページ読み込み完了を待つスクリプト */
	private static final String SCRIPT_WAIT_LOADED = "return document && document.readyState && document.readyState == \"complete\";";

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
		this.driver.get(String.format("http://%s/%s%s", this.site.getDomain(), this.site.getPagePrefix(), page));
	}

	/**
	 * ページ読み込み完了を待つ
	 */
	protected void waitLoaded() {
		// document.readyState が complete であること
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(this.driver);
		Function<RemoteWebDriver, Boolean> isTrue = (driver) -> {
			return Boolean.TRUE.equals(this.driver.executeScript(SCRIPT_WAIT_LOADED));
		};
		wait.until(isTrue);
	}

	/**
	 * エレメントが存在するか
	 * @param selector セレクタ
	 * @return エレメントが存在するか
	 */
	protected boolean existsElement(String selector) {
		return !this.driver.findElements(By.cssSelector(selector)).isEmpty();
	}

	/**
	 * エレメントを探す
	 * @param by By
	 * @return エレメント
	 */
	protected WebElement findElement(By by) {
		return this.driver.findElement(by);
	}

	/**
	 * エレメントを探す
	 * @param selector セレクタ
	 * @return エレメント
	 */
	protected WebElement findElement(String selector) {
		return findElement(By.cssSelector(selector));
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
}
