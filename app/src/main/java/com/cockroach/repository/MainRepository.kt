package com.cockroach.repository

import androidx.annotation.WorkerThread
import com.cockroach.mapper.ErrorResponseMapper
import com.cockroach.model.Pokemon
import com.cockroach.network.NetClient
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val client: NetClient,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val pokemonArr = ArrayList<Pokemon>()
        val response = client.fetchPokemonList(page = page)
        response.suspendOnSuccess {
            data.results.forEach { pokemon ->
                pokemonArr.add(pokemon)
                pokemon.page = page
            }
            emit(pokemonArr)
        }
            .onError {
                map(ErrorResponseMapper) {
                    onError("[Code: $code]: $message")
                }
            }
            .onException { onError(message) }
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.flowOn(ioDispatcher)
}
