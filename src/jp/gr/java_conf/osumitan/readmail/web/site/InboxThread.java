package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：受信箱
 */
public class InboxThread extends PointThread {

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 */
	public InboxThread(MainThread mainThread) {
		super(mainThread);
	}

	/**
	 * 処理
	 */
	public void run() {
		// ログ
		log("受信箱ページを開いています…");
		// 受信箱を開く
		navigate(site.getInboxPage());
		// ログ
		log("受信箱ページを開きました。");
		// 受信箱メールリンクがあるか
		By byInboxMailLink = site.getInboxMailLinkSelectorType().getInboxMailLinkSelector().apply(this);
		if(existsElement(byInboxMailLink)) {
			// ログ
			log("受信箱メールを開いています…");
			// 受信箱メールを開く
			WebElement mail = findElement(byInboxMailLink);
			get(mail.getAttribute("href"));
			// ログ
			log("受信箱メールを開きました。");
			// 受信箱ポイントリンクがあるか
			if(existsElement(String.format("a[href*='%s']", site.getInboxPointLink()))) {
				// ログ
				log("受信箱ポイントを開いています…");
				// 受信箱ポイントを開く
				navigatePointLink(site.getInboxPointLink());
				// ログ
				log("受信箱ポイントを開きました。");
				// ポイント獲得まで待つ
				waitUntilPointGet();
			}
			// 受信箱メール削除が必要な場合
			if(site.isRequiredInboxMailDelete()) {
				// ログ
				log("受信箱ページを開いています…");
				// 受信箱を開く
				navigate(site.getInboxPage());
				// ログ
				log("受信箱メールを削除しています…");
				// 受信箱メールを削除する
				setChecked(By.xpath(String.format(site.getInboxMailDeleteCheck(), site.getInboxMailLink())), true);
				click(String.format("input[type='submit'][value='%s']", site.getInboxMailDeleteButton()));
				// ログ
				log("受信箱メールを削除しました。");
			}
		} else {
			// ログ
			log("受信箱が終了しました。");
			// ステータス：処理後報酬を取得する
			setSiteStatus(SiteStatus.EARNINGS_AFTER);
		}
	}

	/**
	 * 受信箱メールリンクセレクタ：CSSセレクタ
	 * @return 受信箱メールリンクセレクタ
	 */
	private By getInboxMailLinkSelectorCssSelector() {
		return By.cssSelector(String.format(site.getInboxMailLinkSelector(), site.getInboxMailLink()));
	}

	/**
	 * 受信箱メールリンクセレクタ：XPATH
	 * @return 受信箱メールリンクセレクタ
	 */
	private By getInboxMailLinkSelectorXPath() {
		return By.xpath(String.format(site.getInboxMailLinkSelector(), site.getInboxMailLink()));
	}

	/**
	 * 受信箱メールリンクセレクタタイプ
	 */
	public enum InboxMailLinkSelectorType {
		/** CSSセレクタ */
		CSS_SELECTOR(InboxThread::getInboxMailLinkSelectorCssSelector),
		/** XPATH */
		XPATH(InboxThread::getInboxMailLinkSelectorXPath);

		/** 受信箱メールリンクセレクタ */
		private Function<InboxThread, By> inboxMailLinkSelector;

		/**
		 * コンストラクタ
		 * @param inboxMailLinkSelector 受信箱メールリンクセレクタ
		 */
		private InboxMailLinkSelectorType(Function<InboxThread, By> inboxMailLinkSelector) {
			// 受信箱メールリンクセレクタ
			this.inboxMailLinkSelector = inboxMailLinkSelector;
		}

		/**
		 * @return 受信箱メールリンクセレクタ
		 */
		public Function<InboxThread, By> getInboxMailLinkSelector() {
			return inboxMailLinkSelector;
		}
	}
}
