
public class Satellite {
    private String orientation;
    private String solarPanels;
    private int dataCollected;
    private final Battery battery;
    private final SolarPanel solarPanel;
    private final DataCollector dataCollector;
    private int commandCount;

    public Satellite() {
        this.orientation = "North";
        this.solarPanels = "Inactive";
        this.dataCollected = 0;
        this.battery = new Battery();
        this.solarPanel = new SolarPanel(battery, this);
        this.dataCollector = new DataCollector();
        this.commandCount = 0;
        System.out.println("Satellite initialized: Orientation=" + orientation + ", Panels=" + solarPanels + ", Data=" + dataCollected + ", Battery=" + battery.getLevel() + "%");
    }

    public synchronized void rotate(String direction) {
        if (!isValidDirection(direction)) {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        this.orientation = direction;
        incrementCommand();
        System.out.println("Satellite rotated to " + orientation);
    }

    public synchronized void activatePanels() {
        if (this.solarPanels.equals("Active")) {
            System.out.println("Solar panels already active.");
            return;
        }
        this.solarPanels = "Active";
        System.out.println("Solar panels activated.");
        solarPanel.activate();
    }

    public synchronized void deactivatePanels() {
        if (this.solarPanels.equals("Inactive")) {
            System.out.println("Solar panels already inactive.");
            return;
        }
        this.solarPanels = "Inactive";
        solarPanel.deactivate();
        System.out.println("Solar panels deactivated to prevent overcharging");
    }
    public synchronized void collectData() {
    if (battery.getLevel() > 10) {
        String source = dataCollector.nextSource();
        this.dataCollected += 10;
        battery.consume(10);
        System.out.println("Data collected from " + source + ". Total Data = " + dataCollected);
        incrementCommand();
        checkBatteryPrompt();
    } else {
        System.out.println(" Battery too low. Cannot collect data.");
    }
}


    public synchronized String getOrientation() {
        return orientation;
    }

    public synchronized String getSolarPanels() {
        return solarPanels;
    }

    public synchronized int getDataCollected() {
        return dataCollected;
    }

    public synchronized int getBatteryLevel() {
        return battery.getLevel();
    }

    void incrementCommand() {
        commandCount++;
        battery.dischargeOnCommand();
        checkBatteryPrompt();
    }

    private void checkBatteryPrompt() {
        
        if (commandCount % 2 == 0 && battery.isLow()) {
            System.out.println("⚡ Battery Low! Please activate solar panels.");
        }
    }

    private boolean isValidDirection(String d) {
        return d.equals("North") || d.equals("South") || d.equals("East") || d.equals("West");
    }

    
    public synchronized void onChargingComplete() {
        this.solarPanels = "Active"; 
    }

    
    public synchronized void autoDeactivatePanelsDueToOvercharge() {
        if (this.solarPanels.equals("Active")) {
            this.solarPanels = "Inactive";
            solarPanel.deactivate();
            System.out.println("⚠ Deactivating panels to prevent overcharging");
        }
    }

    public synchronized void status() {
    System.out.println("========== SATELLITE STATUS ==========");
    System.out.println("Orientation : " + this.orientation);
    System.out.println("Panels      : " + this.solarPanels);
    System.out.println("Battery     : " + battery.getLevel() + "%");
    System.out.println("Data Stored : " + this.dataCollected + " units");
    System.out.println("======================================");
}

}
