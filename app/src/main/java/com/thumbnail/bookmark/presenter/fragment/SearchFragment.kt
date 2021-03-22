package com.thumbnail.bookmark.presenter.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.thumbnail.bookmark.databinding.FragmentSearchBinding
import com.thumbnail.bookmark.model.ThumbnailItem
import com.thumbnail.bookmark.model.UIEvent
import com.thumbnail.bookmark.model.UIResponse
import com.thumbnail.bookmark.presenter.view.adapter.SearchAdapter
import com.thumbnail.bookmark.presenter.view.adapter.SearchAdapterHandler
import com.thumbnail.bookmark.presenter.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: SearchAdapter

    lateinit var binding: FragmentSearchBinding

    init {
        lifecycleScope.launchWhenStarted {
            val word = "카카오뱅크"
            binding.editQueryWord.setText(word)
            viewModel.setQuery(word)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = SearchAdapter(object : SearchAdapterHandler {
            override fun onClickItem(view: View, thumbnailItem: ThumbnailItem) {
                viewModel.saveThumbnail(thumbnailItem)
            }
        })

        binding.rvSearchResult.adapter = adapter
        viewModel.thumbnails.observe(viewLifecycleOwner, {
            adapter.submitList(it) {
                binding.nsvSearchResult.scrollTo(0, 0)
            }
        })

        viewModel.thumbnailEvent.observe(viewLifecycleOwner, {
            when (it) {
                UIResponse.Loading -> {
                    binding.layoutProgress.visibility = View.VISIBLE
                }
                is UIResponse.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.layoutProgress.visibility = View.GONE
                }
                UIResponse.Complete -> {
                    binding.layoutProgress.visibility = View.GONE
                }
            }
        })

        viewModel.uiEvent.observe(viewLifecycleOwner, {
            when (it) {
                is UIEvent.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    viewModel.setUiIdle()
                }
                is UIEvent.Idle -> {
                    Log.d("TAG", "Idle")
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.editQueryWord.imeOptions = EditorInfo.IME_ACTION_SEARCH
        binding.editQueryWord.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.setQuery(binding.editQueryWord.text.toString())
                    hideSoftInputFromWindow(requireContext(), v)
                    true
                }
                else -> false
            }
        }

        binding.btnQuery.setOnClickListener {
            hideSoftInputFromWindow(requireContext(), it)
            viewModel.setQuery(binding.editQueryWord.text.toString())
        }

        binding.ivBeforePage.setOnClickListener {
            viewModel.before()
        }

        binding.ivNextPage.setOnClickListener {
            viewModel.next()
        }
    }

    private fun hideSoftInputFromWindow(context: Context, view : View): Boolean {
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
