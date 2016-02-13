package jp.gr.java_conf.osumitan.readmail.web;

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
	/** ログインページ */
	private String loginPage;
	/** ログインID */
	private String loginId;
	/** ログインパスワード */
	private String loginPassword;

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
	public Site() {}

	/**
	 * @return サイト名
	 */
	public String getName() {
		return name;
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
	 * @return ログインID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return ログインパスワード
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * @param loginPassword ログインパスワード
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
