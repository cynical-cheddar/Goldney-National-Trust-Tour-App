package com.goldney.tourguide

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.goldney.tourguide.barcode.BarcodeCaptureActivity
import kotlinx.android.synthetic.main.fragment_developer_home.*

class developerHome : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_developer_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   val args = Bundle()
        //   args.putString("id", "0")

        // toTile1Button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.tileFragment, args))
        btn_launchCam.setOnClickListener {
            val intent = Intent(this.context, BarcodeCaptureActivity::class.java)
            startActivity(intent)

        }


        a360.setOnClickListener {
            val intent = Intent(this.context, p360Activity::class.java)
            startActivity(intent)

        }

        btn_tourList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.tileList))
        btn_exampleTile.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.tileList))
        img_settings.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.settings))
    }
}



/*findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
startActivity(intent)
}




} */
