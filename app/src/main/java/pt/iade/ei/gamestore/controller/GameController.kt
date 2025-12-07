package pt.iade.ei.gamestore.controller

import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.PurchasableItem

class GameController {


    fun getSampleGames(): List<Game> {
        return listOf(
            createRainbowSixSiege(),
            createFortnite()
        )
    }

    private fun createRainbowSixSiege(): Game {
        val items = listOf(
            PurchasableItem(
                name = "Bandit",
                description = "Operador de defesa com gadget de choque elétrico. Essencial para reforço.",
                price = 12.99,
                imageResId = R.drawable.bandit
            ),
            PurchasableItem(
                name = "Camuflagem BI",
                description = "Camuflagem lendária de alto contraste para todas as armas. Exclusiva da temporada.",
                price = 19.99,
                imageResId = R.drawable.bi
            ),
            PurchasableItem(
                name = "Doc",
                description = "Operador de defesa que pode curar e reviver aliados à distância. Suporte vital.",
                price = 12.99,
                imageResId = R.drawable.doc
            )
        )

        return Game(
            id = 1,
            name = "Rainbow Six Siege",
            description = "Tom Clancy's Rainbow Six Siege é um atirador tático focado em combate de curta distância e destruição ambiental.",
            imageResId = R.drawable.r6c,
            items = items
        )
    }


    private fun createFortnite(): Game {
        val items = listOf(
            PurchasableItem(
                name = "Corvo",
                description = "Skin lendária de trajes negros e penas escuras. Item cosmético raro.",
                price = 9.99,
                imageResId = R.drawable.corvo
            ),
            PurchasableItem(
                name = "Picareta Glacial",
                description = "Instrumento de coleta raro com efeitos de gelo e partículas azuis. Essencial para farm.",
                price = 3.99,
                imageResId = R.drawable.picareta
            ),
            PurchasableItem(
                name = "V-Bucks",
                description = "Moeda do jogo utilizada para comprar itens cosméticos e o Passe de Batalha.",
                price = 12.99,
                imageResId = R.drawable.vb
            )
        )

        return Game(
            id = 2,
            name = "Fortnite",
            description = "Um jogo de Battle Royale massivo onde 100 jogadores lutam para serem o último sobrevivente.",
            imageResId = R.drawable.fort,
            items = items
        )
    }


    fun getGameById(id: Int): Game? {
        return getSampleGames().find { it.id == id }
    }


    fun searchGamesByName(query: String): List<Game> {
        return getSampleGames().filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}