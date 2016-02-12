package jp.gr.java_conf.osumitan.readmail.gui.main;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseFrame;

/**
 * メインフレーム
 */
public class MainFrame extends BaseFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7242896871386666060L;

	/**
	 * デフォルトコンストラクタ
	 */
	public MainFrame() {
		super("readmail");
		setSize(800, 450);
		south.add(new StartButton(this));
	}
}
