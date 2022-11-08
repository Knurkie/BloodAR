package com.example.bloodar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val syndromeButton: Button = findViewById(R.id.syndromeButton)
        syndromeButton.setOnClickListener {openSyndromeListActivity()}

        val feedbackButton: FloatingActionButton = findViewById(R.id.feedbackButton)
        feedbackButton.setOnClickListener {openFeedbackActivity()}
        }

    /** Called when the user touches the 'AR' button */
    fun sendARMessage(view: View) {
        Toast.makeText(this, "Open camera and display AR model", Toast.LENGTH_SHORT).show()
    }
    /** Called when the user touches the 'AR' button */
    fun sendFeedbackMessage(view: View) {
        Toast.makeText(this, "Open feedback screen1", Toast.LENGTH_SHORT).show()
    }

    private fun openSyndromeListActivity() {
        val intent = Intent(this, SyndromeListActivity::class.java)
        startActivity(intent)
    }

    private fun openFeedbackActivity() {
        val intent = Intent(this, FeedbackActivity::class.java)
        startActivity(intent)
    }
}