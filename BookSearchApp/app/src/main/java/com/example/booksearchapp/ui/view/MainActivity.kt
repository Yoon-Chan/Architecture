package com.example.booksearchapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.booksearchapp.R
import com.example.booksearchapp.data.repository.BookSearchRepository
import com.example.booksearchapp.data.repository.BookSearchRepositoryImpl
import com.example.booksearchapp.databinding.ActivityMainBinding
import com.example.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.example.booksearchapp.ui.viewmodel.BookSearchViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookSearchViewModel: BookSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()


        //앱이 처음으로 실행한 경우에만 searchFragment를 실행하도록 하는 기능
        if(savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
        }
        val bookSearchRepository = BookSearchRepositoryImpl()
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SearchFragment())
                        .commit()
                    true
                }

                R.id.fragment_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }

                R.id.fragment_setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SettingsFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}