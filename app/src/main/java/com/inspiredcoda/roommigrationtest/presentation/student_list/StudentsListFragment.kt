package com.inspiredcoda.roommigrationtest.presentation.student_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inspiredcoda.roommigrationtest.R
import com.inspiredcoda.roommigrationtest.data.Repository
import com.inspiredcoda.roommigrationtest.data.db.MainDb
import com.inspiredcoda.roommigrationtest.databinding.FragmentStudentsListBinding
import com.inspiredcoda.roommigrationtest.presentation.StudentViewModelBuilder
import com.inspiredcoda.roommigrationtest.presentation.StudentsViewModel
import timber.log.Timber

class StudentsListFragment : Fragment() {

    private var _binding: FragmentStudentsListBinding? = null
    private val binding: FragmentStudentsListBinding
        get() = _binding!!

    private val navController: NavController by lazy { findNavController() }

    private val studentViewModel: StudentsViewModel by lazy {
        val studentDao =
            MainDb.getDatabase(applicationContext = requireContext().applicationContext)
                .studentDao()
        val repository = Repository(studentDao)
        StudentViewModelBuilder(repository).create(StudentsViewModel::class.java)

    }

    private val mAdapter: StudentListAdapter = StudentListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addStudentBtn.setOnClickListener {
            navController.navigate(R.id.action_studentsListFragment_to_addStudentDialog)
        }
        setupRecyclerView()
        observers()
    }

    private fun setupRecyclerView() {
        binding.studentListRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun observers() {
        studentViewModel.students.observe(this) { students ->
            Timber.d("students size: ${students?.size}")
            if (students != null && students.isNotEmpty()) {
                binding.noStudentText.visibility = View.GONE
                mAdapter.submitStudents(students)
            } else {
                binding.noStudentText.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}