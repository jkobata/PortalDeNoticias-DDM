package com.example.projetofinal.Interface

import com.example.projetofinal.Model.WebSite
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @get: GET("v2/sources?apiKey=d6166a2cac96400797fadb74b12a6ec9")
    val sources: Call<WebSite>
}