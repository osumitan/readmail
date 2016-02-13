package jp.gr.java_conf.osumitan.readmail.gui.common;

import java.util.List;
import java.util.function.Function;

import javax.swing.table.AbstractTableModel;

/**
 * テーブルモデルの基底クラス
 * @param <D> データ
 */
public abstract class BaseTableModel<D> extends AbstractTableModel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1622444825574635669L;

	/** データリスト */
	protected List<D> dataList;
	/** 列情報リスト */
	protected List<Column> columnList;

	/**
	 * コンストラクタ
	 * @param dataList データリスト
	 * @param columnList 列情報リスト
	 */
	public BaseTableModel(List<D> dataList, List<Column> columnList) {
		super();
		// データリスト
		this.dataList = dataList;
		// 列情報リスト
		this.columnList = columnList;
	}

	/**
	 * 行数
	 * @return 行数
	 */
	@Override
	public int getRowCount() {
		return this.dataList.size();
	}

	/**
	 * 列数
	 * @return 列数
	 */
	@Override
	public int getColumnCount() {
		return this.columnList.size();
	}

	/**
	 * 列名
	 * @param c 列番号
	 * @return 列名
	 */
	@Override
	public String getColumnName(int c) {
		return this.columnList.get(c).getName();
	}

	/**
	 * 列値クラス
	 * @param c 列番号
	 * @return 列値クラス
	 */
	@Override
	public Class<?> getColumnClass(int c) {
		return this.columnList.get(c).getValueClass();
	}

	/**
	 * 列値取得
	 * @param r 行番号
	 * @param c 列番号
	 * @return 列値
	 */
	@Override
	public Object getValueAt(int r, int c) {
		return this.columnList.get(c).getGetter().apply(this.dataList.get(r));
	}

	/**
	 * @return データリスト
	 */
	public List<D> getDataList() {
		return dataList;
	}

	/**
	 * 列情報
	 */
	public class Column {

		/** 列名 */
		private String name;
		/** 列値クラス */
		private Class<?> valueClass;
		/** 列値Getter */
		private Function<D, ?> getter;

		/**
		 * コンストラクタ
		 * @param name 列名
		 * @param valueClass 列値クラス
		 * @param getter 列値Getter
		 */
		public Column(String name, Class<?> valueClass, Function<D, ?> getter) {
			// 列名
			this.name = name;
			// 列値クラス
			this.valueClass = valueClass;
			// 列値Getter
			this.getter = getter;
		}

		/**
		 * @return 列名
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return valueClass 列値クラス
		 */
		public Class<?> getValueClass() {
			return valueClass;
		}

		/**
		 * @return 列値Getter
		 */
		public Function<D, ?> getGetter() {
			return getter;
		}
	}
}
