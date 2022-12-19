package solution_to_reader_writer_problem;

import java.io.FileWriter;
import java.io.IOException;

class Writer implements Runnable {
    @Override
    public void run() {
        try {
        	if(ReaderWriter.readCount > 0) {
        		
        		ReaderWriter.continueReading.set(false);
//        		System.out.println("Stop the reading process now "+ReaderWriter.readerTurn);
        	}
//        	System.out.println("writer process ask to acquire");
            ReaderWriter.writeLock.acquire();
            ReaderWriter.continueReading.set(true);
            // write in to file section
            System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");

            if(ReaderWriter.file_data == 0) {
            	System.out.println("Sorry, no tickets are available.");
            	ReaderWriter.writeLock.release();
            	return ;
            }
            FileWriter writer = new FileWriter("D:\\study\\real_world_þþsolution_to_reader_writer_problem\\src\\solution_to_reader_writer_problem\\my_file2.txt");
            --ReaderWriter.file_data;
            writer.write(String.valueOf(ReaderWriter.file_data));
            writer.close();
            // The end of  writing and the release section
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
            ReaderWriter.writeLock.release();
            
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
        	System.out.println("An error accured while trying to write to the file");
        	System.out.println(e.getMessage());
		}
    }
}