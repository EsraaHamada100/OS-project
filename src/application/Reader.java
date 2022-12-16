package application;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

class Reader implements Runnable {
//	static int column = 0;
	
	@Override
	public void run() {
		try {
			while (!ReaderWriter.continueReading.get())
				;
			// Acquire Section
			ReaderWriter.readLock.acquire();
			ReaderWriter.readCount++;
			if (ReaderWriter.readCount == 1) {
            	System.out.println("Reader process ask to acquire");
				ReaderWriter.writeLock.acquire();
			}
			ReaderWriter.readLock.release();

			// Reading section
			System.out.println("Thread " + Thread.currentThread().getName() + " is READING");
			String threadNumber =  Thread.currentThread().getName().replaceAll("[^0-9]", "");
			
			Label readLabel = new Label("R"+ threadNumber);
			
			// showing it in GUI
			ApplicationUI.runAndWait(new Runnable() {
				
				@Override
				public void run() {
					try {
						ApplicationUI.updateLock.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ApplicationUI.update(ApplicationUI.columnNumber,ApplicationUI.rowNumber);
//					System.out.println(ApplicationUI.columnNumber+ApplicationUI.rowNumber);
					GridPane.setConstraints(readLabel, ApplicationUI.columnNumber, ApplicationUI.rowNumber);
					ApplicationUI.updateLock.release();
					Main.grid.getChildren().add(readLabel);
					

				}

			});
			
			Thread.sleep(5000);
			
			// make it disappear from GUI
			ApplicationUI.runAndWait(new Runnable() {

				@Override
				public void run() {
					Main.grid.getChildren().removeIf(
							node -> GridPane.getColumnIndex(node) == GridPane.getColumnIndex(readLabel) && GridPane.getRowIndex(node) == GridPane.getRowIndex(readLabel));

				}
			});
			System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING ");

			// Releasing section
			ReaderWriter.readLock.acquire();
			ReaderWriter.readCount--;
			if (ReaderWriter.readCount == 0) {
				ReaderWriter.writeLock.release();
			}
			ReaderWriter.readLock.release();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}


}