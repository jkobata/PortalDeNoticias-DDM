package com.example.projetofinal.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.Interface.ItemClickListener
import com.example.projetofinal.Model.WebSite
import com.example.projetofinal.PortalDeNoticias
import com.example.projetofinal.R
import kotlinx.android.synthetic.main.source_news_layout.view.*


class ListSourceAdapter(val context: Context, val webSite: WebSite): RecyclerView.Adapter<ListSourceAdapter.ListSourceViewHolder>() {

    class ListSourceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var itemClickListener: ItemClickListener
        var source_title = itemView.source_news_name
        var source_category = itemView.source_news_category
        var source_description = itemView.source_news_description

        init{
            itemView.setOnClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener){
            this.itemClickListener = itemClickListener

        }


        override fun onClick(v: View?) {
            itemClickListener.onClick(v!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.source_news_layout, parent, false)
        return ListSourceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return webSite.sources!!.size
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {

        holder. source_title.text = webSite.sources!![position].name
        holder. source_category.text = webSite.sources!![position].category
        holder. source_description.text = webSite.sources!![position].description

        holder.setItemClickListener(object : ItemClickListener {

            override fun onClick(view: View, position: Int)
            {
                Log.d("deb", "direcionado para: ${webSite.sources!![position].url.toString()}")
            }

        })
    }


}