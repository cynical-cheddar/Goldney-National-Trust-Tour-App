package com.goldney.tourguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TourTile {
    private String id;
    private String title;
    private String description;
    String tileType = "Normal";
    // Now trying to add images
    List<Bitmap> images = new ArrayList<>();
    List<Section> sections = new ArrayList<>();
    String audioFileName = "";
    String panoramaImagePath = "";

//    com.goldney.barcodereadersample.TourTile(String tile, String description) {
//        this.title = tile;
//        this.description = description;
//    }

    TourTile() {

    }





    public void setValuesFromJSON (String id, Context context, String jsonFile){

        String title = "undef";
        String json;
        Boolean found = false;
        try {
          //  InputStream is = context.getAssets().open(jsonFile);
            Log.d("setValuesFromJSON", "setValuesFromJSON path: " + context.getFilesDir() + "/" + jsonFile);
            InputStream is =  new FileInputStream(new File(context.getFilesDir() + "/" + jsonFile));

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                if(obj.getString("id").equals(id) ) {
                    found = true;
                    this.setTitle(obj.getString("title"));
                    this.setDescription(obj.getString("description"));
                  //  this.setType(obj.getString("type"));
                   // if(obj.getString("type") != null) {
                  //      this.setType(obj.getString("type"));
                  //  }
                    //tileList.add(obj.getString("name"));
                        // If the media folder is specified, then load the relevent media
                    Log.d("360view", "JSON says that the tile is: " + tileType);
                  //  if(tileType.equals("360")) {
                        panoramaImagePath = getPanoramaImagePathFromFolder(context, obj.getString("id"));
                        setPanoramaImagePath(getPanoramaImagePathFromFolder(context, obj.getString("id")));
                   // }

                       // List<Bitmap> imagesToSet = getImagesFromMediaFolder(context.getFilesDir() + "/" + obj.getString("id"), context, obj.getString("id"));
                      //  setImages(imagesToSet);
                        audioFileName = getAudioFilePathFromFolder(context.getFilesDir() + "/" + obj.getString("id"), context, obj.getString("id"));
                        setAudio(getAudioFilePathFromFolder(context.getFilesDir() + "/" + obj.getString("id"), context, obj.getString("id")));


                    if(obj.get("sections") != null){
                        sections = getSectionsFromJSON(obj, context,  obj.getString("id"));
                    }
                }
                //tileList.add(obj.getString("name"));
            }
            if(found == false){
                setDefaultParameters();
            }
            this.id = id;
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setDefaultParameters(){
        this.setTitle("Invalid Tile");
        this.setDescription("Invalid Tile");

    }

    List<Section> getSectionsFromJSON(JSONObject obj, Context context, String folderName) throws JSONException, IOException {
        JSONArray sectionInfo = null;
        List<Section> sectionObjects = new ArrayList<>();

        sectionInfo = obj.getJSONArray("sections");

        for (int j = 0; j < sectionInfo.length(); j++) {
            Log.d("section", sectionInfo.getJSONObject(j).toString() );
            Section newSection = new Section();
            newSection.setAllDetails(sectionInfo.getJSONObject(j), context, folderName);
            sectionObjects.add(newSection);
        }

        Log.d("section", String.valueOf(sectionObjects.size()));
        return sectionObjects;
    }





    private String getAudioFilePathFromFolder(String path, Context context, String folderName) {
        String[] list;
        String filepath = "";
      //  AssetManager assetManager = context.getAssets();
        String folderPath = context.getFilesDir().getPath() + "/" + folderName;
        File folder = new File(folderPath);
        list = folder.list();
        Log.d("audioFinder", "finding audio file at:  " + folderPath);
        Log.d("audioFinder", "folder size is :  " + list.length);
        if (list.length > 0) {
            // This is a folder
            for (String file : list) {
                if(getFileExtension(file).contains("wav") || getFileExtension(file).contains("mp3") ){

                 //   filepath =  context.getAssets() + "/" + folderName + "/" + file;

                    filepath = folderPath + "/" + file;
                    Log.d("audioFinder", "Found audio file at:  " + filepath);

                }
            }
        }

        return filepath;
    }
    private String getPanoramaImagePathFromFolder(Context context, String folderName) {
        String[] list;
        String filepath = "";
        //  AssetManager assetManager = context.getAssets();
        String folderPath = context.getFilesDir().getPath() + "/" + folderName;
        File folder = new File(folderPath);
        list = folder.list();
        if (list.length > 0) {
            // This is a folder
            for (String file : list) {
                if(getFileExtension(file).contains("jpg") || getFileExtension(file).contains("png") ){
                    //   filepath =  context.getAssets() + "/" + folderName + "/" + file;
                    if(file.startsWith(("360"))){
                        filepath = folderPath + "/" + file;
                    }
                }
            }
        }

        return filepath;
    }

    private List<Bitmap> getImagesFromMediaFolder(String path, Context context, String folderName) {
        Log.d("bitmapFinder", "Bitmap method called");
        Log.d("bitmapFinder", "path is:  " + path);
        Log.d("bitmapFinder", "path could be:  " + context.getAssets());
        String folderPath = context.getFilesDir().getAbsolutePath() + "/" + folderName;
        File folder = new File(folderPath);
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        String[] list;
        try {

            list = folder.list();
            Log.d("bitmapFinder", "list length is:  " + Integer.toString(list.length));
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    Log.d("bitmapFinder", "File in the directory is called  " + file);
                    if(getFileExtension(file).contains("bmp") || getFileExtension(file).contains("png") || getFileExtension(file).contains("jpg") ){
                        // We have a bitmap, now att it to our list
                        Log.d("bitmapFinder", "bitmap found ");

                      //  InputStream istr = assetManager.open((folderName) + "/"  + file);
                        InputStream istr =  new FileInputStream(new File(context.getFilesDir() + "/" + folderName + "/" + file));
                        Bitmap bitmapImage = BitmapFactory.decodeStream(istr);
                        bitmaps.add(bitmapImage);
                        Log.d("bitmapFinder", "File name is " + file);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmaps;
    }



    private static String getFileExtension(String file) {
        String fileName = file;
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the title
     */
    public String getTileType() {
        return tileType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(List<Bitmap> foundImages) {
        images = foundImages;
    }

    public void setAudio(String path) {
        audioFileName = path;
    }

    public void setPanoramaImagePath(String path) {
        panoramaImagePath = path;
    }

    public void setType(String typeString){tileType = typeString;}
}

