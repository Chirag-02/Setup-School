package com.android.setupschool.ui.student

import android.database.sqlite.SQLiteConstraintException
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.setupschool.R
import com.android.setupschool.data.Repository
import com.android.setupschool.data.db.entities.Student
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val toastEvent = LiveEvent<Int>()
    val studentCreatedEvent = LiveEvent<Unit>()

    val rollNumber = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val className = MutableLiveData<String>()
    val section = MutableLiveData<String>()

    fun createStudent() {
        val rollNumber = rollNumber.value
        if (rollNumber.isNullOrEmpty() || !rollNumber.isDigitsOnly()) {
            toastEvent.postValue(R.string.invalid_roll_number)
            return
        }

        val name = name.value
        if (name.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_name)
            return
        }

        val className = className.value
        if (className.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_class)
            return
        }

        val section = section.value
        if (section.isNullOrEmpty()) {
            toastEvent.postValue(R.string.invalid_section)
            return
        }

        viewModelScope.launch(IO) {
            try {
                repository.createStudent(Student(name, rollNumber.toInt(), className, section))
                studentCreatedEvent.postValue(Unit)
            } catch (e: SQLiteConstraintException) {
                toastEvent.postValue(R.string.student_already_exists)
            }
        }

    }
}