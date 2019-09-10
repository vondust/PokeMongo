package br.com.brunoaguiar.pokemongo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("number") val numero: String,
    @SerializedName("name") val nome: String,
    @SerializedName("imageURL") val urlImagem: String,
    var attack: Int,
    var defense: Int,
    var velocity: Int,
    var ps: Int
):Parcelable