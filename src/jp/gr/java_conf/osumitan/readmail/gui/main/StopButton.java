package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.event.ActionEvent;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseActionListener;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseButton;

/**
 * 停止ボタン
 */
public class StopButton extends BaseButton<MainFrame> {

	/** serialVersionUID */
	private static final long serialVersionUID = 4374935913103186470L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public StopButton(MainFrame frame) {
		super(frame, "停止", new OnClick(frame));
		// デフォルト無効
		setEnabled(false);
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
			// メインスレッド停止
			frame.getMainThread().abort();
		}
	}}
