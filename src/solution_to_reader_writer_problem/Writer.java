package solution_to_reader_writer_problem;

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
            System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
            Thread.sleep(500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
            ReaderWriter.writeLock.release();
            
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}