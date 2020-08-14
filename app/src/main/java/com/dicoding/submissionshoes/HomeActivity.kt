package com.dicoding.submissionshoes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionshoes.adapter.ShoesAdapter
import com.dicoding.submissionshoes.model.Shoes
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var items: MutableList<Shoes> = mutableListOf()

    @SuppressLint("Recycle")
    private fun initData() {
//        get the data from string.xml
        val name = resources.getStringArray(R.array.shoes_name)
        val image = resources.obtainTypedArray(R.array.shoes_image)
        val price = resources.getStringArray(R.array.shoes_price)
        val size = resources.getStringArray(R.array.shoes_size)

//        looping the data
        items.clear()
        for (i in name.indices) {
            items.add(
                Shoes(
                    name[i],
                    image.getResourceId(i, 0),
                    price[i],
                    size[i]
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val actionbar = supportActionBar
        actionbar!!.title = "Home"

        rv_shoes.setHasFixedSize(true)
        initData()
//        implement the recyclerview in this layout
        rv_shoes.layoutManager = LinearLayoutManager(this)
        rv_shoes.adapter = ShoesAdapter(context = this, listData = items) {
            val intent = Intent(this@HomeActivity,DetailActivity::class.java)
                intent.putExtra("name", it.name )
                intent.putExtra("image", it.image)
                intent.putExtra("size", it.size)
                intent.putExtra("price", it.price)
            startActivity(intent)
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_people, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.profile -> {
                val profile = Intent(this@HomeActivity, ProfileMeActivity::class.java)
                startActivity(profile)
            }
        }
    }
}