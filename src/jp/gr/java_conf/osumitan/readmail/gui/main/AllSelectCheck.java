package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.event.ActionEvent;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseActionListener;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseCheckBox;
import jp.gr.java_conf.osumitan.readmail.web.site.Site;

/**
 * 全選択チェックボックス
 */
public class AllSelectCheck extends BaseCheckBox<MainFrame> {

	/** serialVersionUID */
	private static final long serialVersionUID = -1220244904062289954L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public AllSelectCheck(MainFrame frame) {
		super(frame, "全選択");
		// クリック時イベントリスナー
		addActionListener(new OnClick(frame));
		// 選択状態
		setSelected(true);
	}

	/**
	 * クリック時
	 */
	private static class OnClick extends BaseActionListener<MainFrame> {

		/** serialVersionUID */
		private static final long serialVersionUID = -7543385626450446144L;

		/**
		 * コンストラクタ
		 * @param frame
		 */
		public OnClick(MainFrame frame) {
			super(frame);
		}

		/**
		 * クリック時処理
		 * @param evt イベント
		 */
		@Override
		public void actionPerformed(ActionEvent evt) {
			// 選択状態を全変更
			for(Site site : frame.getSiteTable().getDataList()) {
				site.setSelected(frame.getAllSelectCheck().isSelected());
			}
			// サイトテーブルを再描画
			frame.getSiteTable().repaint();
		}
	}
}
