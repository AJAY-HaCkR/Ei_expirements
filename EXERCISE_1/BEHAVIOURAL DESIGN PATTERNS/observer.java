import java.util.*;

interface Observer {
    void update(String telemetry);
}

class GroundStation implements Observer {
    private final String name;

    public GroundStation(String name) {
        this.name = name;
    }

    @Override
    public void update(String telemetry) {
        System.out.println(name + " received telemetry: " + telemetry);
    }
}

class Satellite {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void setTelemetry(String telemetry) {
        for (Observer o : observers) {
            o.update(telemetry);
        }
    }
}

public class observer {
    public static void main(String[] args) {
        Satellite sat = new Satellite();
        GroundStation gs1 = new GroundStation("Houston");
        GroundStation gs2 = new GroundStation("Bangalore");

        sat.addObserver(gs1);
        sat.addObserver(gs2);

        sat.setTelemetry("Altitude: 400km");
        sat.setTelemetry("Velocity: 7.8 km/s");
    }
}
