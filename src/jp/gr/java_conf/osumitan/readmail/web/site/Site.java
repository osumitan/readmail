package jp.gr.java_conf.osumitan.readmail.web.site;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.arnx.jsonic.JSON;

/**
 * サイト情報
 */
public class Site {

	/** 選択 */
	private boolean selected;
	/** サイト名 */
	private String name;
	/** 処理前報酬 */
	private Double earningsBefore;
	/** 処理後報酬 */
	private Double earningsAfter;
	/** ドメイン */
	private String domain;
	/** ページプレフィックス */
	private String pagePrefix;
	/** ログインページ */
	private String loginPage;
	/** ユーザ名 */
	private String userName;
	/** パスワード */
	private String password;
	/** 報酬明細ページ */
	private String earningsPage;
	/** 報酬パス */
	private String earningsPath;
	/** 報酬正規表現 */
	private String earningsRegexp;
	/** クリックポイントページ */
	private String clickPointPage;
	/** クリックポイントリンク */
	private String clickPointLink;
	/** 数字認証画像 */
	private String numberAuthImage;
	/** 数字認証入力項目 */
	private String numberAuthInput;
	/** ポイント取得フレーム */
	private String pointGetFrame;
	/** ポイント取得メッセージ */
	private String pointGetMessage;
	/** ポイント取得メッセージパス */
	private String pointGetMessagePath;
	/** ログアウトページ */
	private String logoutPage;

	/** デフォルト：ページプレフィックス */
	private static final String DEFAULT_PAGE_PREFIX = "pages/";
	/** デフォルト：ログインページ */
	private static final String DEFAULT_LOGIN_PAGE = "enter.php";
	/** デフォルト：報酬明細ページ */
	private static final String DEFAULT_EARNINGS_PAGE = "earnings.php";
	/** デフォルト：報酬パス */
	private static final String DEFAULT_EARNINGS_PATH = "//*[contains(text(),'合計ポイント')]/ancestor::tr[1]/td[2]//text()/..";
	/** デフォルト：報酬正規表現 */
	private static final String DEFAULT_EARNINGS_REGEXP = "(\\d+\\.\\d+)";
	/** デフォルト：クリックポイントページ */
	private static final String DEFAULT_CLICK_POINT_PAGE = "ptc.php";
	/** デフォルト：クリックポイントリンク */
	private static final String DEFAULT_CLICK_POINT_LINK = "scripts/runner.php?PA=";
	/** デフォルト：数字認証画像 */
	private static final String DEFAULT_NUMBER_AUTH_IMAGE = "scripts/runner.php?TN=";
	/** デフォルト：数字認証入力項目 */
	private static final String DEFAULT_NUMBER_AUTH_INPUT = "PI";
	/** デフォルト：ポイント取得フレーム */
	private static final String DEFAULT_POINT_GET_FRAME = "timerfrm";
	/** デフォルト：ポイント取得メッセージ */
	private static final String DEFAULT_POINT_GET_MESSAGE = "報酬が加算されました";
	/** デフォルト：ポイント取得メッセージパス */
	private static final String DEFAULT_POINT_GET_MESSAGE_PATH = "//*[contains(text(),'%s')]/..";
	/** デフォルト：ログアウトページ */
	private static final String DEFAULT_LOGOUT_PAGE = "index.php?username=LOGOUT&password=LOGOUT";

