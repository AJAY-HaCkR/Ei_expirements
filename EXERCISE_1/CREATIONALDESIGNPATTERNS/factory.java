interface Spacecraft {
    void launch();
}

class Shuttle implements Spacecraft {
    @Override
    public void launch() { System.out.println("Shuttle launching into low earth orbit "); }
}

class Probe implements Spacecraft {
    @Override
    public void launch() { System.out.println("Probe launched toward Mars "); }
}

class SpacecraftFactory {
    public static Spacecraft getSpacecraft(String type) {
        if (type.equalsIgnoreCase("shuttle")) return new Shuttle();
        if (type.equalsIgnoreCase("probe")) return new Probe();
        return null;
    }
}

public class factory {
    public static void main(String[] args) {
        Spacecraft s1 = SpacecraftFactory.getSpacecraft("shuttle");
        Spacecraft s2 = SpacecraftFactory.getSpacecraft("probe");

        s1.launch();
        s2.launch();
    }
}
