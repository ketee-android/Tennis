@file:Suppress("DEPRECATION")

package com.ketee_jishs.tennis.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.drawee.backends.pipeline.Fresco
import com.ketee_jishs.tennis.databinding.ActivityMainBinding
import com.ketee_jishs.tennis.utils.AppState
import com.ketee_jishs.tennis.utils.toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        onClick()
        getData()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getData() {
        viewModel.getData().observe(
            this@MainActivity,
            Observer<AppState> { renderData(it) }
        )
    }

    private fun onClick() {
        clicked(binding.backButton, "Back")
        clicked(binding.uploadButton, "Upload")
        clicked(binding.messageButton, "Message")
        clicked(binding.bookingButton, "Booking")
        clicked(binding.invitePersonButton, "Invite")
    }

    private fun clicked(view: View, toastText: String) {
        view.setOnClickListener {
            toast(applicationContext, toastText)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewModel.setData(Uri.parse(appState.tennisServerResponse.coachPhoto))
            }
            is AppState.Error -> {
                toast(applicationContext, appState.error.message)
            }
        }
    }
}