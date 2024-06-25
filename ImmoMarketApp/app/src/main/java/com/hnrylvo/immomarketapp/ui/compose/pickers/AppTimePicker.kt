package com.hnrylvo.inmomarket.ui.compose.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.White
import com.hnrylvo.inmomarket.ui.compose.dialogs.TimePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTimePicker(
    showTimePicker: Boolean,
    setShowTimePicker: (Boolean) -> Unit,
    selectedHour: String,
    onHourChanged: (String) -> Unit,
    selectedMinute: String,
    onMinuteChanged: (String) -> Unit,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = 7,
        initialMinute = 0,
        is24Hour = true
    )
    Button(
        modifier = Modifier
            .width(180.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = { setShowTimePicker(true) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(35.dp)
                    .border(1.dp, LightGreen, RoundedCornerShape(5.dp))
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedHour,
                    style = MyTypography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = ":",
                style = MyTypography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(35.dp)
                    .border(1.dp, LightGreen, RoundedCornerShape(5.dp))
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedMinute,
                    style = MyTypography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = {
                setShowTimePicker(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onHourChanged(timePickerState.hour.toString().padStart(2, '0'))
                        onMinuteChanged(timePickerState.minute.toString().padStart(2, '0'))
                        setShowTimePicker(false)
                    }
                ) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        setShowTimePicker(false)
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TimePickerPreview() {
    AppTimePicker(
        showTimePicker = false,
        setShowTimePicker = {},
        selectedHour = "07",
        onHourChanged = {},
        selectedMinute = "00",
        onMinuteChanged = {}
    )
}
