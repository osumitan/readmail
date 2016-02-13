package jp.gr.java_conf.osumitan.readmail.gui.common;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * フレームの基底クラス
 */
public abstract class BaseFrame extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 1531309703617832923L;

	/**
	 * コンストラクタ
	 * @param title タイトル
	 */
	public BaseFrame(String title) {
		super();
		// ×押下で閉じる
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// タイトル
		setTitle(title);
	}

	/**
	 * メッセージ表示
	 * @param title タイトル
	 * @param message メッセージ
	 */
	public void showMessage(String title, String message) {
		// メッセージ表示
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
	}
}
