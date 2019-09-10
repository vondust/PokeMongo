package br.com.brunoaguiar.pokemongo.di

import android.content.Context
import br.com.brunoaguiar.pokemongo.api.AuthInterceptor
import br.com.brunoaguiar.pokemongo.api.PokemonRepository
import br.com.brunoaguiar.pokemongo.api.PokemonRepositoryImpl
import br.com.brunoaguiar.pokemongo.api.PokemonService
import br.com.brunoaguiar.pokemongo.view.splash.SplashViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}
val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}
val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
    single { createOkhttpClientAuth(get()) }
    single { createPicassoAuth(get(), get()) }
}