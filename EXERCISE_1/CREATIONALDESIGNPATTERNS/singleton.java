class MissionLogger {
    private static MissionLogger instance;

    private MissionLogger() {}

    public static MissionLogger getInstance() {
        if (instance == null) {
            instance = new MissionLogger();
        }
        return instance;
    }

    public void log(String event) {
        System.out.println("[MISSION LOG] " + event);
    }
}

public class singleton {
    public static void main(String[] args) {
        MissionLogger log1 = MissionLogger.getInstance();
        MissionLogger log2 = MissionLogger.getInstance();

        log1.log("Satellite deployed into orbit ");
        log2.log("Solar panels activated ");

        System.out.println("Same logger instance? " + (log1 == log2));
    }
}
