package com.varvet.tourguide

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.gms.vision.barcode.Barcode

import com.varvet.tourguide.R
import com.varvet.tourguide.barcode.BarcodeCaptureActivity
import kotlinx.android.synthetic.main.fragment_developer_home.*
import kotlinx.android.synthetic.main.fragment_load_screen.*
import kotlinx.android.synthetic.main.fragment_tile_list.*

class loadScreen : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_load_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btn_continue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.developerHome))
    }
}



/*findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
startActivity(intent)
}


} */
