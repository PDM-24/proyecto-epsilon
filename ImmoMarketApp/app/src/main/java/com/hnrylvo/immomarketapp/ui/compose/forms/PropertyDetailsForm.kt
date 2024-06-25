package com.hnrylvo.inmomarket.ui.compose.forms

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.IstokWebFamily
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.buttons.TertiaryButton
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.pickers.AppTimePicker
import com.hnrylvo.inmomarket.ui.compose.pickers.DayPicker
import com.hnrylvo.inmomarket.ui.theme.IstokWebFamily
import com.hnrylvo.inmomarket.ui.theme.LightGreen
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.PrimaryGreen
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.utils.Schedule

@Composable
fun PropertyDetailsForm(
    propertyPrice: String,
    setPropertyPrice: (String) -> Unit,
    days: List<String>,
    selectedDay: String,
    onDaySelected: (String) -> Unit,
    showStartTimePicker: Boolean,
    setShowStartTimePicker: (Boolean) -> Unit,
    selectedStartHour: String,
    onStartHourChanged: (String) -> Unit,
    selectedStartMinute: String,
    onStartMinuteChanged: (String) -> Unit,
    showFinishTimePicker: Boolean,
    setShowFinishTimePicker: (Boolean) -> Unit,
    selectedFinishHour: String,
    onFinishHourChanged: (String) -> Unit,
    selectedFinishMinute: String,
    onFinishMinuteChanged: (String) -> Unit,
    savedSchedules: List<Schedule>,
    isFormCompleted: (Boolean),
    addSchedule: (Schedule) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_details_title),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyPrice(
            propertyPrice = propertyPrice,
            setPropertyPrice = setPropertyPrice
        )
        Spacer(modifier = Modifier.padding(36.dp))
        Text(
            text = stringResource(id = R.string.sales_form_property_viewing_hours),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyViewingHours(
            days = days,
            selectedDay = selectedDay,
            onDaySelected = onDaySelected,
            disabledDays = savedSchedules.map { it.day }
        )
        Spacer(modifier = Modifier.padding(26.dp))
        StartTimePicker(
            showTimePicker = showStartTimePicker,
            setShowTimePicker = setShowStartTimePicker,
            selectedHour = selectedStartHour,
            onHourChanged = onStartHourChanged,
            selectedMinute = selectedStartMinute,
            onMinuteChanged = onStartMinuteChanged
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FinishTimePicker(
            showTimePicker = showFinishTimePicker,
            setShowTimePicker = setShowFinishTimePicker,
            selectedHour = selectedFinishHour,
            onHourChanged = onFinishHourChanged,
            selectedMinute = selectedFinishMinute,
            onMinuteChanged = onFinishMinuteChanged
        )
        Spacer(modifier = Modifier.padding(8.dp))
        if (isFormCompleted) {
            TertiaryButton(
                text = R.string.app_add,
                onClick = {
                    addSchedule(
                        Schedule(
                            day = selectedDay,
                            startHour = selectedStartHour,
                            startMinute = selectedStartMinute,
                            finishHour = selectedFinishHour,
                            finishMinute = selectedFinishMinute
                        )
                    )
                    onDaySelected("")
                    onStartHourChanged("00")
                    onStartMinuteChanged("00")
                    onFinishHourChanged("00")
                    onFinishMinuteChanged("00")
                }
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(16.dp))
        Schedules(
            schedule = savedSchedules
        )
        Spacer(modifier = Modifier.padding(36.dp))
    }
}

@Composable
fun Schedules(
    schedule: List<Schedule>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_schedule_selected),
            style = MyTypography.bodyLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.sales_form_property_schedule_days),
                style = MyTypography.bodyMedium,
            )
            Text(
                text = stringResource(id = R.string.sales_form_property_schedule_time_selected),
                style = MyTypography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.padding(3.dp))
        schedule.forEach { schedule ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = schedule.day,
                    style = MyTypography.bodySmall,
                    color = SecondaryGreen
                )
                Text(
                    text = "${schedule.startHour}:${schedule.startMinute} - ${schedule.finishHour}:${schedule.finishMinute}" ,
                    style = MyTypography.bodySmall,
                    color = SecondaryGreen
                )
            }
        }
    }
}

@Composable
fun FinishTimePicker(
    showTimePicker: Boolean,
    setShowTimePicker: (Boolean) -> Unit,
    selectedHour: String,
    onHourChanged: (String) -> Unit,
    selectedMinute: String,
    onMinuteChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_viewing_end_time),
            style = MyTypography.bodySmall,
            color = SecondaryGreen
        )
        AppTimePicker(
            showTimePicker = showTimePicker,
            setShowTimePicker = setShowTimePicker,
            selectedHour = selectedHour,
            onHourChanged = onHourChanged,
            selectedMinute = selectedMinute,
            onMinuteChanged = onMinuteChanged
        )
    }
}

@Composable
fun StartTimePicker(
    showTimePicker: Boolean,
    setShowTimePicker: (Boolean) -> Unit,
    selectedHour: String,
    onHourChanged: (String) -> Unit,
    selectedMinute: String,
    onMinuteChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_viewing_start_time),
            style = MyTypography.bodySmall,
            color = SecondaryGreen
        )
        AppTimePicker(
            showTimePicker = showTimePicker,
            setShowTimePicker = setShowTimePicker,
            selectedHour = selectedHour,
            onHourChanged = onHourChanged,
            selectedMinute = selectedMinute,
            onMinuteChanged = onMinuteChanged
        )
    }
}

@Composable
fun PropertyViewingHours(
    days: List<String>,
    selectedDay: String,
    onDaySelected: (String) -> Unit,
    disabledDays: List<String>
) {
    Column {
        Text(
            text = stringResource(id = R.string.app_select_the_days),
            style = MyTypography.bodySmall,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(4.dp))
        DayPicker(
            days = days,
            selectedDay = selectedDay,
            onDaySelected = onDaySelected,
            disableDays = disabledDays
        )
    }
}

@Composable
fun PropertyPrice(
    propertyPrice: String,
    setPropertyPrice: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_price),
            style = MyTypography.bodyMedium,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(4.dp))
        TextField(
            modifier = Modifier
                .height(50.dp)
                .width(250.dp)
                .border(1.dp, LightGreen, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontFamily = IstokWebFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = PrimaryGreen,
                letterSpacing = 3.sp
            ),
            prefix = {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 35.dp),
                    text = "$",
                    style = MyTypography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            maxLines = 1,
            value = propertyPrice,
            onValueChange = { setPropertyPrice(it) }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PropertyDetailsFormPreview() {
    PropertyDetailsForm(
        propertyPrice = "",
        setPropertyPrice = {},
        days = listOf(
            "Dom",
            "Lun",
            "Mar",
            "Mié",
            "Jue",
            "Vie",
            "Sáb"
        ),
        selectedDay = "Dom",
        onDaySelected = {},
        showStartTimePicker = false,
        setShowStartTimePicker = {},
        selectedStartHour = "01",
        onStartHourChanged = {},
        selectedStartMinute = "45",
        onStartMinuteChanged = {},
        selectedFinishHour = "05",
        onFinishHourChanged = {},
        selectedFinishMinute = "30",
        onFinishMinuteChanged = {},
        showFinishTimePicker = false,
        setShowFinishTimePicker = {},
        savedSchedules = listOf(
            Schedule(
                day = "Dom",
                startHour = "01",
                startMinute = "45",
                finishHour = "05",
                finishMinute = "30"
            )
        ),
        isFormCompleted = true,
        addSchedule = {}
    )
}