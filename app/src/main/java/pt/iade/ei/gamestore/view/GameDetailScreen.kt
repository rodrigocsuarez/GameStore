package pt.iade.ei.gamestore.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.model.Game
import pt.iade.ei.gamestore.model.PurchasableItem
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBackClick: () -> Unit,
    onItemClick: (PurchasableItem) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = game.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Adicionar aos Favoritos"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Image(
                            painter = painterResource(id = game.imageResId),
                            contentDescription = game.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )

                        Spacer(modifier = Modifier.width(16.dp))


                        Text(
                            text = game.description,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                    Text(
                        text = "Purchasable Items",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }


                items(game.items) { item ->
                    ItemRow(
                        item = item,
                        onItemClick = onItemClick
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_5",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GameDetailScreenPreview() {
    GameStoreTheme {
        val sampleGame = Game(
            id = 1,
            name = "Rainbow Six Siege",
            description = "Um jogo tático de tiro em primeira pessoa focado em ambientes destrutíveis e operações de contra-terrorismo.",
            imageResId = R.drawable.r6c,
            items = listOf(
                PurchasableItem("Camuflagem Black Ice", "Skin de arma rara e cobiçada.", 14.99, R.drawable.bi),
                PurchasableItem("Pacote de Moedas", "Contém 1200 R6 Credits para gastar.", 9.99, R.drawable.bandit),
                PurchasableItem("Passe de Batalha", "Desbloqueia recompensas sazonais.", 19.99, R.drawable.fort),
            )
        )
        GameDetailScreen(
            game = sampleGame,
            onBackClick = {},
            onItemClick = {}
        )
    }
}