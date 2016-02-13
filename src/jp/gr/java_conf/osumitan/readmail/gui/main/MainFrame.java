package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseFrame;

/**
 * メインフレーム
 */
public class MainFrame extends BaseFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -7242896871386666060L;

	/** スタートボタン */
	private StartButton startButton;
	/** サイト表 */
	private SiteTable siteTable;
	/** ログリスト */
	private LogList logList;

	/**
	 * メイン
	 * @param args 引数
	 */
	public static void main(String[] args) {
		// メインフレームを表示
		new MainFrame().setVisible(true);
	}

	/**
	 * デフォルトコンストラクタ
	 */
	public MainFrame() {

		super("readmail");
		// サイズ
		setSize(800, 600);

		// スタートボタン
		this.startButton = new StartButton(this);
		// サイト表
		this.siteTable = new SiteTable(this);
		// ログリスト
		this.logList = new LogList(this);

		// NORTH
		JPanel np = new JPanel(new FlowLayout(FlowLayout.LEFT));
		np.add(this.startButton);
		add(np, BorderLayout.NORTH);

		// CENTER
		JScrollPane cpSiteTable = new JScrollPane(this.siteTable);
		add(cpSiteTable, BorderLayout.CENTER);

		// SOUTH
		JScrollPane cpLogList = new JScrollPane(logList);
		add(cpLogList, BorderLayout.SOUTH);
	}

	/**
	 * ログ追加
	 * @param log ログ
	 */
	public void addLog(String log) {
		this.logList.add(log);
	}

	/**
	 * @return スタートボタン
	 */
	public StartButton getStartButton() {
		return startButton;
	}

	/**
	 * @return サイト表
	 */
	public SiteTable getSiteTable() {
		return siteTable;
	}

	/**
	 * @return ログリスト
	 */
	public LogList getLogList() {
		return logList;
	}
}
