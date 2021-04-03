package com.android.setupschool.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.setupschool.ui.student.StudentListFragment
import com.android.setupschool.ui.teacher.TeacherListFragment

class HomeViewPagerAdapter(parent: Fragment) : FragmentStateAdapter(parent) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StudentListFragment()
            1 -> TeacherListFragment()
            else -> throw IllegalStateException()
        }
    }
}