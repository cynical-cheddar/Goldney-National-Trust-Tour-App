package com.varvet.tourguide;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.graphics.Bitmap;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

public class generateQRCodeImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode_image);


        // Listener for the generate button
        final Button button = findViewById(R.id.btn_generateImage);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                EditText inputDataField = findViewById(R.id.txt_QRinputData);
                String inputData = inputDataField.getText().toString();

                    generateQRCodeImageVoid(inputData, 32, 32, QR_CODE_IMAGE_PATH);




                System.out.println("Code has been generated");
            }
        });


    }

    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    void generateQRCodeImageVoid(String inputData, int width, int height, String filePath){

       // BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
       // bitMatrix.clear();

      //  Path path = FileSystems.getDefault().getPath(filePath);

      //  MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

      //  Bitmap bitmap = BitmapFactory.decodeFile(path.toString());
        // display locally
       ImageView qrCodeView = findViewById(R.id.img_QRCode);
     //  Bitmap bmp = encodeAsBitmap(inputData, width, height);
      // qrCodeView.setImageBitmap();

        Bitmap myBitmap = QRCode.from(inputData).bitmap();

        qrCodeView.setImageBitmap(myBitmap);


    }

    Bitmap encodeAsBitmap(String str, int width, int height) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, width, height, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);
        return bitmap;
    }



    public  void main(String[] args) {
      //  try {
      //      generateQRCodeImageVoid("This is my first QR Code", 350, 350, QR_CODE_IMAGE_PATH);
      //  } catch (WriterException e) {
      //      System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
       // } catch (IOException e) {
      //      System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
      //  }
    }
}
