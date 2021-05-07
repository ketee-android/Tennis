@file:Suppress("DEPRECATION")

package com.ketee_jishs.tennis.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketee_jishs.tennis.databinding.FragmentStudentsBinding
import com.ketee_jishs.tennis.ui.MainViewModel
import com.ketee_jishs.tennis.ui.adapters.StudentsAdapter
import com.ketee_jishs.tennis.utils.AppState
import com.ketee_jishs.tennis.utils.toast

@Suppress("DEPRECATION")
class StudentsFragment : Fragment() {

    private lateinit var binding: FragmentStudentsBinding
    private val studentsAdapter = StudentsAdapter(arrayListOf())
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.studentsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = studentsAdapter
        }

        getData()
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getData() {
        viewModel.getData().observe(
            this@StudentsFragment,
            Observer<AppState> { renderData(it) }
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewModel.setStudentsData(appState.tennisServerResponse.students.size.toString())
                studentsAdapter.replaceData(appState.tennisServerResponse.students)
            }
            is AppState.Error -> {
                toast(appState.error.message)
            }
        }
    }
}