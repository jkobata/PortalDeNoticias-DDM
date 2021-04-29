package com.example.projetofinal.Common

import com.example.projetofinal.Interface.NewsService
import com.example.projetofinal.Remote.RetrofitClient

object Common {
    val BASE_URL = "https://newsapi.org/"

    val newsService: NewsService
    get() = RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)
}