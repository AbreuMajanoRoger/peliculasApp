package com.example.peliculasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.peliculasapp.data.Movie
import com.example.peliculasapp.data.RetrofitService
import com.example.peliculasapp.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detail : AppCompatActivity() {


    lateinit var binding: ActivityDetailBinding
    lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  imdbID= intent.getStringExtra("EXTRA_ID")
        getById(imdbID!!)
    }


    private fun loadData() {
         Picasso.get().load(movie.Poster).into(binding.imgMovieDetail)

        binding.txtTitleMovie.text = movie.Title
        binding.txtYearMovie.text = movie.Year
        binding.txtDuration.text  = movie.Runtime
        binding.txtdetaildirector.text = movie.Director
        binding.txtDuration.text = movie.Runtime
        binding.txtgender.text = movie.Genre
        binding.txtplot.text = movie.Plot

    //binding.txtSpeciesDetail.text = character.species

        //val  species = character.species
        //val gender = character.gender
        //val status = character.status


    }


    private fun getById(imdbID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getRetrofit().create(RetrofitService::class.java)
                val result = apiService.getById(imdbID)

                movie = result.body()!!

                runOnUiThread {
                    Log.i("RESPUESTADETAIL", "${movie}")
                    loadData()

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}