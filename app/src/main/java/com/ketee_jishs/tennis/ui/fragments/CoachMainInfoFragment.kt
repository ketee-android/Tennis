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
import com.ketee_jishs.tennis.databinding.FragmentCoachMainInfoBinding
import com.ketee_jishs.tennis.ui.MainViewModel
import com.ketee_jishs.tennis.utils.AppState
import com.ketee_jishs.tennis.utils.toast

@Suppress("DEPRECATION")
class CoachMainInfoFragment : Fragment() {

    private lateinit var binding: FragmentCoachMainInfoBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoachMainInfoBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        getData()
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getData() {
        viewModel.getData().observe(
            this@CoachMainInfoFragment,
            Observer<AppState> { renderData(it) }
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewModel.setCoachMainInfo(
                    appState.tennisServerResponse.coachName,
                    appState.tennisServerResponse.coachLocation,
                    appState.tennisServerResponse.coachDescription
                )
            }
            is AppState.Error -> {
                toast(appState.error.message)
            }
        }
    }
}