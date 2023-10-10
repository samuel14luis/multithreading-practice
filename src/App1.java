import utils.ConsoleColors;

public class App1 implements Runnable {

    private String threadName;
    private String color;
    private String background;
    private int stopIn;

    public App1(String threadName, String color, String background, int stopIn) {
        Thread.currentThread().setName(threadName);
        this.threadName = threadName;
        this.color = color;
        this.background = background;
        this.stopIn = stopIn;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.printf(ConsoleColors.colorize(
                        "[" + threadName + "] " + i,
                        this.color, this.background));
                if (i == stopIn) {
                    Thread.sleep(350);
                }
            }
        } catch (Exception e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }

    }
}
