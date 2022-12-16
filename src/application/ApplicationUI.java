package application;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;

public class ApplicationUI {
	static int columnNumber = 0;
	static int rowNumber = 0;
	static Semaphore updateLock = new Semaphore(1, true);

	public static void update(int col, int row) {

		if (col == 10) {

			++ApplicationUI.rowNumber;
			ApplicationUI.columnNumber = 0;

		}else {
			++ApplicationUI.columnNumber;
		}

	}
	
	public static void runAndWait(Runnable run) throws InterruptedException {
		FutureTask<String> task = new FutureTask<>(run, "updated");
		Platform.runLater(task);
		try {
			task.get();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
