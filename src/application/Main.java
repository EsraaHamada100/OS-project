package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	// grid
	static GridPane grid = new GridPane();
	@Override
	public void start(Stage primaryStage) {
		try {
			
			grid.getStyleClass().add("pane");
			grid.setPadding(new Insets(10, 10, 10, 10));
			// set vertical gap
			grid.setVgap(8);
			// set horizontal gap
			grid.setHgap(10);
			
			
			// scene
			Scene scene = new Scene(grid,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// stage
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// threads
	        Reader read = new Reader();
	        Writer write = new Writer();
	        for(int i = 0 ; i < 50 ; i++) {
	        	
	            Thread t1 = new Thread(read);
	            t1.setName("thread1");

	            Thread t2 = new Thread(read);
	            t2.setName("thread2");
	            Thread t3 = new Thread(write);
	            t3.setName("thread3");
	            Thread t4 = new Thread(read);
	            t4.setName("thread4");
	            Thread t5 = new Thread(write);
	            t5.setName("thread5");
	            t1.start();
	            Thread.sleep(1);
	            t3.start();
	            Thread.sleep(1);
	            t2.start();
	            Thread.sleep(1);
	            t4.start();
	            Thread.sleep(1);
	            t5.start();
	            Thread.sleep(1);
	        }
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
