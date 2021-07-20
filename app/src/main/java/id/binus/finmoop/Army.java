package id.binus.finmoop;

/**
 * Created by IsmailR on 19/07/21.
 */
public class Army extends Hero{
    private double size;

    public Army(int category, int size) {
        super(category);
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getDamage(int attacker) {
        double damage = 0;
        switch (getCategory()) {
            case Constants.CAVALRY:
                switch (attacker) {
                    case Constants.INFANTRY:
                        damage = 0.1;
                        break;
                    case Constants.ARCHER:
                        damage = 0.4;
                        break;
                }
                break;
            case Constants.INFANTRY:
                switch (attacker) {
                    case Constants.ARCHER:
                        damage = 0.1;
                        break;
                    case Constants.CAVALRY:
                        damage = 0.3;
                        break;
                }
                break;
            case Constants.ARCHER:
                switch (attacker) {
                    case Constants.CAVALRY:
                        damage = 0.1;
                        break;
                    case Constants.INFANTRY:
                        damage = 0.4;
                        break;
                }
                break;
        }
        return damage;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return getName() + "[" + (int) size + "]";
    }
}
