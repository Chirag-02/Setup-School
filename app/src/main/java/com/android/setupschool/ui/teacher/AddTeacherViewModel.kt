package com.android.setupschool.ui.teacher

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.setupschool.R
import com.android.setupschool.data.Repository
import com.android.setupschool.data.db.entities.Teacher
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTeacherViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val toastEvent = LiveEvent<Int>()
    val teacherCreatedEvent = LiveEvent<Unit>()

    val teacherId = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val subject = MutableLiveData<String>()

    fun createTeacher() {
        val teacherId = teacherId.value
        if (teacherId.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_teacher_id)
            return
        }

        val name = name.value
        if (name.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_name)
            return
        }

        val subject = subject.value
        if (subject.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_subject)
            return
        }

        viewModelScope.launch(IO) {
            try {
                repository.createTeacher(Teacher(name, teacherId, subject))
                teacherCreatedEvent.postValue(Unit)
            } catch (e: SQLiteConstraintException) {
                toastEvent.postValue(R.string.teacher_already_exists)
            }
        }

    }
}