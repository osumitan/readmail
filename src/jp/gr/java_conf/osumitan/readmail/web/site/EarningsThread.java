package jp.gr.java_conf.osumitan.readmail.web.site;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.gr.java_conf.osumitan.readmail.web.main.MainThread;

/**
 * サイトスレッド：報酬取得スレッドの基底クラス
 */
public abstract class EarningsThread extends BaseSiteThread {

	/** 報酬setter */
	private BiConsumer<Site, Double> eaningsSetter;

	/**
	 * コンストラクタ
	 * @param mainThread メインスレッド
	 * @param eaningsSetter 報酬setter
	 */
	public EarningsThread(MainThread mainThread, BiConsumer<Site, Double> eaningsSetter) {
		super(mainThread);
		// 報酬setter
		this.eaningsSetter = eaningsSetter;
	}

	/**
	 * 報酬を読み込む
	 */
	protected void readEarnings() {
		// 報酬を読み込む
		WebElement element = findElement(By.xpath(String.format(site.getEarningsPath(), site.getEarningsText())));
		Pattern p = Pattern.compile(site.getEarningsRegexp());
		Matcher m = p.matcher(element.getText());
		this.eaningsSetter.accept(site, m.find() ? Double.valueOf(m.group(1)) : null);
		mainThread.getFrame().getSiteTable().repaint();
	}
}
