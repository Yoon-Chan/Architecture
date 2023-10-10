package com.example.imageapp.mvc

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.imageapp.databinding.ActivityMvcBinding
import com.example.imageapp.mvc.provider.ImageProvider

class MvcActivity : AppCompatActivity(), ImageProvider.Callback {


    private val imageProvider = ImageProvider(this)
    private val model = ImageCountModel()

    private lateinit var binding: ActivityMvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
    }

    fun loadImage() {
        imageProvider.getRandomImage()
    }

    override fun loadImage(url: String, color: String) {
        model.increase()
        with(binding) {
            imageView.setBackgroundColor(Color.parseColor(color))
            imageView.load(url)
            imageCountTextView.text = "불러온 이미지 수 : ${model.count}"
        }
    }
}