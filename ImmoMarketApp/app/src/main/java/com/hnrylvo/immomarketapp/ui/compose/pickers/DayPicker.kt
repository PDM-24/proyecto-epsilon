package com.hnrylvo.inmomarket.ui.compose.pickers

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen
import com.hnrylvo.immomarketapp.ui.theme.White

@Composable
fun DayPicker(
    days: List<String>,
    selectedDay: String,
    onDaySelected: (String) -> Unit,
    disableDays: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        days.forEach {
            Box(
                modifier = Modifier
                    .width(35.dp)
                    .height(45.dp)
                    .clickable(
                        enabled = !disableDays.contains(it),
                        onClick = {
                            onDaySelected(it)
                        }
                    )
                    .background(
                        if (disableDays.contains(it)) LightGreen else Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(1.dp, LightGreen, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                val animatedSize by animateDpAsState(
                    targetValue = if (selectedDay == it) 60.dp else 0.dp,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    ), label = "DayPicker"
                )
                Box(
                    modifier = Modifier
                        .size(animatedSize)
                        .background(
                            if (selectedDay == it) LightGreen else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Text(
                    text = it,
                    color = if (selectedDay == it || disableDays.contains(it)) White else PrimaryGreen,
                    style = MyTypography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun DayPickerPreview() {
    DayPicker(
        days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        selectedDay = "Mon",
        onDaySelected = {},
        disableDays = listOf("Sat", "Sun")
    )
}