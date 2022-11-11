package com.goldney.tourguide

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_tile.*


class TileFragment : Fragment() {

    //change this when we don't need to use the test file anymore
    var seekHandler = Handler()

    private var jsonFileName: String = "getTiles.json"
    private var tourId: String? = "67"
    private var audioFile: String? = ""
    private var panoramaImagePath: String = ""
    private var tileType: String = "Normal"
    val player = MediaPlayer()
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
        tourId = activity!!.id
        // if we have NOT gotten our data via DisplayTileActivity
        if(tourId == null) {
            arguments?.let {
                tourId = it.getString("id")
            }
            if (tourId != null) {
                tour.setValuesFromJSON(tourId!!, this.context, jsonFileName)

            } else {
            }
        }
        // if we have gotten our data via DisplayTileActivuty
        else{
            tour.setValuesFromJSON(tourId!!, this.context, jsonFileName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.stop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(tour.sections.size > 0) sectionTile()
        else legacyTile()
        btn_backArrow.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.developerHome))
    }

    
    // This is the method called when we are using our updated content display method.
    private fun sectionTile(){

        val tileSections = tour.sections
            if(tour.panoramaImagePath != ""){
                panoramaImagePath = tour.panoramaImagePath;
                Log.d("360view", "Adding button 1")
                add360ButtonAsSection(panoramaImagePath)
                Log.d("360view", "Adding button 2")
            }
        // Get all section objects and display them in the scrollview.
        for (section in tileSections){
            //display section

            Log.d("SectionDisplay", tileSections.size.toString())
            displaySection(section)
        }
    
        // Set the tile's master description
        if(tour.description != null) {


            txt_description.text = tour.description
            txt_title.text = tour.title
        }

        // If we successfully locate an audio file, we should enable the tile's audio player
        Log.d("HideAudio", "Test about to ruin " + tour.audioFileName)
        if(tour.audioFileName != ""){
            Log.d("HideAudio", "Audio will not be hidden. Filename is:  " + tour.audioFileName)
            startSound(tour.audioFileName)
        }
        else{
            Log.d("HideAudio", "Audio WILL be hidden. Filename is:  " + tour.audioFileName)
            hideAudioPlayer()
        }
    }

    private fun hideAudioPlayer(){
        seekBar.visibility = View.INVISIBLE;
        btnPausePlay.visibility = View.INVISIBLE;
    }

    private fun add360ButtonAsSection(imagePath: String){
        if(imagePath != "") create360Button(imagePath ,sectionLayout)
    }

    private fun displaySection(section: Section){

        if(section.isMedia){
            Log.d("SectionDisplay", section.image.toString())
            createImageView(section.image, sectionLayout)
        }
        else if(section.is360){
            panoramaImagePath = section.path360

            if(panoramaImagePath != ""){
                add360ButtonAsSection(section.path360)
            }

        }
        else{
            if(section.title != ""){
                //create title display
                Log.d("SectionDisplay", section.title.toString())
                createTextView(section.title, sectionLayout, 22f, Color.BLACK)

            }
            if(section.description != ""){
                // create description display
                Log.d("SectionDisplay", section.description.toString())
                createTextView(section.description, sectionLayout, 16f, Color.GRAY)
            }
        }
    }
    // Legacy tile displayer. Does not use sections. Included to prevent app crashes with an old JSON file.
    private fun legacyTile(){
        if(tour.description != null) {
            txt_description.text = tour.description
            txt_title.text = tour.title
        }
        // If we successfully locate an audio file, we should enable the tile's audio player
        if(tour.audioFileName != ""){
            startSound(tour.audioFileName)
        }

        if(tour.images != null){
            // for each bitmap, create an image view
            tour.images.forEach{
                createImageView(it, tile_Image_Gallery)
            }
        }
    }



    // Creates a text view with the given string and displays it in a given layout.

    private  fun createTextView(string: String, linearLayout: LinearLayout, fontsize : Float, color: Int){
        val textView = TextView(this.context)
        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        textView.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
        textView.text = string
        textView.setPadding(10,10,10,10)
        textView.setTextSize(fontsize)
        textView.setTextColor(color)
        linearLayout.addView(textView)
    }
    // Creates an image view with the given bitmap and displays it in a given layout.
     private fun createImageView(bitmap: Bitmap, linearLayout: LinearLayout){
        val imageView = ImageView(this.context)
        // setting height and width of imageview

        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)


        imageView.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, 409)


        //accessing our relative layout from activity_main.xml
        imageView.setImageBitmap(bitmap)

        // Add ImageView to LinearLayout
        linearLayout.addView(imageView) //adding image to the layout
    }

    private fun create360Button(imagePath: String, linearLayout: LinearLayout){
        val button = Button(this.context)

        button.layoutParams = LinearLayout.LayoutParams(400, 100)
        button.setText("Launch 360 viewer")
        linearLayout.addView(button) //adding image to the layout
        button.setOnClickListener{
            val intent = Intent(this.context, p360Activity::class.java)
            // Bundle the image path
            val b = Bundle()
            // Detected QR CODE
            b.putString("path", panoramaImagePath)
            intent.putExtras(b)
            startActivity(intent)
        }
    }

    // THIS IS ALL OF THE AUDIO STUFF

    //-------------------------------------------------------------------------------------------

    private fun initialiseSeekbarControls(){
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    player.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun initialisePauseControls(){
        val toggle: ToggleButton = btnPausePlay
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                player.pause()
                // The toggle is enabled
            } else {
                player.start()
                seekHandler.removeCallbacks(moveSeekBarThread)
                seekHandler.postDelayed(moveSeekBarThread, 100) //cal the thread after 100 milliseconds
                // The toggle is disabled
            }
        }
    }




    private fun startSound(filename: String) {
        // remember that this uses assets
      //  val afd = context?.assets?.openFd(filename)
        Log.d("audioPlayer", filename)

        if (filename != null) {
            player.setDataSource(filename)
            Log.d("audioPlayer", "setDataSource")
            player.prepare()
            player.start()
            player.setVolume(100.0f, 100.0f)
            val mediaPos = player.currentPosition
            val mediaMax = player.duration
            seekBar.max = mediaMax // Set the Maximum range of the
            seekBar.progress = mediaPos// set current progress to song's
            seekHandler.removeCallbacks(moveSeekBarThread)
            seekHandler.postDelayed(moveSeekBarThread, 100) //cal the thread after 100 milliseconds
            initialiseSeekbarControls()
            initialisePauseControls()
        }
    }

    // This is a coroutine.
    // It interpolates the position of the audio seek track based on the current time.
    private val moveSeekBarThread = object : Runnable {
        override fun run() {
            if (player.isPlaying) {
                val posNew = player.currentPosition
                val maxNew = player.duration
                seekBar.max = maxNew
                seekBar.progress = posNew
                seekBar.postDelayed(this, 100) //Looping the thread after 0.1 second
            }
        }
    }


    //-------------------------------------------------------------------------------------------



}