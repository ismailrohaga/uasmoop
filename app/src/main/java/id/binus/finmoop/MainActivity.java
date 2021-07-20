package id.binus.finmoop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean isGameOneLoaded = false;

    //player
    List<Castle> castles = new ArrayList<>();
    List<Army> pOneArmy = new ArrayList<>();
    List<Hero> pOneHero = new ArrayList<>();
    List<Army> pTwoArmy = new ArrayList<>();
    List<Hero> pTwoHero = new ArrayList<>();

    //available hero
    Hero cavalryHero = new Hero(Constants.CAVALRY);
    Hero infantryHero = new Hero(Constants.INFANTRY);
    Hero archerHero = new Hero(Constants.ARCHER);

    //ui
    Button button;
    RelativeLayout layoutPOne, layoutPTwo;
    ImageView ivCastlePOne, ivCastlePTwo;
    TextView castlePOne, castleDetailPOne, castlePTwo,
            castleDetailPTwo, battleLog, title, pOneWin, pTwoWin;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        loadGame();

        button.setOnClickListener(v -> {
            if (!battleLog.getText().equals("battle_log")){
                Toast.makeText(this, "next match", Toast.LENGTH_SHORT).show();
                loadGame();
                battleLog.setText("battle_log");
                pOneWin.setText("");
                pTwoWin.setText("");
            } else {
                battle();
            }
        });
    }

    void bindView() {
        button = findViewById(R.id.btn);
        layoutPOne = findViewById(R.id.layoutPOne);
        layoutPTwo = findViewById(R.id.layoutPTwo);
        ivCastlePOne = findViewById(R.id.ivCastlePOne);
        ivCastlePTwo = findViewById(R.id.ivCastlePTwo);
        castlePOne = findViewById(R.id.tvCastlePOne);
        castleDetailPOne = findViewById(R.id.tvCastleDetailPOne);
        castlePTwo = findViewById(R.id.tvCastlePTwo);
        castleDetailPTwo = findViewById(R.id.tvCastleDetailPTwo);
        battleLog = findViewById(R.id.tvLog);
        title = findViewById(R.id.tvTitle);
        pOneWin = findViewById(R.id.pOneWin);
        pTwoWin = findViewById(R.id.pTwoWin);
    }

    @SuppressLint("SetTextI18n")
    void battle() {
        StringBuilder result = new StringBuilder();

        for (Army p1Army: castles.get(0).getArmies()) {
            for (Army p2Army: castles.get(1).getArmies()) {
                double p1damage = p1Army.getDamage(p2Army.getCategory()) * p1Army.getSize();
                double p2damage = p2Army.getDamage(p1Army.getCategory()) * p2Army.getSize();

                result.append("\np1's ")
                        .append(p1Army.getName())
                        .append(" get damage from ")
                        .append("p2's ")
                        .append(p2Army.getName())
                        .append(" [").append(p1damage).append("]");

                result.append("\np2's ")
                        .append(p2Army.getName())
                        .append(" get damage from ")
                        .append("p1's ")
                        .append(p1Army.getName())
                        .append(" [").append(p2damage).append("]");

                p1Army.setSize(p1Army.getSize() - p1damage);
                p2Army.setSize(p2Army.getSize() - p2damage);
            }
        }

        double p1Total = 0, p2Total = 0;

        for (Army p1Army: castles.get(0).getArmies()) {
            p1Total += p1Army.getSize();
        }

        for (Army p2Army: castles.get(1).getArmies()) {
            p2Total += p2Army.getSize();
        }

        if (p1Total > p2Total) {
            pOneWin.setText("WINNER\n" + (int)p1Total);
            pTwoWin.setText("LOOSER\n" + (int)p2Total);
        } else {
            pOneWin.setText("LOOSER\n" + (int)p1Total);
            pTwoWin.setText("WINNER\n" + (int)p2Total);
        }

        battleLog.setText(result);
    }

    void loadGame() {
        if (isGameOneLoaded) {
            initGameTwo();
            isGameOneLoaded = false;
        } else {
            initGameOne();
            isGameOneLoaded = true;
        }
    }

    void initGameOne() {
        castles.clear();
        pOneArmy.clear();
        pOneHero.clear();
        pTwoArmy.clear();
        pTwoHero.clear();
        //init player one
        pOneArmy.add(new Army(Constants.CAVALRY, 100000));
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);

        //init player two
        pTwoArmy.add(new Army(Constants.ARCHER, 100000));
        pTwoHero.add(archerHero);
        pTwoHero.add(archerHero);
        pTwoHero.add(archerHero);
        pTwoHero.add(archerHero);
        pTwoHero.add(archerHero);

        //assign army
        assignArmy();
    }

    void initGameTwo() {
        castles.clear();
        pOneArmy.clear();
        pOneHero.clear();
        pTwoArmy.clear();
        pTwoHero.clear();
        //init player one
        pOneArmy.add(new Army(Constants.CAVALRY, 75000));
        pOneArmy.add(new Army(Constants.ARCHER, 25000));
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);
        pOneHero.add(cavalryHero);
        pOneHero.add(archerHero);
        pOneHero.add(archerHero);

        //init player two
        pTwoArmy.add(new Army(Constants.INFANTRY, 100000));
        pTwoHero.add(infantryHero);
        pTwoHero.add(infantryHero);

        //assign army
        assignArmy();
    }

    void assignArmy() {
        castles.add(new Castle(Constants.STONE, pOneArmy, pOneHero));
        castles.add(new Castle(Constants.STONE, pTwoArmy, pTwoHero));

        setCastleUi(castles.get(0), ivCastlePOne, castlePOne, castleDetailPOne);
        setCastleUi(castles.get(1), ivCastlePTwo, castlePTwo, castleDetailPTwo);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    void setCastleUi(Castle castle, ImageView imageView, TextView title, TextView detail) {
        switch (castle.getSkin()) {
            case Constants.HORSE:
                imageView.setBackground(getDrawable(R.drawable.horse));
                title.setText("Horse Castle");
                break;
            case Constants.WOOD:
                imageView.setBackground(getDrawable(R.drawable.wood));
                title.setText("Wood Castle");
                break;
            case Constants.STEEL:
                imageView.setBackground(getDrawable(R.drawable.steel));
                title.setText("Steel Castle");
                break;
            case Constants.STONE:
                imageView.setBackground(getDrawable(R.drawable.stone));
                title.setText("Stone Castle");
                break;
        }

        castle.buildArmy();

        detail.setText("Armies:" + fetchArmy(castle.getArmies()) +
                "\nHeroes:" + fetchHero(castle.getHeroes()));
    }

    String fetchArmy(List<Army> armies) {
        StringBuilder result = new StringBuilder();
        for (Army army: armies) {
            result.append("\n").append(army.toString()).append(".");
        }
        return result.toString();
    }

    String fetchHero(List<Hero> heroes) {
        StringBuilder result = new StringBuilder();
        for (Hero hero: heroes) {
            result.append("\n").append(hero.toString()).append(".");
        }
        return result.toString();
    }
}