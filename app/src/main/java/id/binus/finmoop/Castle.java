package id.binus.finmoop;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IsmailR on 19/07/21.
 */
public class Castle {
    private int skin;
    private List<Army> armies;
    private List<Hero> heroes;

    public Castle(int skin, List<Army> armies, List<Hero> heroes) {
        this.skin = skin;
        this.armies = armies;
        this.heroes = heroes;
    }

    public int getSkin() {
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public List<Army> getArmies() {
        return armies;
    }

    public void setArmies(List<Army> armies) {
        this.armies = armies;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void buildArmy() {
        List<Army> armyList = new ArrayList<>();
        for (Army army: armies) {
            int heroPerks = 0;
            for (Hero hero: heroes) {
                if (army.getCategory() == hero.getCategory()) {
                    heroPerks+=40;
                }
            }

            army.setSize(army.getSize() + (army.getSize() * (heroPerks/100.0)));

            if (army.getCategory() == skin) {
                army.setSize(army.getSize() + (army.getSize() * (20/100.0)));
            }

            armyList.add(army);
        }
        armies = armyList;
    }
}
