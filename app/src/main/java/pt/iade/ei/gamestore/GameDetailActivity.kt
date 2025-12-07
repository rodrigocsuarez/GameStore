package pt.iade.ei.gamestore

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import pt.iade.ei.gamestore.controller.GameDetailController
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.PurchasableItem
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme
import pt.iade.ei.gamestore.view.GameDetailScreen
import pt.iade.ei.gamestore.view.PurchaseBottomSheet

class GameDetailActivity : ComponentActivity() {

    private lateinit var controller: GameDetailController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game: Game? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("GAME_KEY", Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("GAME_KEY") as? Game
        }

        if (game == null) {
            Toast.makeText(this, "Erro: Detalhes do jogo não encontrados.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }


        controller = GameDetailController(game)

        setContent {
            GameStoreTheme {
                var showBottomSheet by remember { mutableStateOf(false) }
                var selectedItem by remember { mutableStateOf<PurchasableItem?>(null) }

                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val context = LocalContext.current

                val onPurchaseConfirmed: (PurchasableItem) -> Unit = { item ->

                    val message = controller.getPurchaseConfirmationMessage(item)
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }

                GameDetailScreen(
                    game = controller.getGame(),
                    onBackClick = { finish() },
                    onItemClick = { item ->
                        selectedItem = item
                        showBottomSheet = true
                    }
                )

                if (showBottomSheet && selectedItem != null) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState
                    ) {
                        PurchaseBottomSheet(
                            item = selectedItem!!,
                            onPurchaseConfirmed = onPurchaseConfirmed,
                            onDismiss = { showBottomSheet = false }
                        )
                    }
                }
            }
        }
    }
}