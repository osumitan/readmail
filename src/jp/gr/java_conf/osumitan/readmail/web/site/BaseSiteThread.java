package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

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
	 * 条件成立まで待つ
	 * @param isTrue 条件判定処理
	 */
	protected void until(Function<RemoteWebDriver, Boolean> isTrue) {
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(this.driver);
		wait.withTimeout(3600L, TimeUnit.SECONDS);
		wait.pollingEvery(100L, TimeUnit.MILLISECONDS);
		wait.until(isTrue);
	}

	/**
	 * ページ読み込み完了を待つ
	 */
	protected void waitLoaded() {
		// document.readyState が complete であること
		until((driver) -> Boolean.TRUE.equals(this.driver.executeScript(SCRIPT_WAIT_LOADED)));
	}

	/**
	 * ポイント獲得を待つ
	 */
	protected void waitPointGet() {
		// 報酬獲得メッセージ表示まで待つ
		until((driver) -> existsElement(By.xpath(String.format(site.getPointGetMessagePath(), site.getPointGetMessage()))));
	}

	/**
	 * GET
	 * @param url URL
	 */
	protected void get(String url) {
		// ページ遷移
		this.driver.get(url);
		// ページ読み込み完了を待つ
		waitLoaded();
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
		element.sendKeys(value == null ? "" : value);
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

	/**
	 * ポイント獲得まで待つ
	 */
	protected void waitUntilPointGet() {
		// 数字認証
		boolean b = false;
		while(!b) {
			// フレームがあるか
			b = existsElement("frameset");
			if(!b) {
				// 数字認証画像があるか
				if(existsElement(String.format("img[src*='%s']", site.getNumberAuthImage()))) {
					// ログ
					log("数字認証を要求されています…");
					// 数字を入力
					String n = JOptionPane.showInputDialog(mainThread.getFrame(), "数字認証");
					setValue(String.format("input[name='%s']", site.getNumberAuthInput()), n);
					click("input[type='submit']");
					// ログ
					log("数字認証を入力しました。");
					// ページ読み込み完了を待つ
					waitLoaded();
				} else {
					// ログ
					log("ポイント獲得失敗");
					return;
				}
			}
		}
		// ログ
		log("ポイント獲得を待っています…");
		// ポイント獲得まで待つ
		driver.switchTo().frame(site.getPointGetFrame());
		waitPointGet();
		driver.switchTo().window(driver.getWindowHandle());
		// ログ
		log("ポイントを獲得しました。");
	}
}
