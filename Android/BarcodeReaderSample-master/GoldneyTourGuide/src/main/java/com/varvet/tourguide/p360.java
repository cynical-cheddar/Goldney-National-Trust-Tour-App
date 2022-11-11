package com.varvet.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.zph.glpanorama.GLPanorama;

public class p360 extends AppCompatActivity {
    private GLPanorama mGLPanorama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p360);
        mGLPanorama= (GLPanorama) findViewById(R.id.mgl);
        //传入全景图片
        mGLPanorama.setGLPanorama(R.drawable.imggugong);
    }


}
