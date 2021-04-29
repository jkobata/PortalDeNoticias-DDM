package com.example.projetofinal

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetofinal.Adapter.ListSourceAdapter
import com.example.projetofinal.Common.Common
import com.example.projetofinal.Interface.NewsService
import com.example.projetofinal.Model.WebSite
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_portal_de_noticias.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortalDeNoticias : AppCompatActivity() {

lateinit var layoutManager: LinearLayoutManager
lateinit var mService: NewsService
lateinit var adapter: ListSourceAdapter
lateinit var dialog: AlertDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portal_de_noticias)

        Paper.init(this)

        mService = Common.newsService

        recycler_view_source_news.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler_view_source_news.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setContext(this).setCancelable(false).build()

        loadwebSiteService(false)

    }
    public fun loadSite(url: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }


    private fun loadwebSiteService(isRefres: Boolean){
        if(!isRefres){
            val cache = Paper.book().read<String>("cache")
            if(cache != null && !cache.isBlank() && cache != null){
                val webSite = Gson().fromJson<WebSite>(cache, WebSite::class.java)
                adapter = ListSourceAdapter(baseContext, webSite)
                adapter.notifyDataSetChanged()
                recycler_view_source_news.adapter = adapter
            }
            else{

                dialog.show()

                mService.sources.enqueue(object : Callback<WebSite> {
                    override fun onResponse(call: Call<WebSite>, response: Response<WebSite>) {
                        adapter = ListSourceAdapter(baseContext, response.body()!!)
                        adapter.notifyDataSetChanged()
                        recycler_view_source_news.adapter = adapter

                        Paper.book().write("cache", Gson().toJson(response.body()))


                    }

                    override fun onFailure(call: Call<WebSite>, t: Throwable) {
                        Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
        else{

        }

    }

}