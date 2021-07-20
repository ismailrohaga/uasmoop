package id.binus.finmoop;

/**
 * Created by IsmailR on 19/07/21.
 */
public class Army {
    private int category;
    private double size;

    public Army(int category, int size) {
        this.category = category;
        this.size = size;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getDamage(int attacker) {
        double damage = 0;
        switch (category) {
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

    String getName() {
        String name = "Army";
        switch (category) {
            case Constants.CAVALRY:
                name = "Cavalry";
                break;
            case Constants.INFANTRY:
                name = "Infantry";
                break;
            case Constants.ARCHER:
                name = "Archer";
                break;
        }

        return name;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return getName() + "[" + (int) size + "]";
    }
}
