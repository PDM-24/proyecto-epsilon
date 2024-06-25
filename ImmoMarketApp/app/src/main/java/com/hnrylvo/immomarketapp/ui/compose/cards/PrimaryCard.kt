package com.hnrylvo.inmomarket.ui.compose.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen
import com.hnrylvo.immomarketapp.ui.theme.White

@Composable
fun PrimaryCard(
    cardWidth: Dp = 234.dp,
    cardHeight: Dp = 230.dp,
    productType: String,
    productPrice: String,
    neighborhood: String,
    municipality: String,
    department: String,
    imageUrl: Any,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(width = cardWidth, height = cardHeight)
            .padding(8.dp)
            .clickable(
                onClick = { onClick() }
            ),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryGreen
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            model = imageUrl,
            contentDescription = stringResource(id = R.string.app_image_description),
            contentScale = ContentScale.Crop
        )
        Text(
            text = productType,
            modifier = Modifier.padding(top=8.dp, start=8.dp),
            style = MyTypography.bodySmall,
            color = White
        )
        Text(
            text = productPrice,
            modifier = Modifier.padding(top=8.dp, start=8.dp),
            style = MyTypography.bodyLarge,
            color = White
        )
        Text(
            text = neighborhood,
            modifier = Modifier.padding(top=8.dp, start=8.dp),
            style = MyTypography.titleSmall,
            color = White
        )
        Text(
            text =  "$municipality, $department",
            modifier = Modifier.padding(start=8.dp),
            style = MyTypography.bodySmall,
            color = White
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PrimaryCardPreview() {
    PrimaryCard(
        productType = "Type",
        productPrice = "Price",
        neighborhood = "Neighborhood",
        municipality = "Municipality",
        department = "Department",
        imageUrl = "https://www.bankrate.com/2022/07/20093642/what-is-house-poor.jpg?auto=webp&optimize=high&crop=16:9&width=912",
        onClick = {}
    )
}