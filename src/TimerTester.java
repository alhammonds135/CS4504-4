import java.time.Duration;
import java.time.LocalTime;

import static java.time.LocalTime.now;

public class TimerTester {
    private MyRunnable client;
    public  TimerTester(MyRunnable runnable) {
        this.client = runnable;
    }
    public Duration[] testRun() {
        Duration[] durations = new Duration[4];
        for (int i = 0; i < 4; i++) {
            LocalTime start = now();
            for (int j = 0; j < Math.pow(10,i); j++) {
                client.run(j);
            }
            LocalTime stop = now();
            durations[i] = Duration.between(start,stop);
        }
        return durations;
    }
}
