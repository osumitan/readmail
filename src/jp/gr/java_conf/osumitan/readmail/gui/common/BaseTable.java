package jp.gr.java_conf.osumitan.readmail.gui.common;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 * テーブルの基底クラス
 */
public abstract class BaseTable<F extends BaseFrame, D> extends JTable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2217425086533264838L;

	/** フレーム */
	protected F frame;
	/** データリスト */
	protected List<D> dataList;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 * @param dataList データリスト
	 */
	public BaseTable(F frame, List<D> dataList) {
		super();
		// フレーム
		this.frame = frame;
		// データリスト
		this.dataList = dataList;
		// テーブルモデル設定
		BaseTableModel<D> model = createTableModel(dataList);
		setModel(model);
		// 列幅設定
		TableColumnModel tcm = getColumnModel();
		for(int i = 0 ; i < tcm.getColumnCount() ; i++) {
			tcm.getColumn(i).setPreferredWidth(model.getColumnList().get(i).getWidth());
		}
	}

	/**
	 * @return データリスト
	 */
	public List<D> getDataList() {
		return this.dataList;
	}

	/**
	 * テーブルモデル生成
	 * @param dataList データリスト
	 * @return テーブルモデル
	 */
	protected abstract BaseTableModel<D> createTableModel(List<D> dataList);
}
