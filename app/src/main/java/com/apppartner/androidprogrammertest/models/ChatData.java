package com.apppartner.androidprogrammertest.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.apppartner.androidprogrammertest.adapters.ChatsArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatData
{
    private static final String LOG_TAG = "ChatData";

    private int userID;
    private String username;
    private String avatarURL;
    private String message;

    public ChatData(JSONObject jsonObject)
    {
        if (jsonObject != null)
        {
            try
            {
                userID = jsonObject.getInt("user_id");
                username = jsonObject.getString("username");
                avatarURL = jsonObject.getString("avatar_url");
                message = jsonObject.getString("message");
            }
            catch (JSONException e)
            {
                Log.w(LOG_TAG, e);
            }
        }
    }

    public ChatData()
    {

    }

    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return userID;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setAvatarURL(String avatarURL){
        this.avatarURL = avatarURL;
    }
    public String getAvatarURL(){
        return avatarURL;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
