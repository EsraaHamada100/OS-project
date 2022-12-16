package application;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReaderWriter {
    static Semaphore readLock = new Semaphore(1, true);
    static Semaphore writeLock = new Semaphore(1, true);
    // we make it atomic to prevent java from reordering and refining
    static AtomicBoolean continueReading = new AtomicBoolean(true);
    static int readCount = 0;
}
