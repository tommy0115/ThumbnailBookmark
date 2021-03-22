package com.thumbnail.bookmark.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thumbnail.bookmark.databinding.FragmentBookmarkBinding
import com.thumbnail.bookmark.model.ThumbnailItem
import com.thumbnail.bookmark.presenter.view.adapter.SearchAdapter
import com.thumbnail.bookmark.presenter.view.adapter.SearchAdapterHandler
import com.thumbnail.bookmark.presenter.viewmodel.BookmarkViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BookmarkFragment: Fragment() {

    private val viewModel: BookmarkViewModel by viewModel()
    private lateinit var adapter : SearchAdapter
    lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = SearchAdapter(object : SearchAdapterHandler {
            override fun onClickItem(view: View, thumbnailItem: ThumbnailItem) {
                viewModel.deleteBookmark(thumbnailItem)
            }
        })

        binding.rvBookmark.adapter = adapter
        viewModel.getBookmarkLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}