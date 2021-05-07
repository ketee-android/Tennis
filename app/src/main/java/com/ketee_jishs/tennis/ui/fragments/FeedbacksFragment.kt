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
import com.ketee_jishs.tennis.databinding.FragmentFeedbacksBinding
import com.ketee_jishs.tennis.retrofit.Feedbacks
import com.ketee_jishs.tennis.ui.MainViewModel
import com.ketee_jishs.tennis.ui.adapters.FeedbackAdapter
import com.ketee_jishs.tennis.utils.AppState
import com.ketee_jishs.tennis.utils.toast

class FeedbacksFragment : Fragment() {

    private lateinit var binding: FragmentFeedbacksBinding
    private val feedbacksAdapter = FeedbackAdapter(arrayListOf())
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedbacksBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.feedbackRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = feedbacksAdapter
        }

        getData()
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getData() {
        viewModel.getData().observe(
            this@FeedbacksFragment,
            Observer<AppState> { renderData(it) }
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewModel.setFeedbacksData(
                    appState.tennisServerResponse.feedbacks.size.toString()
                )
                val feedbacksArray = appState.tennisServerResponse.feedbacks
                if (feedbacksArray.size > 2) {
                    val shortArray: ArrayList<Feedbacks> = arrayListOf(
                        feedbacksArray[0],
                        feedbacksArray[1],
                        feedbacksArray[2]
                    )
                    feedbacksAdapter.replaceData(shortArray)
                    binding.moreFeedback.setOnClickListener {
                        feedbacksAdapter.replaceData(feedbacksArray)
                        binding.moreFeedback.visibility = View.GONE
                        binding.hideFeedback.visibility = View.VISIBLE
                    }
                    binding.hideFeedback.setOnClickListener {
                        feedbacksAdapter.replaceData(shortArray)
                        binding.moreFeedback.visibility = View.VISIBLE
                        binding.hideFeedback.visibility = View.GONE
                    }
                } else {
                    binding.moreFeedback.setOnClickListener {
                        feedbacksAdapter.replaceData(feedbacksArray)
                        binding.moreFeedback.visibility = View.GONE
                    }
                }
            }
            is AppState.Error -> {
                toast(appState.error.message)
            }
        }
    }
}