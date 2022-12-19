package solution_to_reader_writer_problem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReaderWriter {
    static Semaphore readLock = new Semaphore(1, true);
    static Semaphore readWriteLock = new Semaphore(1, true);
    static AtomicBoolean continueReading = new AtomicBoolean(true);
    static int readCount = 0;
}
