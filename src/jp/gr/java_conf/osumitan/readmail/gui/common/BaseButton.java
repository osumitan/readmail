package jp.gr.java_conf.osumitan.readmail.gui.common;

import javax.swing.JButton;

/**
 * ボタンの基底クラス
 */
public abstract class BaseButton<F extends BaseFrame> extends JButton {

/** フレーム */
protected F frame;

	/** serialVersionUID */
	private static final long serialVersionUID = -5978023895321622042L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 * @param text テキスト
	 * @param onClick ボタン押下時イベントリスナー
	 */
	public BaseButton(F frame, String text, BaseActionListener<F> onClick) {
		super();
		this.frame = frame;
		setText(text);
		addActionListener(onClick);
	}
}
