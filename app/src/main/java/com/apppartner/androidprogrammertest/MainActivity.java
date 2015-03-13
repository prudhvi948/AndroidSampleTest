package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView textView = (TextView) findViewById(R.id.coding_tasks);
        /* Custom font for TextView*/
        Typeface machinatoBold = Typeface.createFromAsset(getAssets(), "fonts/Jelloween - Machinato Bold.ttf");
        textView.setTypeface(machinatoBold);
    }

    public void onLoginButtonClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onChatButtonClicked(View v)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void onAnimationTestButtonClicked(View v)
    {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
