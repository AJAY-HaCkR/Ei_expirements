interface GroundReceiver {
    void receive(String data);
}

class NewTelemetrySystem {
    public void sendNewData(String packet) {
        System.out.println("Sending telemetry packet: " + packet);
    }
}

class TelemetryAdapter implements GroundReceiver {
    NewTelemetrySystem newSystem = new NewTelemetrySystem();

    @Override
    public void receive(String data) {
        newSystem.sendNewData(data);
    }
}

public class adapter {
    public static void main(String[] args) {
        GroundReceiver receiver = new TelemetryAdapter();
        receiver.receive("Altitude: 400km, Velocity: 7.8km/s");
    }
}
