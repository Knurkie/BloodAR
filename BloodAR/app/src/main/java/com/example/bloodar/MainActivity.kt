package com.example.bloodar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }
    /** Called when the user touches the 'Syndromes' button */
    fun sendSyndromeMessage(view: View) {
// Do something in response to button click
        Toast.makeText(this, "Display list of syndromes", Toast.LENGTH_SHORT).show()
    }
    /** Called when the user touches the 'AR' button */
    fun sendARMessage(view: View) {
// Do something in response to button click
        Toast.makeText(this, "Open camera and display AR model", Toast.LENGTH_SHORT).show()
    }
    /** Called when the user touches the 'AR' button */
    fun sendFeedbackMessage(view: View) {
// Do something in response to button click
        Toast.makeText(this, "Open feedback screen1", Toast.LENGTH_SHORT).show()
    }
}