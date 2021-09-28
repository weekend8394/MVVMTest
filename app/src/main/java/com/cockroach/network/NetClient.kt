package com.cockroach.network
import com.cockroach.model.PokemonInfo
import com.cockroach.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class NetClient @Inject constructor(
    private val myService: MyService
){
    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonResponse> =
        myService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}