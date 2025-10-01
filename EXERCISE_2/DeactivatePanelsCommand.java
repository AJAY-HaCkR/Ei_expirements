



public class DeactivatePanelsCommand implements Command {
    @Override
    public void execute(Satellite satellite) {
        satellite.deactivatePanels();
    }
}
