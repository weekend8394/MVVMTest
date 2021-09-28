package com.cockroach.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.cockroach.mvvmdemo.R
import com.cockroach.mvvmdemo.databinding.ActivityMainBinding
import com.cockroach.viewModel.MainViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        GlobalScope.launch {
            viewModel.getData()
            Timber.e("000")
            viewModel.toastMessage.whatIfNotNull {
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        startActivity(Intent(this@MainActivity, MainActivity2::class.java))
        finish()
    }
}