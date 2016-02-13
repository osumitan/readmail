package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.BorderLayout;

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
		setResizable(false);

		// NORTH
		JPanel north = new JPanel();
		add(north, BorderLayout.NORTH);
		// スタートボタン
		this.startButton = new StartButton(this);
		north.add(this.startButton);

		// CENTER
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		// サイト表
		this.siteTable = new SiteTable(this);
		JScrollPane spSiteTable = new JScrollPane(this.siteTable);
		center.add(spSiteTable);
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
