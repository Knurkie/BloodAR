package com.example.bloodar

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val feedbackText: EditText = findViewById(R.id.feedbackText)
        val feedbackSendButton: Button = findViewById(R.id.feedbackSendButton)
        feedbackSendButton.setOnClickListener {openSendFeedback(feedbackText)}
    }

    private fun openSendFeedback(feedbackText: EditText) {
        feedbackText.text.clear()
        Toast.makeText(applicationContext, "Feedback sent!", Toast.LENGTH_SHORT).show()

    }
}