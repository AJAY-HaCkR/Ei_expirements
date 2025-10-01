

public class Battery {
    private int level; // 0-100

    public Battery() {
        this.level = 100;
    }

    public synchronized int getLevel() {
        return level;
    }

    public synchronized boolean isLow() {
        return level <= 20;
    }

    public synchronized void dischargeOnCommand() {
        
        level -= 10;
        if (level < 0) level = 0;
    }

    public synchronized void setFull() {
        level = 100;
    }

    public synchronized void chargeStep(int amount) {
        level += amount;
        if (level > 100) level = 100;
    }
    public synchronized void consume(int amount) {
        level -= amount;
        if (level < 0) level = 0;
    }
}
