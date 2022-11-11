package com.varvet.tourguide;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.varvet.tourguide.R;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

public class downloadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);


        final Button button = findViewById(R.id.btn_DownloadFile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                EditText inputDataField = findViewById(R.id.txt_downloadURL);
                String inputData = inputDataField.getText().toString();

             //   new DownloadTask(downloadFileActivity.this, inputData);
                downloadFile(inputData);

            }
        });
    }


     public  void downloadFile(String uriPath) {
        DownloadManager downloadManager;
        downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(uriPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue((request));
    }
}
