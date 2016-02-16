package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseTable;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseTableModel;
import jp.gr.java_conf.osumitan.readmail.web.site.Site;

/**
 * サイト表
 */
public class SiteTable extends BaseTable<MainFrame, Site> {

	/** serialVersionUID */
	private static final long serialVersionUID = 7524571069380866881L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public SiteTable(MainFrame frame) {
		super(frame, Site.createSiteList());
	}

	/**
	 * テーブルモデル生成
	 * @param dataList データリスト
	 * @return テーブルモデル
	 */
	protected SiteTableModel createTableModel(List<Site> dataList) {
		return new SiteTableModel(dataList);
	}

	/**
	 * 編集可否
	 * @param r 行番号
	 * @param c 列番号
	 * @return 編集可否
	 */
	@Override
	public boolean isCellEditable(int r, int c) {
		// 選択チェックのみ可
		return c == 0;
	}

	/**
	 * 列値設定
	 * @param value 列値
	 * @param r 行番号
	 * @param c 列番号
	 */
	@Override
	public void setValueAt(Object value, int r, int c) {
		// 選択チェックのみ受付
		if(c == 0) {
			dataList.get(r).setSelected(Boolean.valueOf(String.valueOf(value)));
		}
	}

	/**
	 * データリストをクリア
	 */
	public void clearDataList() {
		for(Site site : dataList) {
			site.clear();
		}
	}

	/**
	 * テーブルモデル
	 */
	static class SiteTableModel extends BaseTableModel<Site> {

		/** serialVersionUID */
		private static final long serialVersionUID = -3672448125451096120L;

		/**
		 * 列情報
		 */
		private static List<Column> COLUMN_LIST = new ArrayList<Column>();

		/**
		 * コンストラクタ
		 * @param dataList データリスト
		 */
		SiteTableModel(List<Site> dataList) {
			super(dataList, COLUMN_LIST);
			// 列情報
			COLUMN_LIST.clear();
			COLUMN_LIST.add(new Column("選択", 100, Boolean.class, Site::isSelected));
			COLUMN_LIST.add(new Column("サイト名", 500, String.class, Site::getName));
			COLUMN_LIST.add(new Column("処理前報酬", 200, Double.class, Site::getEarningsBefore));
			COLUMN_LIST.add(new Column("処理後報酬", 200, Double.class, Site::getEarningsAfter));
		}
	}
}
