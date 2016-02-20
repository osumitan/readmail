package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.event.ActionEvent;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseActionListener;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseButton;
import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * 開始ボタン
 */
public class StartButton extends BaseButton<MainFrame> {

	/** serialVersionUID */
	private static final long serialVersionUID = 2390862907751037255L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public StartButton(MainFrame frame) {
		super(frame, "開始", new OnClick(frame));
	}

	/**
	 * ボタン押下時
	 */
	private static class OnClick extends BaseActionListener<MainFrame> {

		/** serialVersionUID */
		private static final long serialVersionUID = 4621489862790816764L;

		/**
		 * コンストラクタ
		 * @param frame フレーム
		 */
		OnClick(MainFrame frame) {
			super(frame);
		}

		/**
		 * ボタン押下時処理
		 * @param evt イベント
		 */
		@Override
		public void actionPerformed(ActionEvent evt) {
			// 選択サイト数チェック
			int cnt = frame.getSiteTable().getSelectedCount();
			if(frame.getLoginOnlyCheck().isSelected()) {
				// ログインのみ時は1件のみ選択
				if(cnt != 1) {
					frame.showMessage("エラー", "ログインのみ時は1サイトのみ選択してください。");
					return;
				}
			} else {
				// 通常時は1件以上選択
				if(cnt < 1) {
					frame.showMessage("エラー", "サイトを選択してください。");
					return;
				}
			}
			// メインスレッド開始
			MainThread mainThread = new MainThread(frame);
			frame.setMainThread(mainThread);
			mainThread.start();
		}
	}
}
