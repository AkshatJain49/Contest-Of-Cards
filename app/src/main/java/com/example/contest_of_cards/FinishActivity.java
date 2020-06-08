package com.example.contest_of_cards;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    ImageButton btn_reload, btn_home, btn_exit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        btn_reload = findViewById(R.id.ibtn_reload);
        btn_home = findViewById(R.id.ibtn_home);
        btn_exit1 = findViewById(R.id.ibtn_exit);

        Intent intent = getIntent();
        String score = intent.getStringExtra("SCORE");
        String stat = intent.getStringExtra("STATUS");
        final String cards = intent.getStringExtra("CARDS");

        TextView tscore = findViewById(R.id.txt_score);
        tscore.setText(score);
        ImageView img_win1 = findViewById(R.id.img_win);
        ImageView img_win2 = findViewById(R.id.img_you_won);

        ImageView img_lose1 = findViewById(R.id.img_lose);
        ImageView img_lose2 = findViewById(R.id.img_you_lose);
        if (stat.equals("LOSS")) {
            img_win1.setVisibility(View.INVISIBLE);
            img_win2.setVisibility(View.INVISIBLE);
            img_lose1.setVisibility(View.VISIBLE);
            img_lose2.setVisibility(View.VISIBLE);
        }

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                intent.putExtra("TOTAL_CARDS", cards);
                startActivity(intent);
                finish();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FinishActivity.this);
                alert.setMessage("Are you sure you want to exit?").setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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
                dialog.setTitle("Confirm Exit");
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#214D22"));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#214D22"));
            }
        });
    }
}