package com.android.setupschool.ui.student

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.setupschool.R
import com.android.setupschool.data.Repository
import com.android.setupschool.data.db.entities.Student
import com.android.setupschool.databinding.LayoutRecyclerViewBinding
import com.android.setupschool.ui.base.BaseFragment
import com.android.setupschool.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentListFragment : BaseFragment<LayoutRecyclerViewBinding>(R.layout.layout_recycler_view),
    StudentsAdapter.RecycleViewItemClickListener {

    private val viewModel: StudentListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StudentsAdapter(requireContext(), this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) {
            val isEmpty = it.isEmpty()
            binding.recyclerView.isVisible = !isEmpty
            binding.emptyListTv.isVisible = isEmpty
            if (!isEmpty) {
                adapter.submitList(it.toMutableList())
            } else {
                binding.emptyListTv.setText(R.string.no_students_added)
            }
        }
    }

    override fun onItemClick(data: Student) {
        viewModel.deleteStudent(data.rollNo)
    }
}