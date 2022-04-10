package br.edu.ifrs.coffeetime

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private var menuList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.hide()

        menuList = findViewById(R.id.listview)

        menuList?.setOnItemClickListener(this)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 == 0) {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        if (p2 == 1) {
            val intent = Intent(this, OurProductsActivity::class.java)
            startActivity(intent)
        }

        if (p2 == 2) {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://www.cuponeria.com.br/busca/cafe"
                )
            )
            startActivity(intent)
        }

        if (p2 == 3) {
            val intent = Intent(this, PhotoGalleryActivity::class.java)
            startActivity(intent)
        }
    }
}