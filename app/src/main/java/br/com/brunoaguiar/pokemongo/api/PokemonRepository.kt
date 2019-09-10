package br.com.brunoaguiar.pokemongo.api

import br.com.brunoaguiar.pokemongo.model.Pokemon

interface PokemonRepository {
    fun checkHealth(
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun updatePokemon(
        pokemon: Pokemon,
        onComplete:(Pokemon?) -> Unit,
        onError:(Throwable) -> Unit
    )
}