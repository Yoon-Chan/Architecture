package com.example.booksearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksearchapp.databinding.ActivityMainBinding
import com.example.booksearchapp.ui.view.FavoriteFragment
import com.example.booksearchapp.ui.view.SearchFragment
import com.example.booksearchapp.ui.view.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()


        //앱이 처음으로 실행한 경우에만 searchFragment를 실행하도록 하는 기능
        if(savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
        }
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