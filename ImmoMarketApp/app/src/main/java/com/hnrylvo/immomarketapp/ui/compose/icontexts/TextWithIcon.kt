package com.hnrylvo.inmomarket.ui.compose.icontexts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen

@Composable
fun TextWithIcon(
    text: String,
    icon: Int,
    iconSize: Dp = 35.dp,
    padding: Dp = 16.dp,
    style: TextStyle = MyTypography.bodyLarge,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = { onClick() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end =16.dp)
                .size(iconSize),
            painter = painterResource(id = icon),
            contentDescription = text
        )
        Text(
            modifier = Modifier
                .padding(start = padding),
            text = text,
            style = style,
            color = SecondaryGreen
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun TextWithIconPreview() {
    TextWithIcon(
        text = stringResource(id = R.string.app_name),
        icon = R.drawable.ic_request
    )
}
