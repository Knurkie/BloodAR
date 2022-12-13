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

        val atrialFibrillationTESTButton: Button = findViewById(R.id.atrialFibrillationTEST)
        atrialFibrillationTESTButton.setOnClickListener{openAtrialFibrillationTESTActivity()}

        val atherosclerosisButton: Button = findViewById(R.id.atherosclerosis)
        atherosclerosisButton.setOnClickListener{openAtherosclerosisActivity()}

        val aneurysmButton: Button = findViewById(R.id.aneurysm)
        aneurysmButton.setOnClickListener{openAneurysmActivity()}

        val strokeButton: Button = findViewById(R.id.stroke)
        strokeButton.setOnClickListener{openStrokeActivity()}

        val heartAttackButton: Button = findViewById(R.id.heart_attack)
        heartAttackButton.setOnClickListener{openHeartAttackActivity()}

    }

    private fun openAtrialFibrillationActivity() {
        val intent = Intent(this, AtrialFibrillationActivity::class.java)
        startActivity(intent)
    }

    private fun openAtrialFibrillationTESTActivity() {
        val intent = Intent(this, AtrialFibrillationTESTActivity::class.java)
        startActivity(intent)
    }

    private fun openAtherosclerosisActivity() {
        val intent = Intent(this, AtherosclerosisActivity::class.java)
        startActivity(intent)
    }

    private fun openAneurysmActivity() {
        val intent = Intent(this, AneurysmActivity::class.java)
        startActivity(intent)
    }

    private fun openStrokeActivity() {
        val intent = Intent(this, StrokeActivity::class.java)
        startActivity(intent)
    }

    private fun openHeartAttackActivity() {
        val intent = Intent(this, HeartAttackActivity::class.java)
        startActivity(intent)
    }
}