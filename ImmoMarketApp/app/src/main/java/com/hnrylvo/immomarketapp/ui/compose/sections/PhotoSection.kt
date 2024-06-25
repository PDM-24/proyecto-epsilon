package com.hnrylvo.inmomarket.ui.compose.sections

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen

@Composable
fun PhotoSection(
    photoList: List<Uri>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(photoList) {photo ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(220.dp)
                    .border(1.dp, LightGreen, shape = RoundedCornerShape(10.dp)),
                model = photo,
                contentDescription = stringResource(id = R.string.app_image_description)
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PhotoSectionPreview() {
    PhotoSection(
        photoList = listOf(
        )
    )
}