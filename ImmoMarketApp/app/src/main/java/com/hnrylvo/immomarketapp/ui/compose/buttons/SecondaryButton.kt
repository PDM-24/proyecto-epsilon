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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.BackgroundGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen

@Composable
fun SecondaryButton(
    text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    maxWidth: Float = 0.5f,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(maxWidth)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryGreen
        ),
        shape = RoundedCornerShape(15.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = text),
            style = MyTypography.titleSmall,
            color = BackgroundGreen
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun SecondaryButtonPreview() {
    SecondaryButton(
        text = R.string.sales_form_property_save_location,
        onClick = {}
    )
}
