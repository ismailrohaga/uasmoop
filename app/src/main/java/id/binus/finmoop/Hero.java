package id.binus.finmoop;

/**
 * Created by IsmailR on 19/07/21.
 */
public class Hero {
    private int category;

    public Hero(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    String getName() {
        String name = "Hero";
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
        return getName();
    }
}
