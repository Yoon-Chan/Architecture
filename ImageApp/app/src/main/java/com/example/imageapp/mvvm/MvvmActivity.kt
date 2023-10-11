package com.example.imageapp.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.imageapp.databinding.ActivityMvvmBinding
import com.example.imageapp.mvvm.repository.ImageRepositoryImpl

class MvvmActivity : AppCompatActivity() {

    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    private lateinit var binding : ActivityMvvmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.viewModle = viewModel

    }
//
//    fun loadImage(){
//        viewModel.loadRandomImage()
//    }
}