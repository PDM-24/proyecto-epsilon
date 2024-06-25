package com.hnrylvo.inmomarket.ui.compose.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.White

@Composable
fun TertiaryButton(
    text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.35f)
            .height(30.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGreen
        ),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = text),
            color = White,
            style = MyTypography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun TertiaryButtonPreview() {
    TertiaryButton(
        onClick = {},
        text = R.string.app_name
    )
}