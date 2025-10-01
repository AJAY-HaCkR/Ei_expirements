

import java.util.Random;

public class DataCollector {
    private final String[] sources = {"India", "USA", "Moon", "Mars", "Europe", "Japan", "Australia"};
    private final Random random;

    public DataCollector() {
        this.random = new Random();
    }

    public String nextSource() {
        int idx = random.nextInt(sources.length);
        return sources[idx];
    }
}
