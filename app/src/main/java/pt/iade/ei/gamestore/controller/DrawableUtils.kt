package pt.iade.ei.gamestore.controller


import androidx.annotation.DrawableRes
import pt.iade.ei.gamestore.R


object DrawableUtils {


    @DrawableRes
    fun getDrawableIdByName(imageName: String): Int {
        return when (imageName.lowercase()) {

            "r6c" -> R.drawable.r6c
            "bandit" -> R.drawable.bandit
            "bi" -> R.drawable.bi
            "doc" -> R.drawable.doc


            "fort" -> R.drawable.fort
            "corvo" -> R.drawable.corvo
            "picareta" -> R.drawable.picareta
            "vb" -> R.drawable.vb


            else -> throw IllegalArgumentException("Imagem não encontrada: $imageName")
        }
    }


    fun imageExists(imageName: String): Boolean {
        return try {
            getDrawableIdByName(imageName)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }


    @DrawableRes
    fun getDrawableOrDefault(
        imageName: String,
        @DrawableRes defaultImage: Int = R.drawable.ic_launcher_foreground
    ): Int {
        return try {
            getDrawableIdByName(imageName)
        } catch (e: IllegalArgumentException) {
            defaultImage
        }
    }
}