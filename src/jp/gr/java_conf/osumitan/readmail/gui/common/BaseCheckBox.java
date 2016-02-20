package jp.gr.java_conf.osumitan.readmail.gui.common;

import javax.swing.JCheckBox;

/**
 * チェックボックスの基底クラス
 * @param <F> フレーム
 */
public class BaseCheckBox<F extends BaseFrame> extends JCheckBox {

	/** serialVersionUID */
	private static final long serialVersionUID = -2800455696825398193L;

	/** フレーム */
	protected F frame;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 * @param text テキスト
	 */
	public BaseCheckBox(F frame, String text) {
		super();
		// フレーム
		this.frame = frame;
		// テキスト
		setText(text);
	}
}
