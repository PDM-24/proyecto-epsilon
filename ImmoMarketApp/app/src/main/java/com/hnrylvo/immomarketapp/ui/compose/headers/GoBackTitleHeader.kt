package com.hnrylvo.inmomarket.ui.compose.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.theme.BackgroundGreen
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.PrimaryGreen

@Composable
fun GoBackWithTitleHeader(
    title: Int,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        IconButton(onClick = { onBackClick() }) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(PrimaryGreen)
                    .size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.app_go_back),
                tint = BackgroundGreen
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            style = MyTypography.titleMedium
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun GoBackWithTitleHeaderPreview() {
    GoBackWithTitleHeader(
        title = R.string.app_name
    )
}
