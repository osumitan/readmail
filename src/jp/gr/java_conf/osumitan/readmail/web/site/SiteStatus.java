package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Function;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトステータス
 */
public enum SiteStatus {

	/** ログインページを開く */
	OPEN_LOGIN_PAGE(OpenLoginPageThread::new),
	/** ログインする */
	LOGIN(LoginThread::new),
	/** ログアウトする */
	LOGOUT(null),
	/** 終了 */
	FINISHED(null);

	/** 初期ステータス */
	public static final SiteStatus INITIAL_STATUS = OPEN_LOGIN_PAGE;

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
