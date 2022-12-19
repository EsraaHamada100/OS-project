package solution_to_reader_writer_problem;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader read = new Reader();
        Writer write = new Writer();
        for(int i = 0 ; i < 100 ; i++) {

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
            Thread.sleep(10);
            t3.start();
            Thread.sleep(10);
            t2.start();
            Thread.sleep(10);
            t4.start();
            Thread.sleep(10);
            t5.start();
            Thread.sleep(1);
        }

    }
}
