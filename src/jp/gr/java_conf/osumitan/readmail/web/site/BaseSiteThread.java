package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
	private static final String SCRIPT_WAIT_LOADED = "return document.readyState == 'interactive' || document.readyState == 'complete';";

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
	 * 条件成立まで待つ
	 * @param isTrue 条件判定処理
	 */
	protected void until(Function<RemoteWebDriver, Boolean> isTrue) {
		until(isTrue, 3600L);
	}

	/**
	 * 条件成立まで待つ
	 * @param isTrue 条件判定処理
	 * @param timeout タイムアウト
	 */
	protected void until(Function<RemoteWebDriver, Boolean> isTrue, long timeout) {
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(this.driver);
		wait.withTimeout(timeout, TimeUnit.SECONDS);
		wait.pollingEvery(100L, TimeUnit.MILLISECONDS);
		wait.until(isTrue);
	}

	/**
	 * ページ読み込み完了を待つ
	 */
	protected void waitLoaded() {
		// document.readyState が interactive または complete であること
		try {
			until((driver) -> Boolean.TRUE.toString().equals(this.driver.executeScript(SCRIPT_WAIT_LOADED)), 5L);
		} catch(TimeoutException e) {
			log("ページ読み込み待ちがタイムアウトしました。");
		}
	}

	/**
	 * GET
	 * @param url URL
	 */
	protected void get(String url) {
		// ページ遷移
		try {
			this.driver.get(url);
		} catch(TimeoutException e) {
			log("ページ読み込み待ちがタイムアウトしました。");
		}
	}

	/**
	 * ページ遷移
	 * @param page ページ
	 */
	protected void navigate(String page) {
		// ページ遷移
		get(String.format("http://%s/%s%s", this.site.getDomain(), this.site.getPagePrefix(), page));
	}

	/**
	 * エレメントが存在するか
	 * @param by By
	 * @return エレメントが存在するか
	 */
	protected boolean existsElement(By by) {
		return !this.driver.findElements(by).isEmpty();
	}

	/**
	 * エレメントが存在するか
	 * @param selector セレクタ
	 * @return エレメントが存在するか
	 */
	protected boolean existsElement(String selector) {
		return existsElement(By.cssSelector(selector));
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
	 * @param by By
	 */
	protected void click(By by) {
		try {
			findElement(by).click();
		} catch(TimeoutException e) {
			log("ページ読み込み待ちがタイムアウトしました。");
		}
	}

	/**
	 * クリックする
	 * @param selector セレクタ
	 */
	protected void click(String selector) {
		click(By.cssSelector(selector));
	}

	/**
	 * 値を設定する
	 * @param by By
	 * @param value 値
	 */
	protected void setValue(By by, String value) {
		WebElement element = findElement(by);
		element.clear();
		element.sendKeys(value == null ? "" : value);
	}

	/**
	 * 値を設定する
	 * @param selector セレクタ
	 * @param value 値
	 */
	protected void setValue(String selector, String value) {
		setValue(By.cssSelector(selector), value);
	}

	/**
	 * チェック状態を設定する
	 * @param by By
	 * @param checked チェック状態
	 */
	protected void setChecked(By by, boolean checked) {
		WebElement element = findElement(by);
		if(checked != element.isSelected()) {
			element.click();
		}
	}

	/**
	 * チェック状態を設定する
	 * @param selector セレクタ
	 * @param checked チェック状態
	 */
	protected void setChecked(String selector, boolean checked) {
		setChecked(By.cssSelector(selector), checked);
	}
}
