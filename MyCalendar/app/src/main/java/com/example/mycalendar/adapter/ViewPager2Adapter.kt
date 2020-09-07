package com.rantea.animeowm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter : FragmentStateAdapter {

    val fragments: MutableList<Fragment>

    constructor(fragmentActivity: FragmentActivity, vararg fragments: Fragment)
            : super(fragmentActivity) {
        this.fragments = mutableListOf(*fragments)
    }

    constructor(fragment: Fragment, fragments: MutableList<Fragment>) : super(fragment) {
        this.fragments = fragments
    }

    constructor(fragment: Fragment, vararg fragments: Fragment)
            : super(fragment) {
        this.fragments = mutableListOf(*fragments)
    }


    constructor(fragmentActivity: FragmentActivity, fragments: MutableList<Fragment>)
            : super(fragmentActivity) {
        this.fragments = fragments
    }

    constructor(
        fragmentManager: FragmentManager, lifecycle: Lifecycle, fragments: MutableList<Fragment>
    ) : super(fragmentManager, lifecycle) {
        this.fragments = fragments
    }


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}