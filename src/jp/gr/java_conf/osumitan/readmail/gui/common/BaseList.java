package jp.gr.java_conf.osumitan.readmail.gui.common;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * リストの基底クラス
 * @param <F> フレーム
 * @param <D> データ
 */
public abstract class BaseList<F extends BaseFrame, D> extends JList<D> {

	/** serialVersionUID */
	private static final long serialVersionUID = -5784819305981856663L;

	/** フレーム */
	protected F frame;
	/** リストモデル */
	protected DefaultListModel<D> listModel;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 * @param listModel リストモデル
	 */
	public BaseList(F frame, DefaultListModel<D> listModel) {
		super(listModel);
		// フレーム
		this.frame = frame;
		// リストモデル
		this.listModel = listModel;
	}

	/**
	 * 追加
	 * @param data データ
	 */
	public void add(D data) {
		this.listModel.addElement(data);
	}
}
