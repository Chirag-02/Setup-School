package com.android.setupschool.ui.teacher

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.setupschool.R
import com.android.setupschool.data.Repository
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val toastEvent = LiveEvent<Int>()
    val teacherCreatedEvent = LiveEvent<Unit>()

    val teachers = repository.teachers

    fun deleteTeacher(teacherId:String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteTeacher(teacherId)
                teacherCreatedEvent.postValue(Unit)
            } catch (e: SQLiteConstraintException) {
                toastEvent.postValue(R.string.teacher_delete)
            }
        }

    }

}

