package com.dicoding.submissionshoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_NAME = "name"
        private const val EXTRA_IMAGE = "image"
        private const val EXTRA_PRICE = "price"
        private const val EXTRA_SIZE = "size"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = supportActionBar
        actionBar!!.title = "Detail Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val dataName = intent.getStringExtra(EXTRA_NAME)
        val dataPrice = intent.getStringExtra(EXTRA_PRICE)
        val dataSize = intent.getStringExtra(EXTRA_SIZE)
        val imageData = intent.getIntExtra(EXTRA_IMAGE, 0)

        shoes_name.text = dataName
        size_shoes.text = dataSize
        price_shoes.text = dataPrice

        Glide.with(this)
            .load(imageData)
            .into(shoes_image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}