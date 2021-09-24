package com.cockroach.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.cockroach.mvvmdemo.R
import com.cockroach.mvvmdemo.databinding.ActivityMainBinding
import com.cockroach.viewModel.MainViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
        }
        Timber.e("123")
        viewModel.getData()
        Timber.e("789")
    }
}