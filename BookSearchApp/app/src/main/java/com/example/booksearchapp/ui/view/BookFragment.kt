package com.example.booksearchapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.booksearchapp.databinding.FragmentBookBinding
import com.example.booksearchapp.ui.viewmodel.BookViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<BookFragmentArgs>()
    //private val viewModel : BookSearchViewModel by activityViewModels()
    private val viewModel by viewModels<BookViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book
        with(binding.webView){
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            viewModel.saveBook(book)
            Snackbar.make(view, "book has saved", Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun onPause() {
        binding.webView.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.webView.onResume()
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}