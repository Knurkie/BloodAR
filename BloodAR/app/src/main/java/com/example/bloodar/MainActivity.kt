package com.example.bloodar

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.ar.core.ArCoreApk
import java.util.*


class MainActivity : AppCompatActivity() {
    var mArButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mArButton = findViewById(R.id.arButton)
        maybeEnableArButton()

        val syndromeButton: Button = findViewById(R.id.syndromeButton)
        syndromeButton.setOnClickListener{openSyndromeListActivity()}

        mArButton!!.setOnClickListener{openArActivity()}

        val feedbackButton: FloatingActionButton = findViewById(R.id.feedbackButton)
        feedbackButton.setOnClickListener{openFeedbackActivity()}
    }

    private fun maybeEnableArButton() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isTransient) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            Handler().postDelayed({
                maybeEnableArButton()
            }, 200)
        }
        if (availability.isSupported) {
            mArButton!!.visibility = View.VISIBLE
            mArButton!!.isEnabled = true
        } else { // The device is unsupported or unknown.
            mArButton!!.visibility = View.INVISIBLE
            mArButton!!.isEnabled = false
        }
    }

    private fun openSyndromeListActivity() {
        val intent = Intent(this, SyndromeListActivity::class.java)
        startActivity(intent)
    }

    private fun openFeedbackActivity() {
        val intent = Intent(this, FeedbackActivity::class.java)
        startActivity(intent)
    }

    private fun openArActivity() {
        val intent = Intent(this, ArActivity::class.java).apply{addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT)}
        startActivity(intent)
    }
}