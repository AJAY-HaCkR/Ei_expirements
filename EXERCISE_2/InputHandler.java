

public class InputHandler {
    public Command parse(String input) throws InvalidCommandException {
        String trimmed = input.trim();
        if (trimmed.equalsIgnoreCase("activatePanels")) {
            return (Command) new ActivatePanelsCommand();
        }
        if (trimmed.equalsIgnoreCase("deactivatePanels")) {
            return new DeactivatePanelsCommand();
        }
        if (trimmed.equalsIgnoreCase("collectData")) {
            return new CollectDataCommand();
        }
        if (trimmed.equalsIgnoreCase("status")) {
            // status is handled in App, return null so App prints directly
            return null;
        }
        if (trimmed.toLowerCase().startsWith("rotate")) {
            String[] parts = trimmed.split("\\s+");
            if (parts.length != 2) {
                throw new InvalidCommandException("Usage: rotate <North|South|East|West>");
            }
            String dir = capitalize(parts[1]);
            if (!isValidDirection(dir)) {
                throw new InvalidCommandException("Invalid direction: " + parts[1]);
            }
            return new RotateCommand(dir);
        }
        throw new InvalidCommandException("Unknown command: " + input);
    }

    private boolean isValidDirection(String d) {
        return d.equals("North") || d.equals("South") || d.equals("East") || d.equals("West");
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        String low = s.toLowerCase();
        return Character.toUpperCase(low.charAt(0)) + low.substring(1);
    }
}
