package com.spicysauce.lessstress;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spicysauce.lessstress.R;

import java.util.TimeZone;
import java.util.stream.IntStream;

public class Tamagochi extends AppCompatActivity {
    ImageView dish, window,karIm,bed,dark;
    ImageButton kar,sleep_button,home, lamp;
    ImageButton store111;
    AnimationDrawable eatAnim, sleepAnim,sleepStaticAnim, happyAnim;
    static int[] time = {100,100,100};
    boolean isTappedSleep = false;
    private SharedPreferences preferences;
    CountDownTimer healthT, healthT2;

    CountDownTimer HappynessTimer, FoodTimer;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamagochi);

        lamp = findViewById(R.id.lamp_tm);
        /*
        store111 = findViewById(R.id.store_button111);
        store111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent store = new Intent(Tamagochi.this,Store.class);
                startActivity(store);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        */
        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeTam = new Intent(Tamagochi.this, Thanks.class);
                homeTam.putExtra("activity",2);
                startActivity(homeTam);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        window = findViewById(R.id.window);
        preferences = getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
        updateEverything();
        home = findViewById(R.id.tools_icon);
        TextView textHealth = (TextView)findViewById(R.id.health);
        ProgressBar progressHealth = (ProgressBar)findViewById(R.id.vertical_progressbar1);
        TextView textEat = (TextView)findViewById(R.id.eat);
        ProgressBar progressEat = (ProgressBar)findViewById(R.id.vertical_progressbar2);
        TextView textHappy = (TextView)findViewById(R.id.happyness);
        ProgressBar progressHappy = (ProgressBar)findViewById(R.id.vertical_progressbar3);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEverything();
                //Tamagochi.this.onPause();
                Intent intent7 = new Intent(Tamagochi.this, MainScreen.class);
                startActivity(intent7);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

        TimeZone tz = TimeZone.getTimeZone("GMT+03");
        java.util.Calendar c = java.util.Calendar.getInstance(tz);
        String time2 = String.format("%02d" , c.get(java.util.Calendar.HOUR_OF_DAY));
        kar = findViewById(R.id.karambola);
        kar.setBackgroundResource(R.drawable.eat);
        dark = findViewById(R.id.dark);
        if (Integer.parseInt(time2) > 20){
            window.setBackgroundResource(R.drawable.w1_night);

        }
        else {
            dark.setVisibility(View.INVISIBLE);
            window.setBackgroundResource(R.drawable.w1_day);
        }

        // TIMERS ZONE ------------------------------------------------------------------------------------------------------------------------------------------------>

        FoodTimer = new CountDownTimer(2000000, 2000) { // this is a happyness timer (clearly)

            public void onTick(long millisUntilFinished) {

                time[1]=checkProgress(time[1]);
                textHappy.setText(checkDigit(time[1]));
                progressHappy.setProgress(checkProgress(time[1]));

                time[1]--;
                //Handler handler6 = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkMood(time);
                    }
                }, 3000);
            }

            private String checkDigit(int time) {
                return String.valueOf(time);
            }

            public void onFinish() {

            }

        };

        HappynessTimer = new CountDownTimer(2000000, 2500) { // this is a fucking eat

            public void onTick(long millisUntilFinished) {
                time[0]=checkProgress(time[0]);
                textEat.setText(checkDigit(time[0]));
                progressEat.setProgress(checkProgress(time[0]));
                //хуй
                //TODO: хуй


                time[0]--;
                //Handler handler6 = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkMood(time);
                    }
                }, 3000);
            }

            private String checkDigit(int time) {
                return String.valueOf(time);
            }

            public void onFinish() {

            }

        };

        healthT = new CountDownTimer(5000000, 5000) { // this is kinda what?

            public void onTick(long millisUntilFinished) {
                time[2]=checkProgress(time[2]);
                textHealth.setText(checkDigit(time[2]));
                progressHealth.setProgress(checkProgress(time[2]));

                time[2]--;
                //Handler handler6 = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkMood(time);
                    }
                }, 3000);

            }

            private String checkDigit(int time) {
                return String.valueOf(time);
            }

            public void onFinish() {

            }

        };

        // TIMERS ZONE END ################################################################################################################################################################################>

        dish = findViewById(R.id.yellow);


        dish.setOnLongClickListener(longClickListener);
        kar.setOnDragListener(dragListener);
        kar.setBackgroundResource(R.drawable.eat);
        eatAnim = (AnimationDrawable) kar.getBackground();
        bed = findViewById(R.id.bed);

        karIm = findViewById(R.id.karambolaIm);
        karIm.setBackgroundResource(R.drawable.sleep);
        sleepAnim = (AnimationDrawable) karIm.getBackground();
        sleep_button = findViewById(R.id.button_sleep);
        sleep_button.setBackgroundResource(R.drawable.sleep_off);




        kar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                kar.setBackgroundResource(R.drawable.happy_newsize);
                happyAnim = (AnimationDrawable) kar.getBackground();
                happyAnim.start();
                //Handler handler3 = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        happyAnim.stop();
                        kar.setBackgroundResource(R.drawable.eat);
                        eatAnim = (AnimationDrawable) kar.getBackground();
                        kar.setOnDragListener(dragListener);
                        time[0]+=10;
                        //Handler handler5 = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                checkMood(time);
                            }
                        }, 3000);

                    }
                }, 3000);
                return false;
            }
        });
        sleep_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isTappedSleep == false) {
                    karIm.setVisibility(View.VISIBLE);
                    kar.setVisibility(View.GONE);
                    bed.setVisibility(View.GONE);
                    karIm.setBackgroundResource(R.drawable.sleep);
                    sleepAnim.start();
                    dark.setVisibility(View.VISIBLE);
                    sleep_button.setEnabled(false);
                    sleep_button.setBackgroundResource(R.drawable.sleep_on);
                    //Handler handler2 = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            sleepAnim.stop();
                            karIm.setBackgroundResource(R.drawable.sleep_static);
                            sleepStaticAnim = (AnimationDrawable) karIm.getBackground();
                            sleepStaticAnim.start();
                            healthT.cancel();
                            sleep_button.setEnabled(true);

                            healthT2 = new CountDownTimer(500000, 2000) { // i don't give a fuck what is it actually

                                public void onTick(long millisUntilFinished) {
                                    time[2] = checkProgress(time[2]);
                                    textHealth.setText(checkDigit(time[2]));
                                    progressHealth.setProgress(checkProgress(time[2]));

                                    time[2]++;
                                    //Handler handler4 = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            checkMood(time);
                                        }
                                    }, 3000);


                                }

                                private String checkDigit(int time) {
                                    return String.valueOf(time);
                                }

                                public void onFinish() {
                                    //healthT2.cancel();
                                }

                            }.start();
                        }
                    }, 4500);
                    isTappedSleep = true;

                }
                else {

                    dark.setVisibility(View.GONE);
                    sleep_button.setBackgroundResource(R.drawable.sleep_off);
                    sleepStaticAnim.stop();
                    karIm.setVisibility(View.GONE);
                    kar.setVisibility(View.VISIBLE);
                    bed.setVisibility(View.VISIBLE);
                    healthT2.cancel();
                    healthT.start();
                    isTappedSleep = false;


                }


            }

        });



    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myshadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myshadowBuilder,v,0);
            return false;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener(){

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();
                    if (view.getId()== R.id.yellow) {
                        kar.setBackgroundResource(R.drawable.eat);
                        eatAnim = (AnimationDrawable) kar.getBackground();
                        eatAnim.start();
                        time[1]+=20;
                        checkProgress(time[1]);

                        //Handler handler1 = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                eatAnim.stop();
                            }
                        }, 3000);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };

    public void checkMood(int[] time){
        int total = IntStream.of(time).sum();
                if (total<200) {
                    //Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            kar.setBackgroundResource(R.drawable.sad11);

                            if (total<140) {
                                kar.setBackgroundResource(R.drawable.sad21);
                                if (total<60) {
                                    kar.setBackgroundResource(R.drawable.sad31);
                                }

                            }
                        }
                    }, 3000);


                }
                else {
                    kar.setBackgroundResource(R.drawable.happy1_new);
                }
    }
    public int checkProgress(int progress) {
        if (progress>100){
            progress=100;
        }
        if (progress<=0) {
            progress=0;
        }
        return progress;
    }

    private void saveEverything() {
        SharedPreferences.Editor editor;
        editor = preferences.edit();

        editor.putInt("time1",time[1]);
        editor.putInt("time0",time[0]);
        editor.putInt("time2",time[2]);
//		this.putDateToSharedPreference("selectedDate", selectedDate);
        editor.apply();
    }
    private void updateEverything() {
        time[0] = preferences.getInt("time0",100);
        time[1] = preferences.getInt("time1",100);
        time[2] = preferences.getInt("time2",100);
    }

    @Override
    protected void onStart(){
        super.onStart();

        HappynessTimer.start();
        FoodTimer.start();
        healthT.start();

    }

    /*@Override
    protected void onStop() {
        super.onStop();

        healthT.cancel();
        HappynessTimer.cancel();
        FoodTimer.cancel();

    }*/

    @Override
    protected void onPause() // necesserily required - as Tamagochi consumes more and more RAM, this feature decreases consuming level, if MainScreen has been activated
    {

        super.onPause();

        healthT.cancel();
        HappynessTimer.cancel();
        FoodTimer.cancel();

    }

    /*@Override
    protected void onDestroy(){
        healthT.cancel();
        HappynessTimer.cancel();
        FoodTimer.cancel();

        super.onDestroy();

    }*/


}
