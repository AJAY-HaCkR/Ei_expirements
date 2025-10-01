



public class ActivatePanelsCommand implements Command {
    @Override
    public void execute(Satellite satellite) {
        satellite.activatePanels();
    }
}
