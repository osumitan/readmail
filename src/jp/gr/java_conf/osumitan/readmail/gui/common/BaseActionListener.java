package jp.gr.java_conf.osumitan.readmail.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * アクションリスナーの基底クラス
 */
public abstract class BaseActionListener<F extends BaseFrame> implements ActionListener, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1757008332146023140L;

	/** フレーム */
	protected F frame;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public BaseActionListener(F frame) {
		// フレーム
		this.frame = frame;
	}

	/**
	 * イベント実行
	 * @param e イベント
	 */
	public abstract void actionPerformed(ActionEvent e);
}
