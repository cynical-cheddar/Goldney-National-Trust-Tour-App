package com.goldney.tourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_load_screen.*

class loadScreen : Fragment() {


    var updateChecker: UpdateClient = UpdateClient()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_load_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        // update files

        // Update this when asset content delivery is fixed
       // updateChecker.populateStorageIfEmpty(this.context)

      //  updateChecker.populateStorageIfEmpty(this.context)


        btn_continue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.developerHome))
        btn_downloadUpdate.setOnClickListener{
            updateChecker.checkForUpdate(this.context)
        }
        btn_factory.setOnClickListener{
            updateChecker.loadFromFactory(this.context)
        }
    }
}



/*findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
startActivity(intent)
}


} */
