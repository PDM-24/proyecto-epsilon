package com.hnrylvo.inmomarket.ux.sales_form

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.inmomarket.ui.compose.buttons.SecondaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.forms.PropertyDetailsForm
import com.hnrylvo.inmomarket.ui.compose.forms.PropertyLocationForm
import com.hnrylvo.inmomarket.ui.compose.forms.PropertyPhotosForm
import com.hnrylvo.inmomarket.ui.compose.forms.PropertySpecsForm
import com.hnrylvo.inmomarket.ui.compose.headers.GoBackWithTitleHeader

@Composable
fun SalesFormScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = SalesFormViewModel(navController = navController, context = context)
    AppScaffold(
        topBar = { TopBar() }
    ) {
        SalesForm(viewModel, context)
    }
}

@Composable
fun TopBar() {
    GoBackWithTitleHeader(title = R.string.sales_form_top_header)
}

@Composable
fun SalesForm(
    viewModel: SalesFormViewModel,
    context: Context
) {
    val propertyType by viewModel.propertyType.collectAsState()
    val neighborhood by viewModel.neighborhood.collectAsState()
    val municipality by viewModel.municipality.collectAsState()
    val department by viewModel.department.collectAsState()
    val propertyAddress by viewModel.propertyAddress.collectAsState()
    val savedPosition by viewModel.savedPosition.collectAsState()
    val isPositionSaved by viewModel.isPositionSaved.collectAsState()
    val propertyDescription by viewModel.propertyDescription.collectAsState()
    val propertySize by viewModel.propertySize.collectAsState()
    val propertyBedrooms by viewModel.propertyBedrooms.collectAsState()
    val propertyFloor by viewModel.propertyFloors.collectAsState()
    val propertyBathrooms by viewModel.propertyBathrooms.collectAsState()
    val propertyParking by viewModel.propertyParking.collectAsState()
    val propertyFurnished by viewModel.propertyFurnished.collectAsState()
    val currentStep by viewModel.currentStep.collectAsState()
    val days = viewModel.days
    val selectedDay by viewModel.day.collectAsState()
    val propertyPrice by viewModel.propertyPrice.collectAsState()
    val showStartTimePicker by viewModel.showStartTimePicker.collectAsState()
    val selectedStartHour by viewModel.selectedStartHour.collectAsState()
    val selectedStartMinute by viewModel.selectedStartMinute.collectAsState()
    val showEndTimePicker by viewModel.showEndTimePicker.collectAsState()
    val selectedEndHour by viewModel.selectedEndHour.collectAsState()
    val selectedEndMinute by viewModel.selectedEndMinute.collectAsState()
    val savedSchedules by viewModel.savedSchedules.collectAsState()
    val lotTopography by viewModel.lotTopography.collectAsState()
    val lotClassification by viewModel.lotClassification.collectAsState()
    val lotHasWater by viewModel.lotHasWater.collectAsState()
    val lotHasElectricity by viewModel.lotHasElectricity.collectAsState()
    val lotHasSewage by viewModel.lotHasSewage.collectAsState()
    val selectedImagesUris by viewModel.selectedImagesUris.collectAsState()

    val isFormCompleted = viewModel.isScheduleCompleted(
        selectedDay,
        selectedStartHour,
        selectedStartMinute,
        selectedEndHour,
        selectedEndMinute
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentStep) {
            SalesStep.PROPERTY_LOCATION -> {
                PropertyLocationForm(
                    propertyType = propertyType,
                    setPropertyType = { viewModel.setPropertyType(it) },
                    neighborhood = neighborhood,
                    onNeighborhoodChanged = { viewModel.onNeighborhoodChanged(it) },
                    municipality = municipality,
                    onMunicipalityChanged = { viewModel.onMunicipalityChanged(it) },
                    department = department,
                    onDepartmentChanged = { viewModel.onDepartmentChanged(it) },
                    propertyAddress = propertyAddress,
                    onPropertyAddressChanged = { viewModel.onPropertyAddressChanged(it) },
                    savedPosition = savedPosition,
                    onMarkerPositionChanged = { viewModel.onMarkerPositionChanged(it) },
                    onSavedPositionChanged = { viewModel.onSavePosition(it) },
                    isPositionSaved = isPositionSaved,
                )
                Spacer(modifier = Modifier.padding(26.dp))
                NextButton(
                    onClick = { viewModel.setCurrentStep(SalesStep.PROPERTY_SPECS) }
                )
            }
            SalesStep.PROPERTY_SPECS -> {
                PropertySpecsForm(
                    propertyDescription = propertyDescription,
                    onPropertyDescriptionChange = { viewModel.onPropertyDescriptionChanged(it) },
                    propertySize = propertySize,
                    onPropertySizeChange = { viewModel.onPropertySizeChanged(it) },
                    propertyBedrooms = propertyBedrooms,
                    onPropertyBedroomsChange = { viewModel.onPropertyBedroomsChanged(it) },
                    propertyFloor = propertyFloor ,
                    onPropertyFloorChange = { viewModel.onPropertyFloorsChanged(it) },
                    propertyBathrooms = propertyBathrooms,
                    onPropertyBathroomsChange = { viewModel.onPropertyBathroomsChanged(it) },
                    propertyParking = propertyParking,
                    onPropertyParkingChange = { viewModel.onPropertyParkingChanged(it) },
                    propertyFurnished = propertyFurnished,
                    onPropertyFurnishedChange = { viewModel.onPropertyFurnishedChanged(it) },
                    propertyType = propertyType,
                    lotTopography = lotTopography,
                    onLotTopographyChange = { viewModel.setLotTopography(it) },
                    lotClassification = lotClassification,
                    onLotClassificationChange = { viewModel.setLotClassification(it) },
                    lotHasWater = lotHasWater,
                    onLotHasWaterChange = { viewModel.setLotHasWater(it) },
                    lotHasEnergy = lotHasElectricity,
                    onLotHasEnergyChange = { viewModel.setLotHasElectricity(it) },
                    lotHasSewage = lotHasSewage,
                    onLotHasSewageChange = { viewModel.setLotHasSewage(it) }
                )
                NextButton(
                    onClick = { viewModel.setCurrentStep(SalesStep.PROPERTY_DETAILS) }
                )
            }
            SalesStep.PROPERTY_DETAILS -> {
                PropertyDetailsForm(
                    propertyPrice = propertyPrice,
                    setPropertyPrice = { viewModel.setPropertyPrice(it) },
                    days = days,
                    selectedDay = selectedDay,
                    onDaySelected = { viewModel.setDay(it) },
                    showStartTimePicker = showStartTimePicker,
                    setShowStartTimePicker = { viewModel.setShowStartTimePicker(it) },
                    selectedStartHour = selectedStartHour,
                    selectedStartMinute = selectedStartMinute,
                    onStartHourChanged = { viewModel.setSelectedStartHour(it) },
                    onStartMinuteChanged = { viewModel.setSelectedStartMinute(it) },
                    selectedFinishHour = selectedEndHour,
                    selectedFinishMinute = selectedEndMinute,
                    onFinishMinuteChanged = { viewModel.setSelectedEndMinute(it) },
                    onFinishHourChanged = { viewModel.setSelectedEndHour(it) },
                    showFinishTimePicker = showEndTimePicker,
                    setShowFinishTimePicker = { viewModel.setShowEndTimePicker(it) },
                    savedSchedules = savedSchedules,
                    isFormCompleted = isFormCompleted,
                    addSchedule = { viewModel.addSchedule(it) }
                )
                Spacer(modifier = Modifier.padding(26.dp))
                NextButton(
                    onClick = { viewModel.setCurrentStep(SalesStep.PROPERTY_PHOTOS) }
                )
            }
            SalesStep.PROPERTY_PHOTOS -> {
                PropertyPhotosForm(
                    selectedImageUris = selectedImagesUris,
                    setSelectedImageUris = { viewModel.setSelectedImagesUris(it) },
                )
                Spacer(modifier = Modifier.padding(26.dp))
                PostButton(
                    onClick = { viewModel.uploadProperty(context = context) }
                )
            }
        }
    }
}

@Composable
fun PostButton(onClick: () -> Unit) {
    SecondaryButton(
        text = R.string.sales_form_upload,
        onClick = onClick
    )
}

@Composable
fun NextButton(onClick: () -> Unit) {
    SecondaryButton(
        text = R.string.app_next,
        onClick = onClick
    )
}

@Composable
@Preview
fun SalesFormScreenPreview() {
    SalesFormScreen(
        navController = NavController(LocalContext.current)
    )
}