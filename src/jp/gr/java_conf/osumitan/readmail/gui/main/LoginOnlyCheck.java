package jp.gr.java_conf.osumitan.readmail.gui.main;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseCheckBox;

/**
 * ログインのみチェックボックス
 */
public class LoginOnlyCheck extends BaseCheckBox<MainFrame> {

	/** serialVersionUID */
	private static final long serialVersionUID = 3491165445499799614L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public LoginOnlyCheck(MainFrame frame) {
		super(frame, "ログインのみ");
	}
}
