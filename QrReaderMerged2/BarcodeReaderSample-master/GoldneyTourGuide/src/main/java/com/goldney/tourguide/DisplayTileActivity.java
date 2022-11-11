package com.goldney.tourguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayTileActivity extends AppCompatActivity {
    String recievedID = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tile);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        if(in.getExtras() != null) {
            recievedID = b.getString("id");
        }

    }

    public String getID(){
        return recievedID;
    }

}
