package pt.iade.ei.gamestore.controller



import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.PurchasableItem
import java.text.NumberFormat
import java.util.Locale


class GameDetailController(private val game: Game) {

    private val priceFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "PT"))

    fun getGame(): Game = game

    fun getPurchasableItems(): List<PurchasableItem> = game.items

    fun getItemByName(name: String): PurchasableItem? {
        return game.items.find { it.name.equals(name, ignoreCase = true) }
    }

    fun formatPrice(price: Double): String {
        return priceFormatter.format(price)
    }

    fun getPurchaseConfirmationMessage(item: PurchasableItem): String {
        val formattedPrice = formatPrice(item.price)
        return "Acabou de comprar o item ${item.name} por $formattedPrice"
    }

    fun getTotalPrice(): Double {
        return game.items.sumOf { it.price }
    }

    fun getItemsSortedByPrice(): List<PurchasableItem> {
        return game.items.sortedBy { it.price }
    }

    fun getItemsByPriceRange(minPrice: Double, maxPrice: Double): List<PurchasableItem> {
        return game.items.filter { it.price in minPrice..maxPrice }
    }

    fun canPurchaseItem(item: PurchasableItem): Boolean {

        return true
    }
}