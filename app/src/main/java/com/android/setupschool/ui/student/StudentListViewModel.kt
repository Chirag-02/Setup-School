package com.android.setupschool.ui.student

import android.database.sqlite.SQLiteConstraintException
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.setupschool.R
import com.android.setupschool.data.Repository
import com.android.setupschool.data.db.entities.Student
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val studentCreatedEvent = LiveEvent<Unit>()
    val toastEvent = LiveEvent<Int>()


    val students = repository.students


    fun deleteStudent(studentID:Int) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteStudent(studentID)
                studentCreatedEvent.postValue(Unit)
            } catch (e: SQLiteConstraintException) {
                toastEvent.postValue(R.string.student_delete)
            }
        }

    }

}