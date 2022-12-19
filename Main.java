package solution_to_reader_writer_problem;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
    	// creating the file that we will read and write in
    	try {
    		File myFile = new File("D:\\study\\real_world_þþsolution_to_reader_writer_problem\\src\\solution_to_reader_writer_problem\\my_file.txt");
    		if(myFile.createNewFile()) {
    			System.out.println("file created "+myFile.getName());
    		}else {
    			System.out.println("The file already exists");
    		}
		} catch (IOException e) {
			System.out.println("An error accured while tring to create the file");
			e.printStackTrace();
		}
    	
    	
        Reader read = new Reader();
        Writer write = new Writer();
        for(int i = 0 ; i < 10 ; i++) {

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

    }
}
