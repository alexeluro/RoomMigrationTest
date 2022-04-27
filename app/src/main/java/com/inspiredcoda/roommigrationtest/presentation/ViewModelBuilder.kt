package com.inspiredcoda.roommigrationtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inspiredcoda.roommigrationtest.data.Repository

class StudentViewModelBuilder(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentsViewModel::class.java)) {
            return StudentsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("")
        }
    }
}