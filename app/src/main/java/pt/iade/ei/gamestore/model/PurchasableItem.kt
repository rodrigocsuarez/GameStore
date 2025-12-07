package pt.iade.ei.gamestore.model

import java.io.Serializable

data class PurchasableItem(
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int
) : Serializable