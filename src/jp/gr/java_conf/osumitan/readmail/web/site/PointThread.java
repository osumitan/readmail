package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：ポイント取得スレッドの基底クラス
 */
public abstract class PointThread extends BaseSiteThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public PointThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * ポイントリンクを開く
	 * @param pointLink ポイントリンク
	 */
	protected void navigatePointLink(String pointLink) {
		// クリックポイントを開く
		WebElement link = findElement(String.format("a[href*='%s']", pointLink));
		get(link.getAttribute("href") + site.getPointLinkParameter());
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
					// 数字認証処理
					site.getNumberAuthType().getProcessor().accept(this);
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
		driver.switchTo().window(mainThread.getWindowHander());
		// 広告ウィンドウを閉じる
		if(site.hasAdWindow()) {
			driver.switchTo().window(site.getAdWindow());
			driver.close();
			driver.switchTo().window(mainThread.getWindowHander());
		}
		// ログ
		log("ポイントを獲得しました。");
	}

	/**
	 * ポイント獲得を待つ
	 */
	protected void waitPointGet() {
		// 報酬獲得メッセージ表示まで待つ
		until((driver) -> existsElement(By.xpath(String.format(site.getPointGetMessagePath(), site.getPointGetMessage()))));
	}

	/**
	 * 数字認証処理：テキスト
	 */
	private void processNumberAuthText() {
		// 数字を入力してボタン押下
		String n = JOptionPane.showInputDialog(mainThread.getFrame(), "数字認証", site.getNumberAuthType().toString(), JOptionPane.QUESTION_MESSAGE);
		setValue(String.format("input[name='%s']", site.getNumberAuthName()), n);
		click("input[type='submit']");
	}

	/**
	 * 数字認証処理：リンク
	 */
	private void processNumberAuthLink() {
		// 数字を入力してリンクをクリック
		boolean b = false;
		while(!b) {
			String n = JOptionPane.showInputDialog(mainThread.getFrame(), "数字認証", site.getNumberAuthType().toString(), JOptionPane.QUESTION_MESSAGE);
			String selector = String.format("a[href*='%s=%s']", site.getNumberAuthName(), n);
			b = existsElement(selector);
			if(b) {
				click(selector);
			}
		}
	}

	/**
	 * 数字認証タイプ
	 */
	public enum NumberAuthType {
		/** テキスト */
		TEXT(PointThread::processNumberAuthText),
		/** リンク */
		LINK(PointThread::processNumberAuthLink);

		/** 数字認証処理 */
		private Consumer<PointThread> processor;

		/**
		 * コンストラクタ
		 * @param processor 数字認証処理
		 */
		private NumberAuthType(Consumer<PointThread> processor) {
			// 数字認証処理
			this.processor = processor;
		}

		/**
		 * @return 数字認証処理
		 */
		public Consumer<PointThread> getProcessor() {
			return processor;
		}
	}

}
