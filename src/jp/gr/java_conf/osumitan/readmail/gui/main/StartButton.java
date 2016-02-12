package jp.gr.java_conf.osumitan.readmail.gui.main;

import java.awt.event.ActionEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jp.gr.java_conf.osumitan.readmail.gui.common.BaseActionListener;
import jp.gr.java_conf.osumitan.readmail.gui.common.BaseButton;

/**
 * スタートボタン
 */
public class StartButton extends BaseButton<MainFrame> {

	/**serialVersionUID */
	private static final long serialVersionUID = 2390862907751037255L;

	/**
	 * コンストラクタ
	 * @param frame フレーム
	 */
	public StartButton(MainFrame frame) {
		super(frame, "スタート", new OnClick(frame));
	}
	
	/**
	 * ボタン押下時
	 */
	private static class OnClick extends BaseActionListener<MainFrame> {

		/** serialVersionUID */
		private static final long serialVersionUID = 4621489862790816764L;

		/**
		 * コンストラクタ
		 * @param frame フレーム
		 */
		OnClick(MainFrame frame) {
			super(frame);
		}
		
		/**
		 * ボタン押下時処理
		 * @param e イベント
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
			WebDriver drv = new ChromeDriver();
			drv.get("http://www.htmlhifive.com/conts/web/view/Main/WebHome");
			WebElement elm1 = drv.findElement(By.id("tutorialLinkOnMainVisual"));
			elm1.click();
			WebDriverWait wait = new WebDriverWait(drv, 10);
			WebElement elm2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("document-title")));
			super.frame.showMessage("title", elm2.getText());
			drv.quit();
		}
	}
}