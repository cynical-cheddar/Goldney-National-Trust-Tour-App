package com.goldney.tourguide
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private val TAG: String = "main"
    private lateinit var mResultTextView: TextView

    private var fragmentID: String = "TileFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // fragmentID = savedInstanceState?.getString("fragmentName")


    }




        // Navigates to a specific fragment if the activity is loaded from camera










        //mResultTextView = findViewById(R.id.result_textview)

        /*findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.generate_barcode).setOnClickListener {
            val i = Intent(applicationContext, generateQRCodeImage::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.btn_UpdateJson).setOnClickListener {
            val i = Intent(applicationContext, downloadFileActivity::class.java)
            startActivity(i)
        } */


    }
