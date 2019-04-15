package com.wipro.day2lab3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ToggleButton
import android.widget.ImageView
import com.wipro.day2lab3.R.drawable.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val arrayOfPictures = arrayOf(ball_1, ball_2, ball_3, ball_4, ball_5, ball_6, ball_7, ball_8, ball_9, ball_10)
    private var counter = 0
    private lateinit var prevButton: Button
    private lateinit var slideShowButton: ToggleButton
    private lateinit var nextButton: Button
    private lateinit var imageView: ImageView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prevButton = findViewById(R.id.button_one)
        slideShowButton = findViewById(R.id.button_two)
        nextButton = findViewById(R.id.button_three)
        imageView = findViewById(R.id.image_id)
        handler = Handler()

        // *************** Manual selection of the Prev Picture from Gallery ***********************
        prevButton.setOnClickListener {
            counter--
            //Toast.makeText(this@MainActivity, "Counter: $counter", Toast.LENGTH_SHORT).show()
            imageView.setImageResource(0)
            imageView.setBackgroundResource(arrayOfPictures[counter])

            if (counter == 0) counter = 10
        }

        // *************** Manual selection of the Next Picture from Gallery ***********************
        nextButton.setOnClickListener {
            counter++
            //Toast.makeText(this@MainActivity, "Counter: $counter", Toast.LENGTH_SHORT).show()
            imageView.setImageResource(0)
            imageView.setBackgroundResource(arrayOfPictures[counter])

            if (counter == 9) counter = -1
        }

        // *************** Run the Slide Show with pictures ****************************************
        slideShowButton.setOnClickListener {
            if (slideShowButton.isChecked) {
                Toast.makeText(this@MainActivity, "Runnable ON", Toast.LENGTH_SHORT).show()
                counter = 0
                handler.postDelayed(runnable,1000)
            }
            if (!slideShowButton.isChecked) {
                Toast.makeText(this@MainActivity, "Runnable OFF", Toast.LENGTH_SHORT).show()
                handler.removeCallbacks(runnable)
            }
        }

        runnable = Runnable {
            imageView.setImageResource(0)
            imageView.setBackgroundResource(arrayOfPictures[counter])
            handler.postDelayed(runnable,2000)
            counter++

            if (counter == 9) counter = 0
        }
    }
}
