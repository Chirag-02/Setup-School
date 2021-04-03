package com.android.setupschool.ui.student

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.setupschool.R
import com.android.setupschool.data.db.entities.Student
import com.android.setupschool.databinding.ListItemStudentBinding

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.rollNo == newItem.rollNo
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}

class StudentsAdapter(
    val context: Context, var listener: RecycleViewItemClickListener
) :
    ListAdapter<Student, StudentsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: ListItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ListItemStudentBinding>(
            inflater, R.layout.list_item_student, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.student = item
        holder.binding.executePendingBindings()

        holder.binding.deleteStudent.setOnClickListener {
//            Toast.makeText(context, "CLICK ON DELTE BUTTON", Toast.LENGTH_SHORT).show()
            listener.onItemClick(item)
            notifyDataSetChanged()
        }

    }

    interface RecycleViewItemClickListener {
        fun onItemClick(data: Student)

    }
}