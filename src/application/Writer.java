package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

class Writer implements Runnable {
	@Override
	public void run() {
		try {
			if (ReaderWriter.readCount > 2) {
				ReaderWriter.continueReading.set(false);
//        		System.out.println("Stop the reading process now "+ReaderWriter.readerTurn);
			}
//        	System.out.println("writer process ask to acquire");
			ReaderWriter.writeLock.acquire();
			ReaderWriter.continueReading.set(true);
			System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING");
			String threadNumber = Thread.currentThread().getName().replaceAll("[^0-9]", "");
			Label writeLabel = new Label("W" + threadNumber);
			ApplicationUI.runAndWait(new Runnable() {

				@Override
				public void run() {
					ApplicationUI.columnNumber = 0;
					ApplicationUI.rowNumber = 0;
					GridPane.setConstraints(writeLabel, ApplicationUI.columnNumber, ApplicationUI.rowNumber);
					Main.grid.getChildren().add(writeLabel);

				}

			});
			Thread.sleep(500);
			ApplicationUI.runAndWait(new Runnable() {

				@Override
				public void run() {
					Main.grid.getChildren().removeIf(
							node -> GridPane.getColumnIndex(node) == GridPane.getColumnIndex(writeLabel) && GridPane.getRowIndex(node) == GridPane.getRowIndex(writeLabel));

				}
			});
			System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING");
			ReaderWriter.writeLock.release();

		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}