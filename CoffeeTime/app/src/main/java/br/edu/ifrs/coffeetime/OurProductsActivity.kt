package br.edu.ifrs.coffeetime

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class OurProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_our_products)

        val actionBar = supportActionBar

        actionBar!!.title = "Nossos produtos"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}