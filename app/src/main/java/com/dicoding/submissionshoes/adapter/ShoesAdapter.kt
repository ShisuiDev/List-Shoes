package com.dicoding.submissionshoes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionshoes.R
import com.dicoding.submissionshoes.model.Shoes

class ShoesAdapter(
    private var listData: List<Shoes>,
    private val context: Context,
    private var listener: (Shoes) -> Unit
) : RecyclerView.Adapter<ShoesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvPrice: TextView = view.findViewById(R.id.tv_price)
        private val tvSize: TextView = view.findViewById(R.id.tv_size)

        private val tvImage: ImageView = view.findViewById(R.id.image_shoes)

        fun bindItem(data: Shoes, listener: (Shoes) -> Unit, context: Context) {
            tvName.text = data.name
            tvPrice.text = data.price
            tvSize.text = data.size

            Glide.with(context)
                .load(data.image)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.row_shoes, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ShoesAdapter.ViewHolder, position: Int) {
        holder.bindItem(listData[position], listener, context)
    }
}