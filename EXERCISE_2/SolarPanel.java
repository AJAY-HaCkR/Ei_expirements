
public class SolarPanel {
    private final Battery battery;
    private final Satellite satellite;
    private volatile boolean active;
    private Thread chargingThread;

    public SolarPanel(Battery battery, Satellite satellite) {
        this.battery = battery;
        this.satellite = satellite;
        this.active = false;
    }

    public synchronized void activate() {
        if (active) return;
        active = true;
        startCharging();
    }

    public synchronized void deactivate() {
        active = false;
        if (chargingThread != null && chargingThread.isAlive()) {
            chargingThread.interrupt();
        }
    }

    private void startCharging() {
        chargingThread = new Thread(() -> {
            try {
                System.out.print("Charging");
                while (active && battery.getLevel() < 100) {
                    try {
                        Thread.sleep(700); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    System.out.print(".");
                    battery.chargeStep(25); 
                }
                System.out.println("\n Battery Full!");
                battery.setFull();
                satellite.onChargingComplete();

                
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (active) {
                    satellite.autoDeactivatePanelsDueToOvercharge();
                }
            } catch (Exception e) {
                System.out.println("\nCharging interrupted.");
            }
        });
        chargingThread.start();
    }
}
