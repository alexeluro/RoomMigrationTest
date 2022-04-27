package com.inspiredcoda.roommigrationtest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspiredcoda.roommigrationtest.data.Repository
import com.inspiredcoda.roommigrationtest.data.db.entity.StudentEntity
import kotlinx.coroutines.launch

class StudentsViewModel(
    private val repository: Repository
) : ViewModel() {

    private var _students: MutableLiveData<List<StudentEntity>> = MutableLiveData()
    val students: LiveData<List<StudentEntity>>
        get() = _students

    init {
        getAllStudents()
    }

    fun getAllStudents() {
        viewModelScope.launch {
            repository.selectAllStudents().observeForever(){
                _students.value = it
            }
        }
    }

    fun saveStudentInfo(studentName: String) {
        viewModelScope.launch {
            repository.insertStudent(studentName)
        }
    }

}