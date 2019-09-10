package br.com.brunoaguiar.pokemongo.view.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.brunoaguiar.pokemongo.api.PokemonRepository

class SplashViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {
    val messageError: MutableLiveData<String> = MutableLiveData()
    fun checkHealth() {
        pokemonRepository.checkHealth(
            onComplete = {
                messageError.value = ""
            },
            onError = {
                messageError.value = it?.message
            }
        )
    }
}