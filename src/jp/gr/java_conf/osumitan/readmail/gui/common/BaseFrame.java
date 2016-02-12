package jp.gr.java_conf.osumitan.readmail.gui.common;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * フレームの基底クラス
 */
public abstract class BaseFrame extends JFrame {

protected JPanel south;

	/** serialVersionUID */
	private static final long serialVersionUID = 1531309703617832923L;

	/**
	 * コンストラクタ
	 * @param title タイトル
	 */
	public BaseFrame(String title) {
		super();
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.south = new JPanel();
		add(this.south, BorderLayout.SOUTH);
	}
	
	/**
	 * メッセージ表示
	 * @param title タイトル
	 * @param message メッセージ
	 */
	public void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
	}
}
