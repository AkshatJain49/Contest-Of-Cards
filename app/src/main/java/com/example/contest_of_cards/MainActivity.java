package com.example.contest_of_cards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // ADAPTERS
    ImageView cImage, uImage;
    TextView cName, uName;
    Button cIntellect, cStrength, cSpeed, cDurability, cPower, cCombat;
    Button uIntellect, uStrength, uSpeed, uDurability, uPower, uCombat;
    UserCharacterAdapter userCharacterAdapter;
    CpuCharacterAdapter cpuCharacterAdapter;

    // VIEWS
    ConstraintLayout constraintLayout;
    TextView cpuScores, userScores, textCardLeft, textSelect;
    ListView userListView, cpuListView;
    Button btnControl, btnDisable;
    ImageView cpuArrow, userArrow;
    CardView cpuCardView, userCardView;

    // DATA TYPES
    ArrayList<Character> arrayList, userArrayList, cpuArrayList, userTempList, cpuTempList;
    boolean cpuTurn = false;
    static int j = 0;
    int perPlayerCards, cardsLeft , userCard = 0, cpuCard = 0;
    int userScore = 0, cpuScore = 0;

    //PLAYER AND ANIMATION
    MediaPlayer player;
    Animation animFadeIn, animFadeIn2, animFadeIn3;





    public class CpuCharacterAdapter extends ArrayAdapter<Character> {
        public CpuCharacterAdapter(Context context, ArrayList<Character> arrayList) {
            super(context, 0, arrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, parent, false);

            Character character = getItem(position);

            cImage = view.findViewById(R.id.characterImage);
            cName = view.findViewById(R.id.characterName);
            cStrength = view.findViewById(R.id.btnStrength);
            cIntellect = view.findViewById(R.id.btnIntellect);
            cSpeed = view.findViewById(R.id.btnSpeed);
            cDurability = view.findViewById(R.id.btnDurability);
            cPower = view.findViewById(R.id.btnPower);
            cCombat = view.findViewById(R.id.btnCombat);

            cImage.setImageResource(character.getImage());
            cName.setText(character.getName());
            cIntellect.setText(String.valueOf(character.getIntelligence()));
            cStrength.setText(String.valueOf(character.getStrength()));
            cSpeed.setText(String.valueOf(character.getSpeed()));
            cDurability.setText(String.valueOf(character.getDurability()));
            cPower.setText(String.valueOf(character.getPower()));
            cCombat.setText(String.valueOf(character.getCombat()));

            return view;
        }
    }





    public class UserCharacterAdapter extends ArrayAdapter<Character> {
        public UserCharacterAdapter(Context context, ArrayList<Character> arrayList) {
            super(context, 0, arrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, parent, false);

            Character character = getItem(position);

            uImage = view.findViewById(R.id.characterImage);
            uName = view.findViewById(R.id.characterName);
            uStrength = view.findViewById(R.id.btnStrength);
            uIntellect = view.findViewById(R.id.btnIntellect);
            uSpeed = view.findViewById(R.id.btnSpeed);
            uDurability = view.findViewById(R.id.btnDurability);
            uPower = view.findViewById(R.id.btnPower);
            uCombat = view.findViewById(R.id.btnCombat);

            uImage.setImageResource(character.getImage());
            uName.setText(character.getName());
            uIntellect.setText(String.valueOf(character.getIntelligence()));
            uStrength.setText(String.valueOf(character.getStrength()));
            uSpeed.setText(String.valueOf(character.getSpeed()));
            uDurability.setText(String.valueOf(character.getDurability()));
            uPower.setText(String.valueOf(character.getPower()));
            uCombat.setText(String.valueOf(character.getCombat()));

            uIntellect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonDisable();
                    uIntellect.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getIntelligence();
                    int uValue = userTempList.get(0).getIntelligence();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);
                }
            });

            uStrength.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonDisable();
                    uStrength.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getStrength();
                    int uValue = userTempList.get(0).getStrength();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);
                }
            });

            uSpeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonDisable();
                    uSpeed.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getSpeed();
                    int uValue = userTempList.get(0).getSpeed();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);
                }
            });

            uDurability.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    buttonDisable();
                    uDurability.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getDurability();
                    int uValue = userTempList.get(0).getDurability();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);

                }
            });

            uPower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonDisable();
                    uPower.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getPower();
                    int uValue = userTempList.get(0).getPower();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);

                }
            });

            uCombat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonDisable();
                    uCombat.setBackgroundColor(R.drawable.gradient_name);

                    int cValue = cpuTempList.get(0).getCombat();
                    int uValue = userTempList.get(0).getCombat();
                    userArrow.setVisibility(View.GONE);
                    checkStatus(cValue, uValue);
                }
            });

            return view;
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.button);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeIn3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        constraintLayout = findViewById(R.id.constraintLayout);
        cpuCardView = findViewById(R.id.cpuCard);
        userCardView = findViewById(R.id.userCard);
        cpuScores = findViewById(R.id.cpuScores);
        userScores = findViewById(R.id.userScores);
        textSelect = findViewById(R.id.textSelect);
        textCardLeft = findViewById(R.id.textCardsLeft);
        btnControl = findViewById(R.id.btnControl);
        btnDisable = findViewById(R.id.btnDisable);
        cpuArrow = findViewById(R.id.cpuArrow);
        userArrow = findViewById(R.id.userArrow);

        userListView = findViewById(R.id.userListView);
        cpuListView = findViewById(R.id.cpuListView);

        arrayList = new ArrayList<>();
        userArrayList = new ArrayList<>();
        cpuArrayList = new ArrayList<>();
        userTempList = new ArrayList<>();
        cpuTempList = new ArrayList<>();


        Intent intent = getIntent();
        perPlayerCards = Integer.parseInt(intent.getStringExtra("TOTAL_CARDS"));
        cardsLeft = perPlayerCards;

        addArrayList();

        // HANDLING CARDS
        for(int i = 0; i < perPlayerCards; i++)
        {
            Random random = new Random();
            int randomNumber = random.nextInt(arrayList.size() - i);

            // GETTING CARD AT RANDOM POSITION
            Character character = arrayList.get(randomNumber);

            // ADDING CARD TO USER ARRAY LIST
            userArrayList.add(character);

            // REMOVING CARD FROM MAIN ARRAY LIST
            arrayList.remove(randomNumber);
        }

        // ADDING REMAINING CARD TO CPU ARRAY LIST
        Collections.shuffle(arrayList);

        for (int i = 0; i < perPlayerCards; i++)
        {
            Character character = arrayList.get(i);
            cpuArrayList.add(character);
        }

        // DISTRIBUTING FIRST CARD
        Character character;
        character = userArrayList.get(j);
        userTempList.add(character);

        character = cpuArrayList.get(j);
        cpuTempList.add(character);

        btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.start();
                textSelect.animate().alpha(0).setDuration(200);

                if(btnControl.getText().toString().equalsIgnoreCase("START"))
                {
                    userCardView.setAnimation(animFadeIn);
                    userCharacterAdapter = new UserCharacterAdapter(MainActivity.this, userTempList);
                    userListView.setAdapter(userCharacterAdapter);
                    cpuCharacterAdapter = new CpuCharacterAdapter(MainActivity.this, cpuTempList);

                    textCardLeft.setText(String.valueOf(cardsLeft));
                    btnControl.setText("NEXT ROUND");
                    userArrow.setVisibility(View.VISIBLE);
                    btnControl.setVisibility(View.INVISIBLE);
                }


                else if (btnControl.getText().toString().equalsIgnoreCase("FINISH"))
                {
                    cpuTurn = false;
                    String finalScore = String.valueOf(userScore);
                    String gameResult;

                    if (userScore > cpuScore)
                        gameResult = "WIN";
                    else
                        gameResult = "LOSS";

                    Intent intent = new Intent(MainActivity.this, FinishActivity.class);
                    intent.putExtra("SCORE", finalScore);
                    intent.putExtra("STATUS", gameResult);
                    intent.putExtra("CARDS", String.valueOf(perPlayerCards));
                    startActivity(intent);
                    finish();
                }


                else
                {
                    userCardView.setAnimation(animFadeIn);

                    // ENABLING THE BUTTONS
                    uIntellect.setEnabled(true);
                    uStrength.setEnabled(true);
                    uSpeed.setEnabled(true);
                    uDurability.setEnabled(true);
                    uPower.setEnabled(true);
                    uCombat.setEnabled(true);

                    // SETTING VISIBILITIES
                    btnDisable.setVisibility(View.GONE);
                    cpuArrow.setVisibility(View.GONE);
                    userArrow.setVisibility(View.VISIBLE);

                    // UPDATING CARDS
                    userCardUpdate();
                    cpuCardUpdate();

                    btnControl.setVisibility(View.INVISIBLE);
                    if (cardsLeft == 1)
                        btnControl.setText("FINISH");
                }


                if (cpuTurn)
                {
                    cpuArrow.setVisibility(View.VISIBLE);
                    userArrow.setVisibility(View.GONE);
                    btnDisable.setVisibility(View.VISIBLE);
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {

                        }
                        public void onFinish() {
                            cpuCardView.startAnimation(animFadeIn2);
                            cpuChoice();
                        }
                    }.start();
                }

            }
        });

        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(constraintLayout, "ITS CPU'S TURN!!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }





    @Override
    public void onBackPressed() {
        showAlert();
    }





    protected void addArrayList()
    {
        arrayList.add(new Character("SPIDER-MAN", 90, 55, 67, 75, 74, 84, R.drawable.spiderman));
        arrayList.add(new Character("THANOS", 95, 100, 33, 100, 100, 80, R.drawable.thanos));
        arrayList.add(new Character("IRON MAN", 100, 85, 58, 85, 100, 64, R.drawable.ironman));
        arrayList.add(new Character("DEADPOOL", 69, 32, 50, 100, 80, 100, R.drawable.deadpool));
        arrayList.add(new Character("CAPTAIN AMERICA", 69, 19, 38, 55, 60, 100, R.drawable.capamerica));
        arrayList.add(new Character("BLACK WIDOW", 75, 13, 33, 30, 36, 100, R.drawable.blackwidow));
        arrayList.add(new Character("HULK", 88, 100, 63, 100, 98, 85, R.drawable.hulk));
        arrayList.add(new Character("SCARLET WITCH", 79, 10, 29, 70, 100, 80, R.drawable.scarletwitch));
        arrayList.add(new Character("THOR", 69, 100, 83, 100, 100, 100, R.drawable.thor));
        arrayList.add(new Character("WOLVERINE", 63, 32, 50, 100, 89, 100, R.drawable.wolverine));
        arrayList.add(new Character("HAWKEYE", 56, 12, 21, 10, 29, 80, R.drawable.hawkeye));
        arrayList.add(new Character("STAR-LORD", 49, 20, 33, 50, 25, 70, R.drawable.starlord));
        arrayList.add(new Character("ANT-MAN", 79, 18, 23, 28, 32, 32, R.drawable.antman));
        arrayList.add(new Character("BLACK PANTHER", 88, 16, 30, 60, 41, 98, R.drawable.blackpanther));
        arrayList.add(new Character("GROOT", 75, 85, 33, 70, 95, 64, R.drawable.groot));
        arrayList.add(new Character("SHE-HULK", 63, 73, 35, 90, 73, 56, R.drawable.shehulk));
        arrayList.add(new Character("THING", 75, 84, 21, 100, 38, 80, R.drawable.thing));
        arrayList.add(new Character("LOKI", 88, 63, 46, 85, 90, 60, R.drawable.loki));
        arrayList.add(new Character("WASP", 63, 17, 58, 52, 29, 42, R.drawable.wasp));
        arrayList.add(new Character("SABRETOOTH", 56, 48, 38, 90, 50, 100, R.drawable.sabretooth));
        arrayList.add(new Character("MAGNETO", 50, 28, 23, 28, 100, 95, R.drawable.magneto));
        arrayList.add(new Character("CABLE", 88, 48, 23, 56, 90, 80, R.drawable.cable));
        arrayList.add(new Character("ROGUE", 75, 10, 12, 28, 80, 80, R.drawable.rogue));
        arrayList.add(new Character("GAMORA", 75, 85, 42, 85, 53, 95, R.drawable.gamora));
        arrayList.add(new Character("NICK FURY", 65, 11, 23, 42, 25, 80, R.drawable.nickfury));
        arrayList.add(new Character("WAR MACHINE", 63, 80, 63, 90, 90, 85, R.drawable.warmachine));
        arrayList.add(new Character("HUMAN TORCH", 63, 11, 63, 70, 87, 42, R.drawable.humantorch));
        arrayList.add(new Character("APOCALYPSE", 100, 100, 33, 100, 100, 60, R.drawable.apocalypse));
        arrayList.add(new Character("DRAX", 54, 80, 25, 85, 46, 65, R.drawable.drax));
        arrayList.add(new Character("CYCLOPS", 75, 10, 23, 42, 76, 80, R.drawable.cyclops));
        arrayList.add(new Character("MYSTIQUE", 65, 12, 23, 64, 64, 74, R.drawable.mystique));
        arrayList.add(new Character("PROFESSOR-X", 92, 8, 12, 15, 98, 32, R.drawable.professor));
        arrayList.add(new Character("RONAN", 56, 52, 21, 40, 66, 80, R.drawable.ronan));
        arrayList.add(new Character("ROCKET RACOON", 50, 5, 23, 28, 28, 64, R.drawable.rocket));
        arrayList.add(new Character("QUICKSILVER", 63, 28, 100, 60, 81, 56, R.drawable.quicksilver));
        arrayList.add(new Character("NEBULA", 63, 52, 13, 50, 40, 60, R.drawable.nebula));
        arrayList.add(new Character("CAPTAIN MARVEL", 88, 99, 88, 95, 99, 75, R.drawable.capmarvel));
        arrayList.add(new Character("ABOMINATION", 43, 80, 53, 90, 62, 95, R.drawable.abomination));
        arrayList.add(new Character("DOCTOR STRANGE", 97, 10, 12, 84, 99, 60, R.drawable.docstrange));
        arrayList.add(new Character("EGO", 88, 80, 83, 99, 71, 28, R.drawable.ego));
        arrayList.add(new Character("FALCON", 38, 13, 50, 28, 22, 64, R.drawable.falcon));
        arrayList.add(new Character("BETA RAY BILL", 63, 80, 35, 95, 92, 87, R.drawable.betaray));
        arrayList.add(new Character("IRON MONGER", 88, 63, 25, 90, 57, 56, R.drawable.ironmonger));
        arrayList.add(new Character("INVISIBLE WOMAN", 88, 10, 27, 85, 93, 56, R.drawable.invisible));
        arrayList.add(new Character("MANTIS", 63, 8, 33, 80, 100, 80, R.drawable.mantis));
        arrayList.add(new Character("MEPHISTO", 88, 85, 35, 95, 98, 42, R.drawable.mephisto));
        arrayList.add(new Character("MODOK", 99, 18, 25, 25, 79, 15, R.drawable.modok));
        arrayList.add(new Character("NAMOR", 69, 95, 58, 70, 73, 85, R.drawable.namor));
        arrayList.add(new Character("ODIN", 63, 60, 90, 98, 100, 35, R.drawable.odin));
        arrayList.add(new Character("RED SKULL", 75, 10, 12, 14, 19, 80, R.drawable.redskull));
        arrayList.add(new Character("STORM", 72, 10, 47, 30, 88, 75, R.drawable.storm));
        arrayList.add(new Character("RED-HULK", 50, 95, 47, 85, 82, 75, R.drawable.redhulk));
        arrayList.add(new Character("ULTRON", 88, 83, 42, 100, 100, 64, R.drawable.ultron));
        arrayList.add(new Character("VALKYRIE", 77, 76, 37, 70, 60, 74, R.drawable.valkryie));
        arrayList.add(new Character("VENOM", 80, 57, 65, 84, 86, 84, R.drawable.venom));
        arrayList.add(new Character("VISON", 99, 72, 54, 95, 98, 70, R.drawable.vision));
        arrayList.add(new Character("VULTURE", 75, 22, 42, 25, 26, 30, R.drawable.vulture));
        arrayList.add(new Character("WONG", 70, 55, 67, 75, 85, 74, R.drawable.wong));
        arrayList.add(new Character("YELLOW JACKET", 88, 15, 12, 28, 12, 14, R.drawable.yellow));
        arrayList.add(new Character("ZOLA", 80, 10, 15, 35, 79, 42, R.drawable.armin));
        arrayList.add(new Character("YONDU", 50, 55, 47, 64, 49, 76, R.drawable.yondu));
        arrayList.add(new Character("SENTRY", 78, 100, 100, 84, 100, 40, R.drawable.sentry));
        arrayList.add(new Character("MYSTERIO", 63, 60, 80, 60, 84, 35, R.drawable.mysterio));
        arrayList.add(new Character("KINGPIN", 75, 18, 25, 40, 13, 70, R.drawable.kingpin));
        arrayList.add(new Character("JUGGERNAUT", 88, 63, 25, 90, 57, 56, R.drawable.juggernaut));
        arrayList.add(new Character("ONE ABOVE ALL", 100, 100, 100, 100, 100, 100, R.drawable.one));
        arrayList.add(new Character("MR. FANTASTIC", 100, 10, 18, 70, 33, 64, R.drawable.fantastic));
        arrayList.add(new Character("HELA", 63, 99, 46, 95, 92, 45, R.drawable.hela));
        arrayList.add(new Character("GREEN GOBLIN", 90, 48, 38, 60, 48, 50, R.drawable.greengob));
        arrayList.add(new Character("DR. OCTOPUS", 94, 48, 33, 40, 53, 64, R.drawable.dococtopus));
        arrayList.add(new Character("COLOSSUS", 63, 83, 33, 96, 45, 80, R.drawable.colossus));
        arrayList.add(new Character("ANCIENT ONE", 80, 84, 52, 85, 94, 88, R.drawable.ancient));
        arrayList.add(new Character("JEAN GRAY", 94, 80, 21, 20, 92, 70, R.drawable.jean));
        arrayList.add(new Character("LIVING TRIBUNAL", 100, 100, 55, 100, 100, 30, R.drawable.living));
        arrayList.add(new Character("NOVA", 90, 85, 75, 94, 98, 80, R.drawable.nova));
        arrayList.add(new Character("TASKMASTER", 75, 12, 50, 20, 63, 99, R.drawable.taskmaster));
        arrayList.add(new Character("DOCTOR DOOM", 94, 32, 20, 96, 90, 84, R.drawable.docdoom));
        arrayList.add(new Character("ETERNITY", 100, 100, 50, 100, 100, 35, R.drawable.eternity));
        arrayList.add(new Character("GALACTUS", 75, 100, 60, 100, 100, 40, R.drawable.galactus));
        arrayList.add(new Character("SILVER SURFER", 63, 90, 65, 89, 87, 85, R.drawable.silver));
    }





    protected void cpuChoice()
    {
        Random random = new Random();
        int cChoice = random.nextInt(6);
        int cValue, uValue;
        cpuArrow.setVisibility(View.GONE);
        textSelect.animate().alpha(1).setDuration(500);
        switch (cChoice)
        {
            case 0:
                cValue = cpuTempList.get(0).getIntelligence();
                uValue = userTempList.get(0).getIntelligence();
                textSelect.setText("CPU SELECTED INTELLIGENCE");
                checkStatus(cValue, uValue);
                break;

            case 1:
                cValue = cpuTempList.get(0).getStrength();
                uValue = userTempList.get(0).getStrength();
                textSelect.setText("CPU SELECTED STRENGTH");
                checkStatus(cValue, uValue);
                break;

            case 2:
                cValue = cpuTempList.get(0).getSpeed();
                uValue = userTempList.get(0).getSpeed();
                textSelect.setText("CPU SELECTED SPEED");
                checkStatus(cValue, uValue);
                break;

            case 3:
                cValue = cpuTempList.get(0).getDurability();
                uValue = userTempList.get(0).getDurability();
                textSelect.setText("CPU SELECTED DURABILITY");
                checkStatus(cValue, uValue);
                break;

            case 4:
                cValue = cpuTempList.get(0).getPower();
                uValue = userTempList.get(0).getPower();
                textSelect.setText("CPU SELECTED POWER");
                checkStatus(cValue, uValue);
                break;

            case 5:
                cValue = cpuTempList.get(0).getCombat();
                uValue = userTempList.get(0).getCombat();
                textSelect.setText("CPU SELECTED COMBAT");
                checkStatus(cValue, uValue);
                break;
        }
    }





    protected void userCardUpdate()
    {
        userTempList.remove(0);
        userCard++;
        Character character = userArrayList.get(userCard);
        userTempList.add(character);
        userListView.setAdapter(userCharacterAdapter);
    }





    protected void cpuCardUpdate()
    {
        cpuTempList.remove(0);
        cpuCard++;
        Character character = cpuArrayList.get(cpuCard);
        cpuTempList.add(character);
        cpuListView.setAdapter(null);
    }





    protected void buttonDisable()
    {
        uIntellect.setEnabled(false);
        uStrength.setEnabled(false);
        uSpeed.setEnabled(false);
        uDurability.setEnabled(false);
        uPower.setEnabled(false);
        uCombat.setEnabled(false);
    }





    protected void checkStatus(int cValue, int uValue)
    {
        cpuListView.setAdapter(cpuCharacterAdapter);
        if (cValue > uValue)
        {
            cpuScore += 10;
            cpuScores.setText(String.valueOf(cpuScore));
            cpuTurn = true;
        }

        else if (cValue < uValue)
        {
            userScore +=10;
            userScores.setText(String.valueOf(userScore));
            cpuTurn = false;
        }

        cardsLeft--;
        textCardLeft.setText(String.valueOf(cardsLeft));
        btnControl.setVisibility(View.VISIBLE);
    }





    protected void showAlert()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Are you sure you want to quit?").setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String finalScore = String.valueOf(userScore);
                        String gameResult;

                        if (userScore > cpuScore)
                            gameResult = "WIN";
                        else
                            gameResult = "LOSS";

                        Intent intent = new Intent(MainActivity.this, FinishActivity.class);
                        intent.putExtra("SCORE", finalScore);
                        intent.putExtra("STATUS", gameResult);
                        intent.putExtra("CARDS", String.valueOf(perPlayerCards));
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = alert.create();
        dialog.setIcon(R.drawable.exit);
        dialog.setTitle("Confirm Quit");
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#214D22"));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#214D22"));
    }

}