	/**
	 * サイトリスト生成
	 * @return サイトリスト
	 */
	public static List<Site> createSiteList() {
		try {
			// JSONファイルからサイトリスト生成
			return Arrays.asList(JSON.decode(new BufferedReader(new FileReader("./json/site-list.json")), Site[].class));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * コンストラクタ
	 */
	public Site() {
		// 選択
		this.selected = true;
		// ページプレフィックス
		this.pagePrefix = DEFAULT_PAGE_PREFIX;
		// ログインページ
		this.loginPage = DEFAULT_LOGIN_PAGE;
		// 報酬明細ページ
		this.earningsPage = DEFAULT_EARNINGS_PAGE;
		// 報酬パス
		this.earningsPath = DEFAULT_EARNINGS_PATH;
		// 報酬正規表現
		this.earningsRegexp = DEFAULT_EARNINGS_REGEXP;
		// クリックポイントページ
		this.clickPointPage = DEFAULT_CLICK_POINT_PAGE;
		// クリックポイントリンク
		this.clickPointLink = DEFAULT_CLICK_POINT_LINK;
		// 数字認証画像
		this.numberAuthImage = DEFAULT_NUMBER_AUTH_IMAGE;
		// 数字認証入力項目
		this.numberAuthInput = DEFAULT_NUMBER_AUTH_INPUT;
		// ポイント取得フレーム
		this.pointGetFrame = DEFAULT_POINT_GET_FRAME;
		// ポイント取得メッセージ
		this.pointGetMessage = DEFAULT_POINT_GET_MESSAGE;
		// ポイント取得メッセージパス
		this.pointGetMessagePath = DEFAULT_POINT_GET_MESSAGE_PATH;
		// ログアウトページ
		this.logoutPage = DEFAULT_LOGOUT_PAGE;
		// クリア
		this.clear();
	}

	/**
	 * クリア
	 */
	public void clear() {
		// 処理前報酬
		this.earningsBefore = null;
		// 処理後報酬
		this.earningsAfter = null;
	}

	/**
	 * @return 選択
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected 選択
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return サイト名
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name サイト名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 処理前報酬
	 */
	public Double getEarningsBefore() {
		return earningsBefore;
	}

	/**
	 * @param earningsBefore 処理前報酬
	 */
	public void setEarningsBefore(Double earningsBefore) {
		this.earningsBefore = earningsBefore;
	}

	/**
	 * @return 処理後報酬
	 */
	public Double getEarningsAfter() {
		return earningsAfter;
	}

	/**
	 * @param earningsAfter 処理後報酬
	 */
	public void setEarningsAfter(Double earningsAfter) {
		this.earningsAfter = earningsAfter;
	}

	/**
	 * @return ドメイン
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain ドメイン
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return ページプレフィックス
	 */
	public String getPagePrefix() {
		return pagePrefix;
	}

	/**
	 * @param pagePrefix ページプレフィックス
	 */
	public void setPagePrefix(String pagePrefix) {
		this.pagePrefix = pagePrefix;
	}

	/**
	 * @return ログインページ
	 */
	public String getLoginPage() {
		return loginPage;
	}

	/**
	 * @param loginPage ログインページ
	 */
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	/**
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return 報酬明細ページ
	 */
	public String getEarningsPage() {
		return earningsPage;
	}

	/**
	 * @param earningsPage 報酬明細ページ
	 */
	public void setEarningsPage(String earningsPage) {
		this.earningsPage = earningsPage;
	}

	/**
	 * @return 報酬パス
	 */
	public String getEarningsPath() {
		return earningsPath;
	}

	/**
	 * @param earningsPath 報酬パス
	 */
	public void setEarningsPath(String earningsPath) {
		this.earningsPath = earningsPath;
	}

	/**
	 * @return 報酬正規表現
	 */
	public String getEarningsRegexp() {
		return earningsRegexp;
	}

	/**
	 * @param earningsRegexp 報酬正規表現
	 */
	public void setEarningsRegexp(String earningsRegexp) {
		this.earningsRegexp = earningsRegexp;
	}

	/**
	 * @return クリックポイントページ
	 */
	public String getClickPointPage() {
		return clickPointPage;
	}

	/**
	 * @param clickPointPage クリックポイントページ
	 */
	public void setClickPointPage(String clickPointPage) {
		this.clickPointPage = clickPointPage;
	}

	/**
	 * @return クリックポイントリンク
	 */
	public String getClickPointLink() {
		return clickPointLink;
	}

	/**
	 * @param clickPointLink クリックポイントリンク
	 */
	public void setClickPointLink(String clickPointLink) {
		this.clickPointLink = clickPointLink;
	}

	/**
	 * @return 数字認証画像
	 */
	public String getNumberAuthImage() {
		return numberAuthImage;
	}

	/**
	 * @param numberAuthImage 数字認証画像
	 */
	public void setNumberAuthImage(String numberAuthImage) {
		this.numberAuthImage = numberAuthImage;
	}

	/**
	 * @return 数字認証入力項目
	 */
	public String getNumberAuthInput() {
		return numberAuthInput;
	}

	/**
	 * @param numberAuthInput 数字認証入力項目
	 */
	public void setNumberAuthInput(String numberAuthInput) {
		this.numberAuthInput = numberAuthInput;
	}

	/**
	 * @return ポイント取得フレーム
	 */
	public String getPointGetFrame() {
		return pointGetFrame;
	}

	/**
	 * @param pointGetFrame ポイント取得フレーム
	 */
	public void setPointGetFrame(String pointGetFrame) {
		this.pointGetFrame = pointGetFrame;
	}

	/**
	 * @return ポイント取得メッセージ
	 */
	public String getPointGetMessage() {
		return pointGetMessage;
	}

	/**
	 * @param pointGetMessage ポイント取得メッセージ
	 */
	public void setPointGetMessage(String pointGetMessage) {
		this.pointGetMessage = pointGetMessage;
	}

	/**
	 * @return ポイント取得メッセージパス
	 */
	public String getPointGetMessagePath() {
		return pointGetMessagePath;
	}

	/**
	 * @param pointGetMessagePath ポイント取得メッセージパス
	 */
	public void setPointGetMessagePath(String pointGetMessagePath) {
		this.pointGetMessagePath = pointGetMessagePath;
	}

	/**
	 * @return ログアウトページ
	 */
	public String getLogoutPage() {
		return logoutPage;
	}

	/**
	 * @param logoutPage ログアウトページ
	 */
	public void setLogoutPage(String logoutPage) {
		this.logoutPage = logoutPage;
	}
}
