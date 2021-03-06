import java.time.Duration;
import java.time.LocalTime;

import static java.time.LocalTime.now;

//Author: Eshin Griffith

public class TimerTester {
    private final int MINPOWER = 2;
    private final int MAXPOWER = 8;
    private MyRunnable client;
    public  TimerTester(MyRunnable runnable) {
        this.client = runnable;
    }
    public Duration[] testRun() {
        Duration[] durations = new Duration[MAXPOWER - MINPOWER];
        for (int i = MINPOWER; i < MAXPOWER; i++) {
            System.out.println("With "+Math.pow(10,i)+" iterations:");
            LocalTime start = now();
            for (int j = 0; j < Math.pow(10,i); j++) {
                client.run(j);
            }
            LocalTime stop = now();
            durations[i - MINPOWER] = Duration.between(start,stop);
        }
        return durations;
    }

    public static void main(String[] args) {
        MyRunnable client = new HelloClient(); // Uncomment which one you want to test
        // MyRunnable client = new UDPClient();
        // MyRunnable client = new TCPClient();
        TimerTester timerTester = new TimerTester(client);
        Duration[] durations = timerTester.testRun();
        for (Duration duration: durations) {
            System.out.format("%d.%09d%n",duration.getSeconds(),duration.getNano());
        }
    }
}
