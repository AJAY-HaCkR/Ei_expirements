



public class CollectDataCommand implements Command {
    @Override
    public void execute(Satellite satellite) {
        satellite.collectData();
    }
}
