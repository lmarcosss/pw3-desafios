package br.edu.ifrs.viagens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TripsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        val actionBar = supportActionBar

        actionBar!!.title = "Minhas Viagens"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}