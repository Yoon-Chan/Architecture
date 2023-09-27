package com.example.booksearchapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.booksearchapp.R
import com.example.booksearchapp.databinding.FragmentSettingsBinding
import com.example.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.example.booksearchapp.util.Sort
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {
    private var _binding : FragmentSettingsBinding? = null
    private val binding get() =  _binding!!

    private val viewModel : BookSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savesSetting()
        loadSetting()
    }

    private fun  savesSetting(){
        binding.rgSort.setOnCheckedChangeListener { _, i ->
            val value = when(i) {
                R.id.rb_accuracy -> Sort.ACCURACY.value
                R.id.rb_latest -> Sort.LATEST.value
                else -> return@setOnCheckedChangeListener
            }
            viewModel.saveSortMode(value)
        }
    }

    private fun loadSetting(){
        lifecycleScope.launch {
            val buttonId = when(viewModel.getSortMode()){
                Sort.ACCURACY.value -> { R.id.rb_accuracy }
                Sort.LATEST.value -> { R.id.rb_latest}
                else -> return@launch
            }
            binding.rgSort.check(buttonId)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}