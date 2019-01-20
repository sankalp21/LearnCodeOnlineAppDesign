package com.example.sankalpsinha.lcoui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainScreen extends AppCompatActivity {
    private Animation animation,animation1,animation2;
    private ImageView logo,logo1;
    private FrameLayout backlayout;
    private LinearLayout textlayout;
    private Handler handler;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        logo=(ImageView)findViewById(R.id.logo);
        logo1=(ImageView)findViewById(R.id.logo1);
        backlayout=(FrameLayout)findViewById(R.id.background);
        textlayout=(LinearLayout)findViewById(R.id.textlayout);
        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainScreen.this,RegisterScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        animationSetup();



    }

    private void animationSetup() {

        animation= AnimationUtils.loadAnimation(MainScreen.this, R.anim.scalelogo);
        animation1= AnimationUtils.loadAnimation(MainScreen.this, R.anim.backlayoutanim);
        animation2=AnimationUtils.loadAnimation(MainScreen.this, R.anim.textlayoutanim);

        logo1.startAnimation(animation);
        backlayout.startAnimation(animation1);
        final long time=logo1.getAnimation().getDuration();

        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                }
                catch (Exception e){

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        logo1.setVisibility(View.INVISIBLE);
                        backlayout.setVisibility(View.INVISIBLE);
                        textlayout.setVisibility(View.VISIBLE);
                        textlayout.startAnimation(animation2);
                        logo.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();

    }

}
