package com.varvet.tourguide;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class TourTile {
    private String id;
    private String title;
    private String description;

//    com.varvet.barcodereadersample.TourTile(String tile, String description) {
//        this.title = tile;
//        this.description = description;
//    }

    TourTile() {

    }

    TourTile(String id, Context context) {
        String title = "undef";
        String json;
        try {
            InputStream is = context.getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("id").equals(id) ) {
                    this.setTitle(obj.getString("title"));
                    this.setDescription(obj.getString("description"));
                    //tileList.add(obj.getString("name"));
                }
                //tileList.add(obj.getString("name"));
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



    public void setValuesFromJSON (String id, Context context){
        Log.d("thing", "Settin the values of the tour");
        String title = "undef";
        String json;
        try {
            InputStream is = context.getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            Log.d("thing", "The json string we get is " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Log.d("thing", "Looping through the json array " + jsonArray);
                Log.d("thing", "id = " + id);

                if(obj.getString("id").equals(id) ) {
                    Log.d("thing", "the id we are trying to set stuff for is " + id);
                    this.setTitle(obj.getString("title"));
                    this.setDescription(obj.getString("description"));
                    //tileList.add(obj.getString("name"));
                }
                //tileList.add(obj.getString("name"));
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

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
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
}

