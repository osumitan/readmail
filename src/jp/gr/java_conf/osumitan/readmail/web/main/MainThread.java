package jp.gr.java_conf.osumitan.readmail.web.main;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import jp.gr.java_conf.osumitan.readmail.gui.main.MainFrame;
import jp.gr.java_conf.osumitan.readmail.web.common.BaseThread;
import jp.gr.java_conf.osumitan.readmail.web.site.BaseSiteThread;
import jp.gr.java_conf.osumitan.readmail.web.site.Site;
import jp.gr.java_conf.osumitan.readmail.web.site.SiteStatus;

/**
 * メインスレッド
 */
public class MainThread extends BaseThread implements UncaughtExceptionHandler {

	/** フレーム */
	private MainFrame frame;
	/** ドライバ */
	private RemoteWebDriver driver;
	/** サイトインデックス */
	private int siteIndex;
	/** サイトステータス */
	private SiteStatus siteStatus;
	/** サイトスレッド */
	private BaseSiteThread siteThread;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public MainThread(MainFrame frame) {
		super();
		// フレーム
		this.frame = frame;
	}

	/**
	 * 準備
	 */
	@Override
	protected void preProcess() {
		// ログクリア
		this.frame.clearLog();
		// ログ
		log("開始");
		// 画面制御
		setComponentEnabled(false);
		// データリストクリア
		frame.getSiteTable().clearDataList();
		// ドライバ生成
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
		this.driver = new ChromeDriver();
		this.driver.manage().window().setSize(new Dimension(800, 600));
		this.driver.manage().window().setPosition(new Point(520, 120));
		// サイトインデックス
		this.siteIndex = 0;
		// サイトステータス
		this.siteStatus = SiteStatus.INITIAL_STATUS;
		// サイトスレッド
		this.siteThread = null;
	}

	/**
	 * メイン
	 */
	@Override
	protected void main() {
		// 現在処理中のサイト情報
		Site site = getCurrentSite();
		if(site == null) {
			// 全サイト終了
			this.frame.log("全サイト終了");
			runningStatus = RunningStatus.FINISHED;
			return;
		}
		// 選択されていないサイトはスキップ
		if(!site.isSelected()) {
			nextSite();
			return;
		}
		// サイトスレッドが実行中なら待つ
		if(this.siteThread != null && this.siteThread.isAlive()) {
			return;
		}
		// サイトスレッドのコンストラクタを取得
		Function<MainThread, BaseSiteThread> constructor = this.siteStatus.getConstructor();
		if(constructor == null) {
			// サイト処理終了
			nextSite();
			return;
		}
		// サイトスレッド実行
		this.siteThread = constructor.apply(this);
		this.siteThread.setUncaughtExceptionHandler(this);
		this.siteThread.start();
	}

	/**
	 * 中断時処理
	 */
	@Override
	protected void onAborted() {
		// ログ
		log("中断");
		// 画面制御
		setComponentEnabled(true);
	}

	/**
	 * 正常終了時処理
	 */
	@Override
	protected void onFinished() {
		// ログ
		log("正常終了");
		// 画面制御
		setComponentEnabled(true);
	}

	/**
	 * 異常終了時処理
	 * @param ex 例外
	 */
	@Override
	protected void onAbended(Throwable ex) {
		// ログ
		log(ex);
		log(String.format("異常終了"));
		// 画面制御
		setComponentEnabled(true);
	}

	/**
	 * 終了処理
	 */
	@Override
	protected void postProcess() {
		// ドライバ破棄
//TODO ブラウザを閉じるのをやめておく
//		this.driver.quit();
	}

	/**
	 * サブスレッドの例外をキャッチ
	 * @param subThread サブスレッド
	 * @param ex 例外
	 */
	@Override
	public void uncaughtException(Thread subThread, Throwable ex) {
		this.subThreadThrowable = ex;
	}

	/**
	 * 画面制御
	 * @param enabled 有効無効
	 */
	private void setComponentEnabled(boolean enabled) {
		// 開始ボタン
		this.frame.getStartButton().setEnabled(enabled);
		// 停止ボタン
		this.frame.getStopButton().setEnabled(!enabled);
		// サイト表
		this.frame.getSiteTable().setEnabled(enabled);
	}

	/**
	 * ログ出力
	 * @param message メッセージ
	 */
	private void log(String message) {
		this.frame.log(message);
	}

	/**
	 * 例外をログ出力
	 * @param ex 例外
	 */
	private void log(Throwable ex) {
		this.frame.log(ex);
	}

	/**
	 * @return フレーム
	 */
	public MainFrame getFrame() {
		return frame;
	}

	/**
	 * @return ドライバ
	 */
	public RemoteWebDriver getDriver() {
		return driver;
	}

	/**
	 * 現在処理中のサイト情報
	 */
	public Site getCurrentSite() {
		List<Site> siteList = this.frame.getSiteTable().getDataList();
		if(0 <= this.siteIndex && this.siteIndex < siteList.size()) {
			return siteList.get(this.siteIndex);
		} else {
			return null;
		}
	}

	/**
	 * @return サイトインデックス
	 */
	public int getSiteIndex() {
		return siteIndex;
	}

	/**
	 * 次のサイトへ
	 */
	public void nextSite() {
		// サイトインデックスを繰り上げ
		this.siteIndex++;
		// サイトステータスを初期化
		this.siteStatus = SiteStatus.INITIAL_STATUS;
		// サイトスレッド
		this.siteThread = null;
	}

	/**
	 * @return サイトステータス
	 */
	public SiteStatus getSiteStatus() {
		return siteStatus;
	}

	/**
	 * @param siteStatus サイトステータス
	 */
	public void setSiteStatus(SiteStatus siteStatus) {
		this.siteStatus = siteStatus;
	}
}
