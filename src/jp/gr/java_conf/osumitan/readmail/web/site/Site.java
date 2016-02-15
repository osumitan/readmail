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
	/** ログアウトページ */
	private String logoutPage;

	/** デフォルト：ページプレフィックス */
	private static final String DEFAULT_PAGE_PREFIX = "pages/";
	/** デフォルト：ログインページ */
	private static final String DEFAULT_LOGIN_PAGE = "enter.php";
	/** デフォルト：報酬明細ページ */
	private static final String DEFAULT_EARNINGS_PAGE = "earnings.php";
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
		// ログアウトページ
		this.logoutPage = DEFAULT_LOGOUT_PAGE;
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
