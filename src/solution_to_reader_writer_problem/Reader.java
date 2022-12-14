package solution_to_reader_writer_problem;

class Reader implements Runnable {
    @Override
    public void run() {
        try {
        	// false => !false => true
        	while(!ReaderWriter.continueReading.get());
            //Acquire Section
        	ReaderWriter.readLock.acquire();
        	ReaderWriter.readCount++;
            if (ReaderWriter.readCount == 1) {
            	System.out.println(Thread.currentThread().getName());
            	System.out.println("Reader process ask to acquire");
            	ReaderWriter.readWriteLock.acquire();
            }
            ReaderWriter.readLock.release();

            //Reading section
            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            Thread.sleep(500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

            //Releasing section
            ReaderWriter.readLock.acquire();
            ReaderWriter.readCount--;
            if(ReaderWriter.readCount == 0) {
            	ReaderWriter.readWriteLock.release();
            }
            ReaderWriter.readLock.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}