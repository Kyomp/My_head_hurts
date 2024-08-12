package com.example.myapplication

import PokemonAdapter
import PokemonSource
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var pokemonAdapter: PokemonAdapter
    private var pokemonSource = PokemonSource()
    private var cur_page = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        pokemonAdapter = PokemonAdapter(mutableListOf())
        var previousQuery:String = ""
        var searchBtn: Button = findViewById<Button>(R.id.btnSearch)
        searchBtn.setOnClickListener{
            var searchQuery: String = findViewById<EditText>(R.id.etSearchBar)
                .text
                .toString()
            if(searchQuery.isNotEmpty()){
                cur_page = 0
                previousQuery = searchQuery
                results = pokemonSource.search("name:"+searchQuery, cur_page)
                pokemonAdapter.deletePokemons()
                for(result in results){
                    pokemonAdapter.addPokemon(result)
                }
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemonList)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    cur_page++
                    previousQuery = previousQuery
                    results = pokemonSource.search("name:"+previousQuery, cur_page)
                    pokemonAdapter.deletePokemons()
                    for(result in results){
                        pokemonAdapter.addPokemon(result)
                    }
                }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}