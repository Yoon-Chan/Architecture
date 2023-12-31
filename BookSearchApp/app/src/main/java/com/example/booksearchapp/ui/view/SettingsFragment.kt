package com.example.booksearchapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.booksearchapp.R
import com.example.booksearchapp.databinding.FragmentSettingsBinding
import com.example.booksearchapp.ui.viewmodel.SettingsViewModel
import com.example.booksearchapp.util.Sort
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private var _binding : FragmentSettingsBinding? = null
    private val binding get() =  _binding!!

    //private val viewModel : BookSearchViewModel by activityViewModels()
    private val viewModel by viewModels<SettingsViewModel>()

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
        showWorkStatus()
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

        binding.swCacheDelete.setOnCheckedChangeListener { _, b ->
            viewModel.saveCacheDeleteMode(b)
            if(b) viewModel.setWork() else viewModel.deleteWork()
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

        lifecycleScope.launch {
            val mode = viewModel.getCacheDeleteMode()
            binding.swCacheDelete.isChecked = mode
        }
    }

    private fun showWorkStatus(){
        viewModel.getWorkStatus().observe(viewLifecycleOwner) { workInfo ->
            Log.d("WorkManager", workInfo.toString())
            binding.tvWorkStatus.text = if(workInfo.isEmpty()){
                "No Works"
            }else {
                workInfo[0].state.toString()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}