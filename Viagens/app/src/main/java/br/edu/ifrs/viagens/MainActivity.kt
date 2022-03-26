package br.edu.ifrs.viagens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Home")

        val myTripsButton = findViewById<Button>(R.id.myTripsButton);

        myTripsButton.setOnClickListener {
            val intent = Intent(this, TripsActivity::class.java)
            startActivity(intent)
        }
    }
}