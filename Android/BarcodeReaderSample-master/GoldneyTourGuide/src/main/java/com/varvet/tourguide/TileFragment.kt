package com.varvet.tourguide

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_tile.*

class TileFragment : Fragment() {

    private var tourId: String? = "0"


    var tour: TourTile = TourTile()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tile, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = activity as DisplayTileActivity?
        tourId = activity!!.getID()

        Log.d("thing", "creating tour id")
        // if we have NOT gotten our data via DisplayTileActivuty
        if(tourId == null) {
            arguments?.let {
                tourId = it.getString("id")
            }
            if (tourId != null) {
                tour.setValuesFromJSON(tourId!!, this.context)
                Log.d("thing", "set the tour values")
            } else {
                Log.d("thing", "tourID == null")
            }
        }
        // if we have gotten our data via DisplayTileActivuty
        else{
            tour.setValuesFromJSON(tourId!!, this.context)
            Log.d("thing", "tours recieved from activuty")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(tour.description != null) {
            Log.d("thing", "Tour description = " + tour.description)
            txt_description.text = tour.description
            txt_title.text = tour.title
        }

        btn_backArrow.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.developerHome))
    }

}