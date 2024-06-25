package com.hnrylvo.inmomarket.ui.compose.headers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen

@Composable
fun TitleHeader(
    modifier: Modifier = Modifier,
    headerText: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = headerText),
            style = MyTypography.titleLarge,
            color = PrimaryGreen
        )
    }
}

@Composable
@Preview
fun LoginHeaderPreview() {
    TitleHeader(headerText = R.string.login_header)
}