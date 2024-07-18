package com.example.peliculasapp.data

import com.google.gson.annotations.SerializedName

class MoviesResponse (
    @SerializedName("Search") val search:List<Movie>
){
}

class Movie (
    @SerializedName("imdbID") val imdbID:String,
    @SerializedName("Title") val Title:String,
    @SerializedName("Year") val Year:String,
    @SerializedName("Type") val Type:String,
    @SerializedName("Poster") val Poster:String,
    @SerializedName("Genre") val Genre:String?,
    @SerializedName("Country") val Country:String?,
    @SerializedName("Runtime") val Runtime:String?,
    @SerializedName("Director") val Director:String?,
    @SerializedName("Plot") val Plot:String?



    ) { }


data class Poster (
    @SerializedName("url") val url: String
)








