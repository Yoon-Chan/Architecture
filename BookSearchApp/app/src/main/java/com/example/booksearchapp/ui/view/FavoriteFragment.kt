package com.example.booksearchapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearchapp.R
import com.example.booksearchapp.databinding.FragmentFavoriteBinding
import com.example.booksearchapp.ui.adapter.BookSearchAdapter
import com.example.booksearchapp.ui.adapter.BookSearchPagingAdapter
import com.example.booksearchapp.ui.viewmodel.BookSearchViewModel
import com.example.booksearchapp.util.collectLatestStateFlow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() =  _binding!!
    private val viewModel : BookSearchViewModel by activityViewModels()
   //private lateinit var bookSearchAdapter : BookSearchAdapter
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupTouchHelper(view)
//        viewModel.favoriteBooks.observe(viewLifecycleOwner){
//            Log.d("DEBUG", "viewModel.favoriteABooks : $it")
//            bookSearchAdapter.submitList(it)
//        }

//        lifecycleScope.launch {
//            viewModel.favoriteBooks.collectLatest {
//                bookSearchAdapter.submitList(it)
//            }
//        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.favoriteBooks.collectLatest {
//                    bookSearchAdapter.submitList(it)
//                }
//            }
//        }

        //Extensions에서의 확장 함수 이용
        collectLatestStateFlow(viewModel.favoritePagingBooks){
            bookSearchAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView() {
        //bookSearchAdapter = BookSearchAdapter()
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvFavoriteBook.adapter = bookSearchAdapter
        binding.rvFavoriteBook.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        bookSearchAdapter.setOnItemClickListener {
            val action = FavoriteFragmentDirections.actionFragmentFavoriteToFragmentBook(it)
            findNavController().navigate(action)
        }
    }

    //왼쪽으로 슬라이드하면 데이터 삭제 기능 구현
    private fun setupTouchHelper(view : View){
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //val position = viewHolder.adapterPosition
//                val book = bookSearchAdapter.currentList[position]
//                viewModel.deleteBook(book)
//                Snackbar.make(view, "Book has deleted", Snackbar.LENGTH_SHORT).apply {
//                    setAction("Undo"){
//                        viewModel.saveBook(book)
//                    }
//                }.show()
                val position = viewHolder.bindingAdapterPosition
                val pagedBook = bookSearchAdapter.peek(position)
                pagedBook?.let { book ->
                    viewModel.deleteBook(book)
                    Snackbar.make(view, "Book has deleted", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo"){
                        viewModel.saveBook(book)
                    }
                }.show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavoriteBook)
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}