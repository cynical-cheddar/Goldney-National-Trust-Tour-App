package com.goldney.tourguide;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UpdateClient {

    Context masterContext;

    // MAIN METHOD CALLED ON STARTUP
    public void checkForUpdate(Context context) throws IOException, JSONException {
        masterContext = context;
      //  forcePopulationFromAssets(context);
        populateStorageIfEmpty(context);

        // change back to update
        if(checkForInternetConnection(context)) {
            deleteRecursive(context, context.getFilesDir());
            update(context);
        }
        else {
            Log.d("checkForUpdate", "checkForUpdate: Failed");
        }
    }

    public void loadFromFactory(Context context){
        masterContext = context;
        deleteRecursive(context, context.getFilesDir());
        try {
            forcePopulationFromAssets(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    boolean checkForInternetConnection(Context context){
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    void update(Context context) throws IOException, JSONException {
        Log.d("checkForUpdate", "checkForUpdate: Passed");
        downloadFile(context, "https://api.goldneyhall.com/v1/getTiles", "getTiles" ,"", ".json", true);
        // Create asset folders as per id.
        // Add in a test txt file to each folder to populate it



        // Find and download images to each folder by looping through sections

        // Download audio (potentially)
    }

    public void callbackCreateAssetFolders() throws IOException, JSONException {

        createAssetFolders(masterContext);
        Log.e("callbackCreateAssetFolders", "Callback called");
    }

    void createAssetFolders(Context context) throws IOException, JSONException {
        String json;
        InputStream is = new FileInputStream(new File(context.getFilesDir() + "/" + "getTiles.json"));

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        json = new String(buffer, "UTF-8");
        JSONArray jsonArray = new JSONArray(json);


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            makeDirectory(context, obj.getString("id"));
            // For each directory, download the assets we need.
            // Download 360 if it exists
            // Download audio if it exists
            // Download tile images if they exist
            List<String> sectionLinks = new ArrayList<>();
            List<String> sectionImageNames = new ArrayList<>();
            sectionLinks = getSectionLinksFromJSON(obj, context,  obj.getString("id"));
            sectionImageNames = getSectionImageNamesFromJSON(obj);
            int j = 0;

            // Download audio if there are any
            if(obj.has("audio_link")){
                downloadFile(context, obj.getString("audio_link"), obj.getString("audio_name"), "/" + obj.getString("id"), "", false);
            }
            // Download section media
            for(String sectionLink : sectionLinks) {
                    downloadFile(context, sectionLink, sectionImageNames.get(j), "/" + obj.getString("id"), "", false);
                    j++;
            }
        }
    }

    void makeDirectory(Context context, String path) throws IOException {
        String fullPath = context.getFilesDir().getPath() + "/" + path;
        Log.d("copyFileOrDir", "new full path: " + fullPath);

        File dir = new File(fullPath);
        if (!dir.exists())
            dir.mkdir();
            makeDummyFile(fullPath + "/dummy.txt");
    }
    void makeDummyFile(String path) throws IOException {
        String content = "Dummy!";
        Files.write( Paths.get(path), content.getBytes());
    }


    public void populateStorageIfEmpty(Context context) throws IOException {
        if(!isFilePresent(("getTiles.json"), context)){
            Log.d("populateStorage", "getTiles.json has not been found in storage, using stock assets");
            forcePopulationFromAssets(context);
            Log.d("populateStorage", "copy success");
        }
        else{
            Log.d("populateStorage", "getTiles.json has been found in storage, no need to copy");
        }
    }

    public void forcePopulationFromAssets(Context context) throws IOException {
        Log.d("forcePopulationFromAssets", "Forcing population from assets");
        File directory = context.getFilesDir();
        deleteRecursive(context, context.getFilesDir());
        (context, "");

    }

    // Downloads a file to the internal s
    void downloadFile(Context context, String inputURL,String fileName ,String relativeOutputDir, String jsonExtension, Boolean callback) throws IOException {
        DownloadTask dl = new DownloadTask();

        dl.fileDownloadFromURL(inputURL, relativeOutputDir, fileName ,context, jsonExtension, this, callback);
    }

    void showDirectory(File fileOrDirectory){
        for (File child : fileOrDirectory.listFiles())
            Log.d("downloadTest", child.getName());
    }


    void deleteRecursive(Context context, File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(context, child);
        Log.d("deleteRecursive", "DELETED: " + fileOrDirectory);
        fileOrDirectory.delete();
    }


    private boolean isFilePresent(String fileName, Context context) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }


















    // Internal Storage APK updater
    // ----------------------------------------------------------

    private void copyAssetsToInternalStorage(Context context, String path) throws IOException {

        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
            Log.d("copyAssetsToInternalStorage", "files length: " + files.length);
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
              //  in = assetManager.open(filename);
              //  File outFile = new File(context.getFilesDir(), filename);
              //  out = new FileOutputStream(outFile);
                Log.d("copyAssetsToInternalStorage", "initial root: " + filename);
                copyFileOrDir(filename, context);

            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }

    }
    private void copyFileOrDir(String path, Context context) {
        AssetManager assetManager = context.getAssets();
        String assets[] = null;
      //  List<String> assets = new ArrayList<>();
        try {
            assets = assetManager.list(path);
            Log.d("copyFileOrDir", "assets length: " + assets.length);
            Log.d("copyFileOrDir", "assets path: " + path);
            if (assets.length == 0) {
                copyFile(path, context);
            } else {
                String fullPath = context.getFilesDir().getPath() + "/" + path;
                Log.d("copyFileOrDir", "new full path: " + fullPath);

                File dir = new File(fullPath);
                if (!dir.exists())
                    dir.mkdir();
              //  for (int i = 0; i < assets.length; ++i) {

              //  }
                for (String file : assets) {

                    Log.d("copyAssetsToInternalStorage", "file recursive name: " + file);
                    copyFileOrDir( path + "/" + file, context);
                }
                }

        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }
    }


    private void copyFile(String filename, Context context) {
        Log.d("copyFileFunc", "copyFile: " + filename);
        Log.d("copyFileFunc", "copyFile source: " + context.getAssets() + "/" + filename);
        Log.d("copyFileFunc", "copyFile dest: " + context.getFilesDir().getPath() + "/" + filename);

        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            String newFileName = context.getFilesDir().getPath() + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }












    List<String> getSectionLinksFromJSON(JSONObject obj, Context context, String folderName) throws JSONException, IOException {
        JSONArray sectionInfo = null;
        List<String> sectionLinks = new ArrayList<>();
        if (obj.has("sections") && obj.isNull("sections")) {
            // Do something with object.
            // This is just a dummy file
            sectionLinks.add("");
        }
        else {
            sectionInfo = obj.getJSONArray("sections");

            for (int j = 0; j < sectionInfo.length(); j++) {
                    if (sectionInfo.getJSONObject(j).getString("type").equals("image")) {
                            System.out.println("Added to sectionLinks " + sectionInfo.getJSONObject(j).getString("image_link"));
                            sectionLinks.add(sectionInfo.getJSONObject(j).getString("image_link"));
                    }
                    if (sectionInfo.getJSONObject(j).getString("type").equals("360")) {
                        System.out.println("Added to sectionLinks " + sectionInfo.getJSONObject(j).getString("image_link"));
                        sectionLinks.add(sectionInfo.getJSONObject(j).getString("image_link"));
                    }
                }
        }

        Log.d("getSectionLinksFromJSON", String.valueOf(sectionLinks.size()));
        return sectionLinks;
    }
    List<String> getSectionImageNamesFromJSON(JSONObject obj) throws JSONException, IOException {
        JSONArray sectionInfo = null;
        List<String> sectionLinks = new ArrayList<>();
        if (obj.has("sections") && obj.isNull("sections")) {
            // Do something with object.
            // This is just a dummy file
            sectionLinks.add("");
        }
        else {
            sectionInfo = obj.getJSONArray("sections");

            for (int j = 0; j < sectionInfo.length(); j++) {
                if (sectionInfo.getJSONObject(j).getString("type").equals("image")) {
                    System.out.println("Added to sectionName " + sectionInfo.getJSONObject(j).getString("image_name"));
                    sectionLinks.add(sectionInfo.getJSONObject(j).getString("image_name"));
                }
                if (sectionInfo.getJSONObject(j).getString("type").equals("360")) {
                    System.out.println("Added to sectionName " + sectionInfo.getJSONObject(j).getString("image_name"));
                    sectionLinks.add(sectionInfo.getJSONObject(j).getString("image_name"));
                }
            }
        }

        Log.d("getSectionNamesFromJSON", String.valueOf(sectionLinks.size()));
        return sectionLinks;
    }











   /* private void copyAssetsToInternalStorage(Context context) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {

            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(context.getFilesDir(), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    } */






}
