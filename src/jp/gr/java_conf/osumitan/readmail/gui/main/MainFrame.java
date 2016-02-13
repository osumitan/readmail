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
		setSize(800, 450);

		// スタートボタン
		this.startButton = new StartButton(this);
		// サイト表
		this.siteTable = new SiteTable(this);
		add(this.siteTable, BorderLayout.CENTER);

		// NORTH
		JPanel np = new JPanel(new FlowLayout(FlowLayout.LEFT));
		np.add(this.startButton);
		add(np, BorderLayout.NORTH);

		// CENTER
		JScrollPane cp = new JScrollPane(this.siteTable);
		add(cp, BorderLayout.CENTER);
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
}
