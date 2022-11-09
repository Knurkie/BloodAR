package com.example.bloodar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SyndromeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syndrome_list)

        val atrialFibrillationButton: Button = findViewById(R.id.atrialFibrillation)
        atrialFibrillationButton.setOnClickListener{openAtrialFibrillationActivity()}

        val atherosclerosisButton: Button = findViewById(R.id.atherosclerosis)
        atherosclerosisButton.setOnClickListener{openAtherosclerosisActivity()}

    }

    private fun openAtrialFibrillationActivity() {
        val intent = Intent(this, AtrialFibrillationActivity::class.java)
        startActivity(intent)
    }

    private fun openAtherosclerosisActivity() {
        val intent = Intent(this, AtherosclerosisActivity::class.java)
        startActivity(intent)
    }
}