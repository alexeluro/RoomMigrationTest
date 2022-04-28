package com.inspiredcoda.roommigrationtest.presentation.add_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.inspiredcoda.roommigrationtest.data.Repository
import com.inspiredcoda.roommigrationtest.data.db.MainDb
import com.inspiredcoda.roommigrationtest.databinding.AddStudentDialogBinding
import com.inspiredcoda.roommigrationtest.presentation.StudentViewModelBuilder
import com.inspiredcoda.roommigrationtest.presentation.StudentsViewModel

class AddStudentDialog : BottomSheetDialogFragment() {

    private var _binding: AddStudentDialogBinding? = null
    private val binding: AddStudentDialogBinding
        get() = _binding!!

    private val studentViewModel: StudentsViewModel by lazy {
        val studentDao =
            MainDb.getDatabase(applicationContext = requireContext().applicationContext)
                .studentDao()
        val repository = Repository(studentDao)
        StudentViewModelBuilder(repository).create(StudentsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddStudentDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener {
            if (validateInput()) {
                saveStudent(binding.studentNameIpt.text.toString())
            }
        }

    }

    private fun validateInput(): Boolean {
        if (binding.studentNameIpt.text.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Input the New Student's Name", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun saveStudent(name: String) {
        studentViewModel.saveStudentInfo(name)
        studentViewModel.getAllStudents()
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}