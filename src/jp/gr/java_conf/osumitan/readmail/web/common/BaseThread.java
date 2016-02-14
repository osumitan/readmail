package jp.gr.java_conf.osumitan.readmail.web.common;

/**
 * スレッドの基底クラス
 */
public abstract class BaseThread extends Thread {

	/** 実行状態 */
	protected RunningStatus runningStatus;

	/**
	 * コンストラクタ
	 */
	public BaseThread() {
		// 実行状態
		this.runningStatus = RunningStatus.PREPARING;
	}

	/**
	 * 実行
	 */
	@Override
	public void run() {
		try {
			// 準備
			prepare();
			// 実行開始
			this.runningStatus = RunningStatus.RUNNING;
			// 実行継続
			while(RunningStatus.RUNNING.equals(this.runningStatus)) {
				// メイン処理
				main();
				// 100msウェイト
				Thread.sleep(100L);
			}
			// 終了処理
			if(RunningStatus.ABORTED.equals(this.runningStatus)) {
				onAborted();
			} else if(RunningStatus.FINISHED.equals(this.runningStatus)) {
				onFinished();
			} else {
				onAbended(new RuntimeException(String.valueOf(this.runningStatus)));
			}
		} catch(Throwable ex) {
			// 異常終了処理
			onAbended(ex);
		}
	}

	/**
	 * 中断
	 */
	public void abort() {
		this.runningStatus = RunningStatus.ABORTED;
	}

	/**
	 * 準備
	 */
	protected abstract void prepare();

	/**
	 * メイン
	 */
	protected abstract void main();

	/**
	 * 中断時処理
	 */
	protected abstract void onAborted();

	/**
	 * 正常終了時処理
	 */
	protected abstract void onFinished();

	/**
	 * 異常終了時処理
	 * @param ex 例外
	 */
	protected abstract void onAbended(Throwable ex);

	/**
	 * 実行状態
	 */
	protected enum RunningStatus {
		/** 準備中 */
		PREPARING,
		/** 実行中 */
		RUNNING,
		/** 中断 */
		ABORTED,
		/** 正常終了 */
		FINISHED,
		/** 異常終了 */
		ABENDED;
	}
}
