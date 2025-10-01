interface Module {
    String getDescription();
    double getCost();
}

class BasicModule implements Module {
    @Override
    public String getDescription() { return "Basic Spacecraft Module"; }
    @Override
    public double getCost() { return 100.0; }
}

abstract class ModuleDecorator implements Module {
    protected Module decoratedModule;
    public ModuleDecorator(Module module) { this.decoratedModule = module; }
    @Override
    public String getDescription() { return decoratedModule.getDescription(); }
    @Override
    public double getCost() { return decoratedModule.getCost(); }
}

class CameraDecorator extends ModuleDecorator {
    public CameraDecorator(Module module) { super(module); }
    @Override
    public String getDescription() { return decoratedModule.getDescription() + ", Camera Module"; }
    @Override
    public double getCost() { return decoratedModule.getCost() + 50; }
}

class SolarPanelDecorator extends ModuleDecorator {
    public SolarPanelDecorator(Module module) { super(module); }
    @Override
    public String getDescription() { return decoratedModule.getDescription() + ", Solar Panels"; }
    @Override
    public double getCost() { return decoratedModule.getCost() + 30; }
}

public class decorator {
    public static void main(String[] args) {
        Module spacecraft = new BasicModule();
        System.out.println(spacecraft.getDescription() + " = $" + spacecraft.getCost());

        spacecraft = new CameraDecorator(spacecraft);
        spacecraft = new SolarPanelDecorator(spacecraft);
        System.out.println(spacecraft.getDescription() + " = $" + spacecraft.getCost());
    }
}
