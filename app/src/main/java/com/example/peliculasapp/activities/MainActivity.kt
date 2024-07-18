package com.example.peliculasapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peliculasapp.R
import com.example.peliculasapp.adapter.adapterMovie
import com.example.peliculasapp.data.RetrofitService
import com.example.peliculasapp.data.Movie
import com.example.peliculasapp.databinding.ActivityMainBinding
import com.example.peliculasapp.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var adapter: adapterMovie
    lateinit var movieList: List<Movie>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        movieList = emptyList()

        adapter =
           adapterMovie(movieList) { position -> navigateToDetail(movieList[position]) }

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        searchByName("matrix")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        return true
    }

    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem != null) {
            var searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchByName(query!!)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })
        }
    }


    private fun navigateToDetail(movieList: Movie) {

        val intent = Intent(this, Detail::class.java)
        intent.putExtra("EXTRA_ID", movieList.imdbID)
        startActivity(intent)
    }


    private fun searchByName(query: String) {


        val service: RetrofitService = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {

            val response = service.searchByName(query)

            runOnUiThread {

                if (response.isSuccessful) {
                    Log.i("HTTP", "${response.body()?.search} :(")
                    Log.i("HTTP", "respuesta correcta :)")
                    movieList = response.body()?.search.orEmpty()
                    adapter.updateData(movieList)
                    Log.i("INFORMAMovie", "$movieList")

                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                    Toast.makeText(
                        this@MainActivity,
                        "Hubo un error inesperado, vuelva a intentarlo más tarde",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}