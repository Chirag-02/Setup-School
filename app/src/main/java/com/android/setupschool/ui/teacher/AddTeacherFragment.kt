package com.android.setupschool.ui.teacher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.setupschool.R
import com.android.setupschool.databinding.FragmentAddTeacherBinding
import com.android.setupschool.ui.base.BaseFragment
import com.android.setupschool.ui.student.AddStudentViewModel
import com.android.setupschool.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTeacherFragment : BaseFragment<FragmentAddTeacherBinding>(R.layout.fragment_add_teacher) {

    private val viewModel: AddTeacherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.include.toolbar.apply {
            title = getString(R.string.add_teacher)
            setNavigationIcon(R.drawable.ic_globals_action_back)
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.done_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.done) {
                    viewModel.createTeacher()
                }
                false
            }
        }

        viewModel.toastEvent.observe(viewLifecycleOwner, ::toast)

        viewModel.teacherCreatedEvent.observe(viewLifecycleOwner){
            toast(R.string.teacher_created_successfully)
            findNavController().navigateUp()
        }
    }
}