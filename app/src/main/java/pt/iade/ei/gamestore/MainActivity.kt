package pt.iade.ei.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pt.iade.ei.gamestore.controller.GameController
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme
import pt.iade.ei.gamestore.view.GameListScreen

class MainActivity : ComponentActivity() {

    private val gameController = GameController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gamesList = gameController.getSampleGames()

        setContent {
            GameStoreTheme {
                GameListScreen(
                    games = gamesList,
                    onGameSelected = { game ->
                        navigateToDetail(game)
                    }
                )
            }
        }
    }

    private fun navigateToDetail(game: Game) {
        val intent = Intent(this, GameDetailActivity::class.java).apply {
            putExtra("GAME_KEY", game)
        }
        startActivity(intent)
    }
}
