package com.hnrylvo.inmomarket.ui.compose.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen

@Composable
fun HorizontalCard(
    imageUrl : Any,
    productPrice : String,
    productType : String,
    size : String,
    habitations : String,
    bathrooms : String,
    municipality: String,
    department: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onClick() }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = stringResource(R.string.app_image_description),
            contentScale = ContentScale.Crop,

        )
        Column {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "$$productPrice",
                    style = MyTypography.bodyLarge,
                    color = SecondaryGreen
                )
                Spacer(modifier = Modifier.width(45.dp))
                Icon(painter = painterResource(id = R.drawable.ic_ruler), contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$size m2",
                    style = MyTypography.bodySmall,
                    color = SecondaryGreen
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(painter = painterResource(id = R.drawable.ic_bed), contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$habitations habs",
                    style = MyTypography.bodySmall,
                    color = SecondaryGreen
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(painter = painterResource(id = R.drawable.ic_bath), contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$bathrooms baños",
                    style = MyTypography.bodySmall,
                    color = SecondaryGreen
                )
            }
            Text(
                text = productType,
                style = MyTypography.bodySmall,
                color = SecondaryGreen
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "$municipality, $department",
                style = MyTypography.bodyMedium,
                color = SecondaryGreen
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun HorizontalCardPreview() {
    HorizontalCard(
        imageUrl = "https://www.bankrate.com/2022/07/20093642/what-is-house-poor.jpg?auto=webp&optimize=high&crop=16:9&width=912",
        productPrice = "100000",
        productType = "Casa",
        municipality = "San Francisco",
        department = "California",
        size = "100",
        habitations = "",
        bathrooms = "",
        onClick = {}
    )
}
