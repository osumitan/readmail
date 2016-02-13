package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseTable;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseTableModel;
import jp.gr.java_conf.osumitan.readmail.web.Site;

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
			COLUMN_LIST.add(new Column("ドメイン", Site::getDomain));
			COLUMN_LIST.add(new Column("サイト名", Site::getName));
			COLUMN_LIST.add(new Column("ログインページ", Site::getLoginPage));
			COLUMN_LIST.add(new Column("ログインID", Site::getLoginId));
			COLUMN_LIST.add(new Column("ログインパスワード", Site::getLoginPassword));
		}
	}
}
