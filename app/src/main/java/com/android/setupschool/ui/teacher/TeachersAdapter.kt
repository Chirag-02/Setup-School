package com.android.setupschool.ui.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.setupschool.R
import com.android.setupschool.data.db.entities.Student
import com.android.setupschool.data.db.entities.Teacher
import com.android.setupschool.databinding.ListItemTeacherBinding
import com.android.setupschool.ui.student.StudentsAdapter

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Teacher>() {
    override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem == newItem
    }
}

class TeachersAdapter(
    val context: Context, var listener:RecycleViewItemClickListener
) : ListAdapter<Teacher, TeachersAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: ListItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ListItemTeacherBinding>(
            inflater, R.layout.list_item_teacher, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.teacher = item
        holder.binding.executePendingBindings()

        holder.binding.deleteTeacher.setOnClickListener {
            listener.onItemClick(item)
            notifyDataSetChanged()
        }
    }
    interface RecycleViewItemClickListener {
        fun onItemClick(data: Teacher)

    }
}