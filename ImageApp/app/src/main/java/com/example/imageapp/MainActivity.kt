package com.example.imageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageapp.databinding.ActivityMainBinding
import com.example.imageapp.mvc.MvcActivity
import com.example.imageapp.mvp.MvpActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
    }


    fun openMVC() {
        val intent = Intent(this, MvcActivity::class.java)
        startActivity(intent)
    }

    fun openMVP(){
        val intent = Intent(this, MvpActivity::class.java)
        startActivity(intent)
    }

    fun openMVVM(){

    }

    fun openMVI(){

    }
}