package com.android.setupschool.ui.teacher

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.android.setupschool.R
import com.android.setupschool.data.db.entities.Teacher
import com.android.setupschool.databinding.LayoutRecyclerViewBinding
import com.android.setupschool.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherListFragment : BaseFragment<LayoutRecyclerViewBinding>(R.layout.layout_recycler_view),TeachersAdapter.RecycleViewItemClickListener {

    private val viewModel: TeacherListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TeachersAdapter(requireContext(),this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.teachers.observe(viewLifecycleOwner) {
            val isEmpty = it.isEmpty()
            binding.recyclerView.isVisible = !isEmpty
            binding.emptyListTv.isVisible = isEmpty
            if (!isEmpty) {
                adapter.submitList(it.toMutableList())
            } else {
                binding.emptyListTv.setText(R.string.no_teachers_added)
            }
        }
    }

    override fun onItemClick(data: Teacher) {
        viewModel.deleteTeacher(data.id)

    }
}