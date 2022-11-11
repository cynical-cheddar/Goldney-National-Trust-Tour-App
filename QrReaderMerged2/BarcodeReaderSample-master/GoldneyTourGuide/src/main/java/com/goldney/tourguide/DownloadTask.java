package com.goldney.tourguide;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadTask {


    String inputURL = "";
    String relativeOutputDirectory  = "";
    String jsonFileExtension = "";
    UpdateClient callerObject;
    Boolean callback = false;
   // Context context;
    String root;
    String newName;
    public void fileDownloadFromURL(String inputURLLocal,  String relativeOutputDirectoryLocal, String fileName, Context context, String optionalFileExtension, UpdateClient caller, boolean shouldCallback) throws IOException {

        callback = shouldCallback;
        callerObject = caller;
        inputURL = inputURLLocal;
        relativeOutputDirectory = relativeOutputDirectoryLocal;
        newName = fileName;
        root = context.getFilesDir().getAbsolutePath();
     //   downloadUsingNIO(inputURL, outputPath);
        jsonFileExtension = optionalFileExtension;

        if(inputURL != "") {
            new downloadAsync().execute();
        }
    }







    private class downloadAsync extends AsyncTask <String, Integer, String> {
        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            Log.d("downloadAsync", inputURL);
           // super.onPreExecute();
         //   System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {


                URL url = new URL(inputURL);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
               // String filename = Paths.get(new URI(inputURL).getPath()).getFileName().toString();
                String filename = newName;

                Log.d("downloadAsync", "dlPath: " + root+relativeOutputDirectory+"/"+filename + jsonFileExtension);
                OutputStream output = new FileOutputStream(root+relativeOutputDirectory+"/"+filename + jsonFileExtension);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }



        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {

            System.out.println("onPostExecute");
            if(callback){
                try {
                    callerObject.callbackCreateAssetFolders();
                    System.out.println("callbackCreateAssetFolders");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        protected void onProgressUpdate(Integer... progress) {

        }



    }


}
