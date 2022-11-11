package com.goldney.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.zph.glpanorama.GLPanorama;

public class p360Activity extends AppCompatActivity {
    private GLPanorama mGLPanorama;

    // Image path is the full path
    private String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        setContentView(R.layout.activity_p360);
        mGLPanorama= (GLPanorama) findViewById(R.id.mgl);
        //传入全景图片

        // open bundle and load path
        Intent in = getIntent();
        Bundle b = in.getExtras();

        if(in.getExtras() != null) {
            imagePath = b.getString("path");
            Log.d("PanoramaBundle", "Success, loading image " + imagePath);
            mGLPanorama.setGLPanorama(imagePath);
        }
        else{
            Log.e("PanoramaBundle", "Asset not found, loading default");
        }
        // if there is no bundle or path, then just load a test image and output and error
       //
    }

}
