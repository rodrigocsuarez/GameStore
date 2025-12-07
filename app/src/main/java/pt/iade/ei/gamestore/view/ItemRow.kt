package pt.iade.ei.gamestore.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.model.PurchasableItem
import java.text.NumberFormat
import java.util.Locale

private val priceFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "PT"))

@Composable
fun ItemRow(
    item: PurchasableItem,
    onItemClick: (PurchasableItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {

            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "Image for ${item.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = priceFormatter.format(item.price),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemRowPreview() {
    val sampleItemR6 = PurchasableItem(
        name = "Bandit",
        description = "Operador de defesa com gadget de choque elétrico. Essencial para reforço.",
        price = 12.99,
        imageResId = R.drawable.bandit
    )
    val sampleItemFortnite = PurchasableItem(
        name = "Corvo",
        description = "Skin lendária de trajes negros e penas escuras. Item cosmético raro e muito procurado.",
        price = 9.99,
        imageResId = R.drawable.corvo
    )

    Column(modifier = Modifier.padding(16.dp)) {
        ItemRow(item = sampleItemR6, onItemClick = {})
        Spacer(modifier = Modifier.height(8.dp))
        ItemRow(item = sampleItemFortnite, onItemClick = {})
    }
}