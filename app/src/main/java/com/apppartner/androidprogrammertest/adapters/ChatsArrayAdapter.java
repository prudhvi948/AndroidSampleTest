package com.apppartner.androidprogrammertest.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.imagehelpers.ImageLoader;
import com.apppartner.androidprogrammertest.models.ChatData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{
    private Activity activity;
    private ImageLoader imageLoader;
    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell = new ChatCell();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.cell_chat, parent, false);

        chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        Typeface fontMachinato = Typeface.createFromAsset(getContext().getAssets(), "fonts/Jelloween - Machinato.ttf");
        chatCell.usernameTextView.setTypeface(fontMachinato);
        chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        Typeface fontMachinatoLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Jelloween - Machinato Light.ttf");
        chatCell.messageTextView.setTypeface(fontMachinatoLight);
        chatCell.avatarImageView = (ImageView) convertView.findViewById(R.id.avatarImageView);

        ChatData chatData = getItem(position);

        chatCell.usernameTextView.setText(chatData.getUsername());
        chatCell.messageTextView.setText(chatData.getMessage());
        imageLoader.displayImage(chatData.getAvatarURL(), chatCell.avatarImageView);

        return convertView;
    }

    private static class ChatCell
    {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView avatarImageView;
    }
}
