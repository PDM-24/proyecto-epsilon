package com.hnrylvo.inmomarket.ui.compose.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.BackgroundGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen

@Composable
fun SchedulePropertyViewingButton(
    propertyPrice: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, PrimaryGreen, RoundedCornerShape(15.dp))
            .clickable(
                onClick = { onClick() }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(60.dp)
                .background(PrimaryGreen),
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sales_form_property_price),
                    style = MyTypography.bodySmall,
                    color = BackgroundGreen
                )
                Text(
                    text = "$ ${propertyPrice}",
                    style = MyTypography.labelLarge,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(60.dp)
                .background(BackgroundGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Agendar visita",
                style = MyTypography.bodyLarge,
                color = PrimaryGreen,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun SchedulePropertyViewingButtonPreview() {
    SchedulePropertyViewingButton(
        propertyPrice = "100000",
        onClick = { /*TODO*/ }
    )
}