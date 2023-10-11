public class Processor extends Thread {

    private int count = 0;
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello " + count++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }

}
