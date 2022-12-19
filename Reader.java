package solution_to_reader_writer_problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Reader implements Runnable {
    @Override
    public void run() {
        try {
        	while(!ReaderWriter.continueReading.get());
            //Acquire Section
        	ReaderWriter.readLock.acquire();
        	ReaderWriter.readCount++;
            if (ReaderWriter.readCount == 1) {
//            	System.out.println("Reader process ask to acquire");
            	ReaderWriter.writeLock.acquire();
            }
            ReaderWriter.readLock.release();

            //Reading section
            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            File myFile = new File("D:\\study\\real_world_þþsolution_to_reader_writer_problem\\src\\solution_to_reader_writer_problem\\my_file.txt");
            Scanner reader = new Scanner(myFile);
            while(reader.hasNextLine()) {
            	String data = reader.nextLine();
            	System.out.println(data);
            }
            reader.close();
            System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

            //Releasing section
            ReaderWriter.readLock.acquire();
            ReaderWriter.readCount--;
            if(ReaderWriter.readCount == 0) {
            	ReaderWriter.writeLock.release();
            }
            ReaderWriter.readLock.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
}