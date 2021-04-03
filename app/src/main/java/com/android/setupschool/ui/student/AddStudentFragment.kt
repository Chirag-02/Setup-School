package com.android.setupschool.ui.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.setupschool.R
import com.android.setupschool.databinding.FragmentAddStudentBinding
import com.android.setupschool.ui.base.BaseFragment
import com.android.setupschool.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStudentFragment : BaseFragment<FragmentAddStudentBinding>(R.layout.fragment_add_student) {

    private val viewModel: AddStudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        binding.include.toolbar.apply {
            title = getString(R.string.add_student)
            setNavigationIcon(R.drawable.ic_globals_action_back)
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.done_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.done) {
                    viewModel.createStudent()
                }
                false
            }
        }

        viewModel.toastEvent.observe(viewLifecycleOwner, ::toast)

        viewModel.studentCreatedEvent.observe(viewLifecycleOwner){
            toast(R.string.student_created_successfully)
            findNavController().navigateUp()
        }
    }
}