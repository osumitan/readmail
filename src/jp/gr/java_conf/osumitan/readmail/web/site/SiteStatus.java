package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Function;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトステータス
 */
public enum SiteStatus {

	/** ログインする */
	LOGIN(LoginThread::new),
	/** 処理前報酬を取得する */
	EARNINGS_BEFORE(EarningsBeforeThread::new),
	/** クリックポイント */
	CLICK_POINT(ClickPointThread::new),
	/** 受信箱 */
	INBOX(InboxThread::new),
	/** 処理後報酬を取得する */
	EARNINGS_AFTER(EarningsAfterThread::new),
	/** 支払請求 */
	PAY_REQUEST(PayRequestThread::new),
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
