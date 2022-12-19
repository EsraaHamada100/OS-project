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
            File myFile = new File("D:\\study\\real_world_þþsolution_to_reader_writer_problem\\src\\solution_to_reader_writer_problem\\my_file2.txt");
            Scanner reader = new Scanner(myFile);
            String file_data = "";
            while(reader.hasNextLine()) {
            	String data = reader.nextLine();
            	file_data += data;
            	System.out.println(data);
            }
            reader.close();
            ReaderWriter.file_data = Integer.parseInt(file_data);
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