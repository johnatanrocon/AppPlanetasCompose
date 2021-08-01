package br.com.jrocon.appplanetascompose.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planetas(
    val nome: String,
    val descricao: String,
    val imagem: Int
) : Parcelable
