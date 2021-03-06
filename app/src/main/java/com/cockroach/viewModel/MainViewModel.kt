package com.cockroach.viewModel

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import com.cockroach.repository.MainRepository
import com.google.gson.Gson
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel(), CoroutineScope by MainScope() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    suspend fun getData() {
        Timber.e("456")
        mainRepository.fetchPokemonList(
            page = 0,
            onStart = {
                isLoading = true
            },
            onComplete = {
                isLoading = false
            },
            onError = {
                toastMessage = it
            }
        ).collect {
            Timber.e("789")
            Timber.w(Gson().toJson(it))
        }
    }

    init {
        Timber.d("init MainViewModel")
    }
}