package com.android.setupschool.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.android.setupschool.R
import com.android.setupschool.databinding.FragmentHomeBinding
import com.android.setupschool.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalStateException

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding.toolbar)

        binding.viewPager.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when(pos){
                0 -> tab.setText(R.string.students)
                1 -> tab.setText(R.string.teachers)
                else -> throw IllegalStateException()
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                findNavController().navigate(R.id.action_add_student)
                true
            }
            R.id.add_teacher -> {
                findNavController().navigate(R.id.action_add_teacher)
                true
            }
            else -> false
        }
    }
}