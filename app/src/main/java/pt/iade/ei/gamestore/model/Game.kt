package pt.iade.ei.gamestore.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Game(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val items: List<PurchasableItem> = emptyList()
) : Serializable