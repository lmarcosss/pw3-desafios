package com.example.organic.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.organic.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val actionBar = supportActionBar

        actionBar!!.title = "Sobre"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}