package com.apppartner.androidprogrammertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;


public class AnimationActivity extends ActionBarActivity implements OnTouchListener
{
    private int xDelta;
    private int yDelta;
    private ViewGroup imageLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageLayout = (ViewGroup) findViewById(R.id.relative_image);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Animation");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.btn_back);

        /* Dialog box to display info about Animation*/
        AlertDialog.Builder builder = new AlertDialog.Builder(AnimationActivity.this);
        builder.setMessage("Drag the above icon to move it across the screen.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onResume();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

        final ImageView imageView = (ImageView) findViewById(R.id.logo);
        ImageButton btnFade = (ImageButton) findViewById(R.id.fade);

        /* Fade animation code*/
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnTouchListener(this);

        /* Start 'fade out' on button click*/
        btnFade.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                imageView.startAnimation(fadeOut);
            }
        });

        /* Listener that helps to start 'fade in' as soon
        * as 'fade out' completes.*/
        fadeOut.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ImageButton btnSpin = (ImageButton) findViewById(R.id.spin);

        /* Spin animation on button click*/
        btnSpin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ImageView imageView = (ImageView) findViewById(R.id.logo);
                Animation spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
                imageView.startAnimation(spin);
            }
        });
    }

    /* Code for dragging the image around the screen.
    Source: Internet tutorials*/
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                xDelta = X - lParams.leftMargin;
                yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        imageLayout.invalidate();
        return true;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
