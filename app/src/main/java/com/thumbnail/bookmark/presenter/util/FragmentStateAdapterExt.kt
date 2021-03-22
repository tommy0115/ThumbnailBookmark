package com.thumbnail.bookmark.presenter.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thumbnail.bookmark.R
import com.thumbnail.bookmark.presenter.fragment.BookmarkFragment
import com.thumbnail.bookmark.presenter.fragment.SearchFragment
import java.security.InvalidKeyException

class FragmentStateAdapterExt(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        const val ITEM_COUNT = 2
        const val IMAGE_SEARCH_FRAGMENT_POSITION = 0
        const val IMAGE_STORE_FRAGMENT_POSITION = 1

        fun getTabTitle(context : Context, index : Int) : String{
            return when(index){
                IMAGE_SEARCH_FRAGMENT_POSITION -> { context.resources.getString(R.string.tab_title_search)}
                IMAGE_STORE_FRAGMENT_POSITION ->  { context.resources.getString(R.string.tab_title_my_bookmark) }
                else -> throw Exception()
            }
        }
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            IMAGE_SEARCH_FRAGMENT_POSITION -> SearchFragment()
            IMAGE_STORE_FRAGMENT_POSITION -> BookmarkFragment()
            else -> {
                throw InvalidKeyException()
            }
        }
    }
}