package com.example

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.getBundleExtra("extra")
        setContentView(R.layout.activity_detail)
        findViewById<TextView>(R.id.detail_rating).text = "${MainActivity.Constants.RATING}: ${bundle?.getString(MainActivity.Constants.RATING)}"
        findViewById<TextView>(R.id.detail_title).text = "${MainActivity.Constants.TITLE}: ${bundle?.getString(MainActivity.Constants.TITLE)}"
        findViewById<TextView>(R.id.detail_year).text = "${MainActivity.Constants.YEAR}: ${bundle?.getString(MainActivity.Constants.YEAR)}"
        findViewById<TextView>(R.id.detail_date).text = "${MainActivity.Constants.DATE}: ${bundle?.getString(MainActivity.Constants.DATE)}"
    }
}

