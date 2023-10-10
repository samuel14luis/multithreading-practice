import utils.ConsoleColors;

public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            long startTime = System.currentTimeMillis();
            int threadNumber = 10;

            for (int i = 0; i < threadNumber; i++) {
                Thread thread = createThread(String.valueOf(i));
                thread.start();
            }

            long endTime = System.currentTimeMillis();

            // Calcula la diferencia de tiempo en milisegundos
            long totalTime = endTime - startTime;

            // Convierte los milisegundos a minutos y segundos
            long segundos = (totalTime / 1000) % 60;
            long minutos = (totalTime / (1000 * 60)) % 60;

            System.out.println("Tiempo transcurrido: " + minutos + "m " + segundos + "s.");

        });

        t1.start();
    }

    private static Thread createThread(String number) {
        int randomThread = (int) (Math.random() * 3);
        switch (randomThread) {
            case 1: return new Thread(new App1("Thread " + number,
                    ConsoleColors.BLUE_FONT,
                    ConsoleColors.BLACK_BACKGROUND, 10));
            case 2: return new Thread(new App1("Thread " + number,
                        ConsoleColors.BLACK_FONT,
                        ConsoleColors.RED_BACKGROUND, 15));
            case 3: return new Thread(new App1("Thread " + number,
                        ConsoleColors.BLACK_GREEN_FONT,
                        ConsoleColors.YELLOW_BACKGROUND, 5));
            default: return new Thread(new App1("Thread " + number,
                        ConsoleColors.BLUE_FONT,
                        ConsoleColors.BLACK_BACKGROUND, 3));
        }
    }
}