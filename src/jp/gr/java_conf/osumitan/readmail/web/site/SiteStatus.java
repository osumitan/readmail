package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Function;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトステータス
 */
public enum SiteStatus {

	/** ログインする */
	LOGIN(LoginThread::new),
	/** 報酬明細を開く */
	EARNINGS(EarningsThread::new),
	/** ログアウトする */
	LOGOUT(LogoutThread::new),
	/** 正常終了 */
	FINISHED(null),
	/** 異常終了 */
	ABENDED(null);

	/** 初期ステータス */
	public static final SiteStatus INITIAL_STATUS = LOGIN;

	/** サイトスレッドのコンストラクタ参照 */
	private Function<MainThread, BaseSiteThread> constructor;

	/**
	 * コンストラクタ
	 * @param constructor サイトスレッドのコンストラクタ参照
	 */
	private SiteStatus(Function<MainThread, BaseSiteThread> constructor) {
		// サイトスレッドのコンストラクタ参照
		this.constructor = constructor;
	}

	/**
	 * @return サイトスレッドのコンストラクタ参照
	 */
	public Function<MainThread, BaseSiteThread> getConstructor() {
		return constructor;
	}
}
