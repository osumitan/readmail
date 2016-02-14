package jp.gr.java_conf.osumitan.readmail.web.main;

import jp.gr.java_conf.osumitan.readmail.gui.main.MainFrame;
import jp.gr.java_conf.osumitan.readmail.web.common.BaseThread;

/**
 * メインスレッド
 */
public class MainThread extends BaseThread {

	/** フレーム */
	private MainFrame frame;

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
	protected void prepare() {
		// ログクリア
		this.frame.clearLog();
		// ログ
		log("開始");
		// 画面制御
		setComponentEnabled(false);
	}

	/**
	 * 中断時処理
	 */
	protected void onAborted() {
		// ログ
		log("中断");
		// 画面制御
		setComponentEnabled(true);
	}

	/**
	 * 正常終了時処理
	 */
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
	protected void onAbended(Throwable ex) {
		// ログ
		log(String.format("異常終了:%s", String.valueOf(ex)));
		// 画面制御
		setComponentEnabled(true);
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

	private int t = 0, c = 0;
	/**
	 * メイン
	 */
	protected void main() {
		t++;
		if(t>=10) {
			log(String.format("ログ出力:%d", ++c));
			t=0;
			if(c >= 30) {
				this.runningStatus = RunningStatus.FINISHED;
			}
		}
	}
}
