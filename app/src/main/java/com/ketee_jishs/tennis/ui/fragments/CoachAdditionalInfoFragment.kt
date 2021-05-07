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
import com.ketee_jishs.tennis.databinding.FragmentCoachAdditionalInfoBinding
import com.ketee_jishs.tennis.ui.MainViewModel
import com.ketee_jishs.tennis.utils.AppState
import com.ketee_jishs.tennis.utils.toast

@Suppress("DEPRECATION")
class CoachAdditionalInfoFragment : Fragment() {

    private lateinit var binding: FragmentCoachAdditionalInfoBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoachAdditionalInfoBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        getData()
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getData() {
        viewModel.getData().observe(
            this@CoachAdditionalInfoFragment,
            Observer<AppState> { renderData(it) }
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewModel.setCoachAdditionalInfo(
                    appState.tennisServerResponse.coachAge.toString(),
                    appState.tennisServerResponse.coachGender,
                    appState.tennisServerResponse.coachHeight.toString(),
                    appState.tennisServerResponse.coachActiveArm,
                    appState.tennisServerResponse.amountOfFeedbacks.toString()
                )
            }
            is AppState.Error -> {
                toast(appState.error.message)
            }
        }
    }
}