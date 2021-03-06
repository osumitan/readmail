package jp.gr.java_conf.osumitan.readmail.web.site;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jp.gr.java_conf.osumitan.readmail.web.site.InboxThread.InboxMailLinkSelectorType;
import jp.gr.java_conf.osumitan.readmail.web.site.PointThread.NumberAuthType;
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
	/** 報酬請求可否 */
	private boolean payable;

	/** ドメイン */
	private String domain;
	/** ページプレフィックス */
	private String pagePrefix;
	/** ログインページ */
	private String loginPage;
	/** ログインボタン */
	private String loginButton;
	/** ユーザ名 */
	private String userName;
	/** ユーザ名テキストボックス */
	private String userNameTextBox;
	/** パスワード */
	private String password;
	/** パスワードテキストボックス */
	private String passwordTextBox;
	/** 報酬明細ページ */
	private String earningsPage;
	/** 報酬明細テキスト */
	private String earningsText;
	/** 報酬パス */
	private String earningsPath;
	/** 報酬正規表現 */
	private String earningsRegexp;
	/** クリックポイントページ */
	private String clickPointPage;
	/** クリックポイントリンク */
	private String clickPointLink;
	/** 受信箱ページ */
	private String inboxPage;
	/** 受信箱メールリンク */
	private String inboxMailLink;
	/** 受信箱メールリンクセレクタ */
	private String inboxMailLinkSelector;
	/** 受信箱メールリンクセレクタタイプ */
	private InboxMailLinkSelectorType inboxMailLinkSelectorType;
	/** 受信箱ポイントリンク */
	private String inboxPointLink;
	/** 受信箱メール削除要否 */
	private boolean requiredInboxMailDelete;
	/** 受信箱メール削除チェック */
	private String inboxMailDeleteCheck;
	/** 受信箱メール削除ボタン */
	private String inboxMailDeleteButton;
	/** 数字認証画像 */
	private String numberAuthImage;
	/** 数字認証タイプ */
	private NumberAuthType numberAuthType;
	/** 数字認証項目名 */
	private String numberAuthName;
	/** ポイントリンクパラメータ */
	private String pointLinkParameter;
	/** ポイント取得フレーム */
	private String pointGetFrame;
	/** ポイント取得メッセージ */
	private String pointGetMessage;
	/** ポイント取得メッセージパス */
	private String pointGetMessagePath;
	/** 広告ウィンドウ */
	private String adWindow;
	/** 支払請求ページ */
	private String payRequestPage;
	/** 支払請求ボタンセレクタ */
	private String payRequestButtonSelector;
	/** ログアウトページ */
	private String logoutPage;

	/** デフォルト：ページプレフィックス */
	private static final String DEFAULT_PAGE_PREFIX = "pages/";
	/** デフォルト：ログインページ */
	private static final String DEFAULT_LOGIN_PAGE = "enter.php";
	/** デフォルト：ログインボタン */
	private static final String DEFAULT_LOGIN_BUTTON = "submit";
	/** デフォルト：ユーザ名テキストボックス */
	private static final String DEFAULT_USER_NAME_TEXT_BOX = "username";
	/** デフォルト：パスワードテキストボックス */
	private static final String DEFAULT_PASSWORD_TEXT_BOX = "password";
	/** デフォルト：報酬明細ページ */
	private static final String DEFAULT_EARNINGS_PAGE = "earnings.php";
	/** デフォルト：報酬明細テキスト */
	private static final String DEFAULT_EARNINGS_TEXT = "合計ポイント";
	/** デフォルト：報酬パス */
	private static final String DEFAULT_EARNINGS_PATH = "//*[contains(text(),'%s')]/ancestor::tr[1]/td[2]//text()/..";
	/** デフォルト：報酬正規表現 */
	private static final String DEFAULT_EARNINGS_REGEXP = "(\\d+\\.\\d+)";
	/** デフォルト：クリックポイントページ */
	private static final String DEFAULT_CLICK_POINT_PAGE = "ptc.php";
	/** デフォルト：クリックポイントリンク */
	private static final String DEFAULT_CLICK_POINT_LINK = "scripts/runner.php?PA=";
	/** デフォルト：受信箱ページ */
	private static final String DEFAULT_INBOX_PAGE = "inbox.php";
	/** デフォルト：受信箱メールリンク */
	private static final String DEFAULT_INBOX_MAIL_LINK = "scripts/runner.php?IM=";
	/** デフォルト：受信箱メールリンクセレクタ */
	private static final String DEFAULT_INBOX_MAIL_LINK_SELECTOR = "a[href*='%s']";
	/** デフォルト：受信箱メールリンクセレクタタイプ */
	private static final InboxMailLinkSelectorType DEFAULT_INBOX_MAIL_LINK_SELECTOR_TYPE = InboxMailLinkSelectorType.CSS_SELECTOR;
	/** デフォルト：受信箱ポイントリンク */
	private static final String DEFAULT_INBOX_POINT_LINK = "scripts/runner.php?EA=";
	/** デフォルト：受信箱メール削除要否 */
	private static final boolean DEFAULT_REQUIRED_INBOX_MAIL_DELETE = true;
	/** デフォルト：受信箱メール削除チェック */
	private static final String DEFAULT_INBOX_DELETE_CHECK = "//*[contains(@href,'%s')]/ancestor::tr[1]//input[contains(@type,'checkbox')]";
	/** デフォルト：受信箱メール削除ボタン */
	private static final String DEFAULT_INBOX_DELETE_BUTTON = "削除";
	/** デフォルト：数字認証画像 */
	private static final String DEFAULT_NUMBER_AUTH_IMAGE = "scripts/runner.php?TN=";
	/** デフォルト：数字認証タイプ */
	private static final NumberAuthType DEFAULT_NUMBER_AUTH_TYPE = NumberAuthType.LINK;
	/** デフォルト：数字認証項目名 */
	private static final String DEFAULT_NUMBER_AUTH_NAME = "PI";
	/** デフォルト：ポイントリンクパラメータ */
	private static final String DEFAULT_POINT_LINK_PARAMETER = "";
	/** デフォルト：ポイント取得フレーム */
	private static final String DEFAULT_POINT_GET_FRAME = "timerfrm";
	/** デフォルト：ポイント取得メッセージ */
	private static final String DEFAULT_POINT_GET_MESSAGE = "報酬が加算されました";
	/** デフォルト：ポイント取得メッセージパス */
	private static final String DEFAULT_POINT_GET_MESSAGE_PATH = "//*[contains(text(),'%s')]/..";
	/** デフォルト：支払請求ページ */
	private static final String DEFAULT_PAY_REQUEST_PAGE = "redeem.php";
	/** デフォルト：支払請求ボタンセレクタ */
	private static final String DEFAULT_PAY_REQUEST_BUTTON_SELECTOR = "#withdbtn1";
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
		// ログインボタン
		this.loginButton = DEFAULT_LOGIN_BUTTON;
		// ユーザ名テキストボックス
		this.userNameTextBox = DEFAULT_USER_NAME_TEXT_BOX;
		// パスワードテキストボックス
		this.passwordTextBox = DEFAULT_PASSWORD_TEXT_BOX;
		// 報酬明細ページ
		this.earningsPage = DEFAULT_EARNINGS_PAGE;
		// 報酬明細テキスト
		this.earningsText = DEFAULT_EARNINGS_TEXT;
		// 報酬パス
		this.earningsPath = DEFAULT_EARNINGS_PATH;
		// 報酬正規表現
		this.earningsRegexp = DEFAULT_EARNINGS_REGEXP;
		// クリックポイントページ
		this.clickPointPage = DEFAULT_CLICK_POINT_PAGE;
		// クリックポイントリンク
		this.clickPointLink = DEFAULT_CLICK_POINT_LINK;
		// 受信箱ページ
		this.inboxPage = DEFAULT_INBOX_PAGE;
		// 受信箱メールリンク
		this.inboxMailLink = DEFAULT_INBOX_MAIL_LINK;
		// 受信箱メールリンクセレクタ
		this.inboxMailLinkSelector = DEFAULT_INBOX_MAIL_LINK_SELECTOR;
		// 受信箱メールリンクセレクタタイプ
		this.inboxMailLinkSelectorType = DEFAULT_INBOX_MAIL_LINK_SELECTOR_TYPE;
		// 受信箱ポイントリンク
		this.inboxPointLink = DEFAULT_INBOX_POINT_LINK;
		// 受信箱メール削除要否
		this.requiredInboxMailDelete = DEFAULT_REQUIRED_INBOX_MAIL_DELETE;
		// 受信箱メール削除チェック
		this.inboxMailDeleteCheck = DEFAULT_INBOX_DELETE_CHECK;
		// 受信箱メール削除ボタン
		this.inboxMailDeleteButton = DEFAULT_INBOX_DELETE_BUTTON;
		// 数字認証画像
		this.numberAuthImage = DEFAULT_NUMBER_AUTH_IMAGE;
		// 数字認証タイプ
		this.numberAuthType = DEFAULT_NUMBER_AUTH_TYPE;
		// 数字認証項目名
		this.numberAuthName = DEFAULT_NUMBER_AUTH_NAME;
		// ポイントリンクパラメータ
		this.pointLinkParameter = DEFAULT_POINT_LINK_PARAMETER;
		// ポイント取得フレーム
		this.pointGetFrame = DEFAULT_POINT_GET_FRAME;
		// ポイント取得メッセージ
		this.pointGetMessage = DEFAULT_POINT_GET_MESSAGE;
		// ポイント取得メッセージパス
		this.pointGetMessagePath = DEFAULT_POINT_GET_MESSAGE_PATH;
		// 支払請求ページ
		this.payRequestPage = DEFAULT_PAY_REQUEST_PAGE;
		// 支払請求ボタンセレクタ
		this.payRequestButtonSelector = DEFAULT_PAY_REQUEST_BUTTON_SELECTOR;
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
	 * @return 報酬請求可否
	 */
	public boolean isPayable() {
		return payable;
	}

	/**
	 * @return 報酬請求可否
	 */
	public String getPayableString() {
		return isPayable() ? "★" : "";
	}

	/**
	 * @param payable 報酬請求可否
	 */
	public void setPayable(boolean payable) {
		this.payable = payable;
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
	 * @return ログインボタン
	 */
	public String getLoginButton() {
		return loginButton;
	}

	/**
	 * @param loginButton ログインボタン
	 */
	public void setLoginButton(String loginButton) {
		this.loginButton = loginButton;
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
	 * @return ユーザ名テキストボックス
	 */
	public String getUserNameTextBox() {
		return userNameTextBox;
	}

	/**
	 * @param userNameTextBox ユーザ名テキストボックス
	 */
	public void setUserNameTextBox(String userNameTextBox) {
		this.userNameTextBox = userNameTextBox;
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
	 * @return パスワードテキストボックス
	 */
	public String getPasswordTextBox() {
		return passwordTextBox;
	}

	/**
	 * @param passwordTextBox パスワードテキストボックス
	 */
	public void setPasswordTextBox(String passwordTextBox) {
		this.passwordTextBox = passwordTextBox;
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
	 * @return 報酬明細テキスト
	 */
	public String getEarningsText() {
		return earningsText;
	}

	/**
	 * @param earningsText 報酬明細テキスト
	 */
	public void setEarningsText(String earningsText) {
		this.earningsText = earningsText;
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
	 * @return i受信箱ページ
	 */
	public String getInboxPage() {
		return inboxPage;
	}

	/**
	 * @param inboxPage 受信箱ページ
	 */
	public void setInboxPage(String inboxPage) {
		this.inboxPage = inboxPage;
	}

	/**
	 * @return 受信箱メールリンク
	 */
	public String getInboxMailLink() {
		return inboxMailLink;
	}

	/**
	 * @param inboxMailLink 受信箱メールリンク
	 */
	public void setInboxMailLink(String inboxMailLink) {
		this.inboxMailLink = inboxMailLink;
	}

	/**
	 * @return 受信箱メールリンクセレクタ
	 */
	public String getInboxMailLinkSelector() {
		return inboxMailLinkSelector;
	}

	/**
	 * @param inboxMailLinkSelector 受信箱メールリンクセレクタ
	 */
	public void setInboxMailLinkSelector(String inboxMailLinkSelector) {
		this.inboxMailLinkSelector = inboxMailLinkSelector;
	}

	/**
	 * @return 受信箱メールリンクセレクタタイプ
	 */
	public InboxMailLinkSelectorType getInboxMailLinkSelectorType() {
		return inboxMailLinkSelectorType;
	}

	/**
	 * @param inboxMailLinkSelectorType 受信箱メールリンクセレクタタイプ
	 */
	public void setInboxMailLinkSelectorType(InboxMailLinkSelectorType inboxMailLinkSelectorType) {
		this.inboxMailLinkSelectorType = inboxMailLinkSelectorType;
	}

	/**
	 * @param inboxMailLinkSelectorType 受信箱メールリンクセレクタタイプ
	 */
	public void setInboxMailLinkSelectorType(String inboxMailLinkSelectorType) {
		setInboxMailLinkSelectorType(InboxMailLinkSelectorType.valueOf(inboxMailLinkSelectorType));
	}

	/**
	 * @return 受信箱ポイントリンク
	 */
	public String getInboxPointLink() {
		return inboxPointLink;
	}

	/**
	 * @param inboxPointLink 受信箱ポイントリンク
	 */
	public void setInboxPointLink(String inboxPointLink) {
		this.inboxPointLink = inboxPointLink;
	}

	/**
	 * @return 受信箱メール削除要否
	 */
	public boolean isRequiredInboxMailDelete() {
		return requiredInboxMailDelete;
	}

	/**
	 * @param requiredInboxMailDelete 受信箱メール削除要否
	 */
	public void setRequiredInboxMailDelete(boolean requiredInboxMailDelete) {
		this.requiredInboxMailDelete = requiredInboxMailDelete;
	}

	/**
	 * @param requiredInboxMailDelete 受信箱メール削除要否
	 */
	public void setRequiredInboxMailDelete(String requiredInboxMailDelete) {
		setRequiredInboxMailDelete(Boolean.valueOf(requiredInboxMailDelete));
	}

	/**
	 * @return 受信箱メール削除チェック
	 */
	public String getInboxMailDeleteCheck() {
		return inboxMailDeleteCheck;
	}

	/**
	 * @param inboxMailDeleteCheck 受信箱メール削除チェック
	 */
	public void setInboxMailDeleteCheck(String inboxMailDeleteCheck) {
		this.inboxMailDeleteCheck = inboxMailDeleteCheck;
	}

	/**
	 * @return 受信箱メール削除ボタン
	 */
	public String getInboxMailDeleteButton() {
		return inboxMailDeleteButton;
	}

	/**
	 * @param inboxMailDeleteButton 受信箱メール削除ボタン
	 */
	public void setInboxMailDeleteButton(String inboxMailDeleteButton) {
		this.inboxMailDeleteButton = inboxMailDeleteButton;
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
	 * @return 数字認証タイプ
	 */
	public NumberAuthType getNumberAuthType() {
		return numberAuthType;
	}

	/**
	 * @param numberAuthType 数字認証タイプ
	 */
	public void setNumberAuthType(NumberAuthType numberAuthType) {
		this.numberAuthType = numberAuthType;
	}

	/**
	 * @param numberAuthType 数字認証タイプ
	 */
	public void setNumberAuthType(String numberAuthType) {
		setNumberAuthType(NumberAuthType.valueOf(numberAuthType));
	}

	/**
	 * @return 数字認証項目名
	 */
	public String getNumberAuthName() {
		return numberAuthName;
	}

	/**
	 * @param numberAuthInput 数字認証項目名
	 */
	public void setNumberAuthName(String numberAuthInput) {
		this.numberAuthName = numberAuthInput;
	}

	/**
	 * @return ポイントリンクパラメータ
	 */
	public String getPointLinkParameter() {
		return pointLinkParameter;
	}

	/**
	 * @param pointLinkParameter ポイントリンクパラメータ
	 */
	public void setPointLinkParameter(String pointLinkParameter) {
		this.pointLinkParameter = pointLinkParameter;
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
	 * @return 広告ウィンドウ
	 */
	public String getAdWindow() {
		return adWindow;
	}

	/**
	 * 広告ウィンドウがあるか
	 * @return 広告ウィンドウがあるか
	 */
	public boolean hasAdWindow() {
		return this.adWindow != null;
	}

	/**
	 * @param adWindow 広告ウィンドウ
	 */
	public void setAdWindow(String adWindow) {
		this.adWindow = adWindow;
	}

	/**
	 * @return 支払請求ページ
	 */
	public String getPayRequestPage() {
		return payRequestPage;
	}

	/**
	 * @param payRequestPage 支払請求ページ
	 */
	public void setPayRequestPage(String payRequestPage) {
		this.payRequestPage = payRequestPage;
	}

	/**
	 * @return 支払請求ボタンセレクタ
	 */
	public String getPayRequestButtonSelector() {
		return payRequestButtonSelector;
	}

	/**
	 * @param payRequestButtonSelector 支払請求ボタンセレクタ
	 */
	public void setPayRequestButtonSelector(String payRequestButtonSelector) {
		this.payRequestButtonSelector = payRequestButtonSelector;
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
