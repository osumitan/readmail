package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

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
		driver.switchTo().window(driver.getWindowHandle());
		// ログ
		log("ポイントを獲得しました。");
	}

	/**
	 * 数字認証処理：テキスト
	 */
	private void processNumberAuthText() {
		// 数字を入力してボタン押下
		String n = JOptionPane.showInputDialog(mainThread.getFrame(), "数字認証");
		setValue(String.format("input[name='%s']", site.getNumberAuthName()), n);
		click("input[type='submit']");
	}

	/**
	 * 数字認証処理：リンク
	 */
	private void processNumberAuthLink() {
		// 数字を入力してリンクをクリック
		String n = JOptionPane.showInputDialog(mainThread.getFrame(), "数字認証");
		click(String.format("a[href*='%s=%s']", site.getNumberAuthName(), n));
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
