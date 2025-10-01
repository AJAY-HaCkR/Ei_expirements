
import java.util.*;




public class App {
    public static void main(String[] args) {
        SimpleLogger.log("Starting Satellite Command System...");
        Satellite satellite = new Satellite();
        try (Scanner scanner = new Scanner(System.in)) {
            InputHandler inputHandler = new InputHandler();
            
            System.out.println("\nCommands: rotate <North|South|East|West> | activatePanels | deactivatePanels | collectData | status | exit");
            
            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                if (line.isEmpty()) continue;
                
                try {
                    Command cmd = inputHandler.parse(line);
                    if (cmd != null) {
    cmd.execute(satellite);
} else if (line.trim().equalsIgnoreCase("status")) {
    satellite.status();
}
                } catch (InvalidCommandException e) {
                    System.out.println("error " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            
            System.out.println("\n--- Final Satellite State ---");
            System.out.println("Orientation: " + satellite.getOrientation());
            System.out.println("Solar Panels: " + satellite.getSolarPanels());
            System.out.println("Data Collected: " + satellite.getDataCollected());
            System.out.println("Battery: " + satellite.getBatteryLevel() + "%");
        }
        SimpleLogger.log("Shutting down.");
    }
}
