package com.example.peliculasapp.data

import com.example.peliculasapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/")
    suspend fun searchByName(@Query("s") query:String, @Query("apikey") apikey: String = Constants.API_ACCESS_TOKEN): Response<MoviesResponse>

    @GET("/")
    suspend fun getById(@Query("i") identifier: String, @Query("apikey") apikey: String = Constants.API_ACCESS_TOKEN) : Response<Movie>

    //http://www.omdbapi.com/?apikey=7fee2446&s=matrix

}