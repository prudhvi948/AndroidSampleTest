package com.apppartner.androidprogrammertest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginActivity extends ActionBarActivity
{
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.btn_back);

        final EditText inputUsername = (EditText) findViewById(R.id.username);
        final EditText inputPassword = (EditText) findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.user_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                username = inputUsername.getText().toString();
                password = inputPassword.getText().toString();
                new NetworkOperation().execute(username, password);

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /* Moving network operations off the UI thread to avoid UI hiccup*/
    private class NetworkOperation extends AsyncTask<String, Void, ArrayList<String>>
    {

        long startTime, endTime;
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> dialogAlert = new ArrayList<String>();
            String username = params[0];
            String password = params[1];
            HttpClient httpClient = new DefaultHttpClient();
            startTime = System.currentTimeMillis();
            HttpPost httpPost = new HttpPost("http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php");

            try
            {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                endTime = System.currentTimeMillis();
                String responseBody = EntityUtils.toString(httpResponse.getEntity());
                JSONObject jsonObject = new JSONObject(responseBody);

                Iterator iterator = jsonObject.keys();
                while (iterator.hasNext())
                {
                    String key = (String) iterator.next();
                    dialogAlert.add((String)jsonObject.get(key));
                }
            }
            catch(UnsupportedEncodingException e)
            {
                Log.w("UnsupportedEncodingException", e);
            }
            catch(ClientProtocolException e)
            {
                Log.w("ClientProtocolException", e);
            }
            catch(IOException e)
            {
                Log.w("IOException", e);
            }
            catch(JSONException e)
            {
                Log.w("JSONException", e);
            }
            return dialogAlert;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            /* Dialog box to display response from server*/
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage(result.get(0) + ", " + result.get(1) + "\nTime elapsed: " + (endTime - startTime) + " ms");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            onBackPressed();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
