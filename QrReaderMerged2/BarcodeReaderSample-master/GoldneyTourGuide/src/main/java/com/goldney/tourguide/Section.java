package com.goldney.tourguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Section {

    public Boolean isMedia = false;
    public Boolean is360 = false;
    public String title = "";
    public String description = "";
    public Bitmap image;
    public String path360 = "";
    public void setImage(Bitmap bitmap) {
        isMedia = true;
        image = bitmap;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public void setAllDetails(JSONObject obj, Context context, String folderName) throws JSONException, IOException {
        Log.d("sectionObject", "method called");
        Log.d("sectionObject2", obj.toString());

        if (obj.getString("type").equals("image")) {
            // REMEMBER TO INCLUDE THE FILE EXTENSION IN THE imgNAme

            isMedia = true;
            String imgName = obj.getString("image_name");
            //AssetManager assetManager = context.getAssets();
           // imgName = imgName.replaceAll("%", " ");
            Log.d("sectionObject3", (folderName) + "/" + imgName);
            //     InputStream istr = assetManager.open((folderName) + "/"  + imgName);

            InputStream istr = new FileInputStream(new File(context.getFilesDir().getPath() + "/" + (folderName) + "/" + imgName));
            Bitmap bitmapImage = BitmapFactory.decodeStream(istr);
            image = bitmapImage;
            Log.d("sectionObject", imgName);
        }
        else if(obj.getString("type").equals("360")){
            title = obj.getString("title");
            description = obj.getString("description");
            is360 = true;
            String imgName = obj.getString("image_name");
            Log.d("sectionObject3", (folderName) + "/" + imgName);
            path360 = context.getFilesDir().getPath() + "/" + (folderName) + "/" + imgName;
            Log.d("sectionObject3", path360);

        }
        else {
            title = obj.getString("title");
            description = obj.getString("description");

            Log.d("sectionObject", title);
            Log.d("sectionObject", description);


        }
    }
}
