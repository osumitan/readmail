package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseFrame;
import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * メインフレーム
 */
public class MainFrame extends BaseFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -7242896871386666060L;

	/** メインスレッド */
	private MainThread mainThread;
	/** 開始ボタン */
	private StartButton startButton;
	/** 停止ボタン */
	private StopButton stopButton;
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
		setSize(600, 600);

		// メインスレッド
		this.mainThread = null;
		// 開始ボタン
		this.startButton = new StartButton(this);
		// 停止ボタン
		this.stopButton = new StopButton(this);
		// サイト表
		this.siteTable = new SiteTable(this);
		// ログリスト
		this.logList = new LogList(this);

		// NORTH
		JPanel np = new JPanel(new FlowLayout(FlowLayout.LEFT));
		np.add(this.startButton);
		np.add(this.stopButton);
		add(np, BorderLayout.NORTH);

		// CENTER
		JScrollPane cpSiteTable = new JScrollPane(this.siteTable);
		add(cpSiteTable, BorderLayout.CENTER);

		// SOUTH
		JScrollPane cpLogList = new JScrollPane(logList);
		add(cpLogList, BorderLayout.SOUTH);
	}

	/**
	 * ログクリア
	 */
	public void clearLog() {
		this.logList.clear();
	}

	/**
	 * ログ出力
	 * @param message メッセージ
	 */
	public void log(String message) {
		this.logList.add(message);
	}

	/**
	 * 例外ログ出力
	 * @param ex 例外
	 */
	public void log(Throwable ex) {
		log(ex.toString());
		for(StackTraceElement st : ex.getStackTrace()) {
			log(st.toString());
		}
		Throwable cause = ex.getCause();
		if(cause != null) {
			log("cause...");
			log(cause);
		}
	}

	/**
	 * @return メインスレッド
	 */
	public MainThread getMainThread() {
		return mainThread;
	}

	/**
	 * @param mainThread メインスレッド
	 */
	public void setMainThread(MainThread mainThread) {
		this.mainThread = mainThread;
	}

	/**
	 * @return 開始ボタン
	 */
	public StartButton getStartButton() {
		return startButton;
	}

	/**
	 * @return 停止ボタン
	 */
	public StopButton getStopButton() {
		return stopButton;
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
