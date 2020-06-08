package com.example.contest_of_cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button btn_play, btn_exit;
    boolean play = true;
    MediaPlayer player;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        viewPager = findViewById(R.id.viewPager);
        btn_play = findViewById(R.id.btn_play);
        btn_exit = findViewById(R.id.btn_exit);
        spinner = findViewById(R.id.spinner);
        final FloatingActionButton actionButton = findViewById(R.id.floatingActionButton);
        player = MediaPlayer.create(this, R.raw.sound);
        player.start();
        player.setLooping(true);

        final String[] number = new String[]{"0", "10", "20", "30", "40"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.layout_spinner, number);
        spinner.setAdapter(spinnerAdapter);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ch = spinner.getSelectedItem().toString();
                Log.i("SPINNER", ch);

                if (ch.equals("0"))
                    Snackbar.make(findViewById(R.id.constraintLayout),"PLEASE CHOOSE VALID NO. OF CARDS", Snackbar.LENGTH_SHORT).show();

                else {
                    Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                    intent.putExtra("TOTAL_CARDS", ch);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!play) {
                    actionButton.setImageResource(R.drawable.volume);
                    player.start();
                    player.setLooping(true);
                    play = true;
                } else if (play) {
                    actionButton.setImageResource(R.drawable.novolume);
                    player.pause();
                    play = false;
                }
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(PlayActivity.this);
        viewPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimer(), 5000, 5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (play)
            player.start();
    }

    @Override
    public void onBackPressed() {
        showAlert();
    }

    void showAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(PlayActivity.this);
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

    public class MyTimer extends TimerTask {
        @Override
        public void run() {
            PlayActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0)
                        viewPager.setCurrentItem(1);
                    else if (viewPager.getCurrentItem() == 1)
                        viewPager.setCurrentItem(2);
                    else if (viewPager.getCurrentItem() == 2)
                        viewPager.setCurrentItem(3);
                    else
                        viewPager.setCurrentItem(0);
                }//INNER RUN ENDS
            });
        }// OUTER RUN ENDS
    }// MYTIMER ENDS
}

class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int[] images = {R.drawable.pager1, R.drawable.pager2, R.drawable.pager3, R.drawable.pager4};

    public ViewPagerAdapter(PlayActivity context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
        ImageView img = view.findViewById(R.id.imageView);
        img.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}