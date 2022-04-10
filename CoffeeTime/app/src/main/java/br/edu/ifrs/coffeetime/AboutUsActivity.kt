package br.edu.ifrs.coffeetime

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val actionBar = supportActionBar

        actionBar!!.title = "Sobre nós"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}