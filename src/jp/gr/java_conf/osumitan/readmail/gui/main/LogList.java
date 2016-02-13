package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseList;

/**
 * ログリスト
 */
public class LogList extends BaseList<MainFrame, String> {

	/** serialVersionUID */
	private static final long serialVersionUID = 8458211048886200057L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public LogList(MainFrame frame) {
		super(frame, new DefaultListModel<String>());
	}

	/**
	 * 追加
	 * @param log ログ
	 */
	public void add(String log) {
		// ログ追加
		super.add(String.format("[%s] %s", new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()), log));
		// 最新行を表示
		ensureIndexIsVisible(super.listModel.size() - 1);
	}
}
