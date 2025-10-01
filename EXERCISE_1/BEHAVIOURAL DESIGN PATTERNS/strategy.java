interface FuelStrategy {
    void launch();
}

class LiquidFuel implements FuelStrategy {
    @Override
    public void launch() {
        System.out.println("Launching rocket with Liquid Fuel 🚀");
    }
}

class SolidFuel implements FuelStrategy {
    @Override
    public void launch() {
        System.out.println("Launching rocket with Solid Fuel 🚀");
    }
}

class Rocket {
    private FuelStrategy fuelStrategy;

    public void setFuelStrategy(FuelStrategy fuelStrategy) {
        this.fuelStrategy = fuelStrategy;
    }

    public void executeLaunch() {
        fuelStrategy.launch();
    }
}

public class strategy {
    public static void main(String[] args) {
        Rocket rocket = new Rocket();

        rocket.setFuelStrategy(new LiquidFuel());
        rocket.executeLaunch();

        rocket.setFuelStrategy(new SolidFuel());
        rocket.executeLaunch();
    }
}
