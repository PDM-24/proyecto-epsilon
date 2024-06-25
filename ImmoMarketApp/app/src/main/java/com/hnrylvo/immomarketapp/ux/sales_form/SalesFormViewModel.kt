package com.hnrylvo.inmomarket.ux.sales_form

import android.content.Context
import android.icu.util.Calendar
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.hnrylvo.inmomarket.data.networking.model.request.UploadPropertyRequest
import com.hnrylvo.inmomarket.data.preferences.AuthPreferencesManager
import com.hnrylvo.inmomarket.data.repository.PropertyRepository
import com.hnrylvo.inmomarket.data.utils.onFailure
import com.hnrylvo.inmomarket.data.utils.onSuccess
import com.hnrylvo.inmomarket.utils.Schedule
import com.hnrylvo.inmomarket.ux.home.HomeRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class SalesFormViewModel(
    context: Context,
    private val navController: NavController
) : ViewModel() {

    private val propertyRepository = PropertyRepository()
    private val authPreferencesManager = AuthPreferencesManager(context = context)
    private val contentResolver = context.contentResolver

    private fun convertUrisToFiles(context: Context, uris: List<Uri>): List<File> {
        return uris.map { uri ->
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val tempFile = File.createTempFile("image", ".jpg", context.cacheDir)
            val outputStream = FileOutputStream(tempFile)

            inputStream.use { input ->
                outputStream.use { output ->
                    input?.copyTo(output)
                }
            }

            tempFile
        }
    }

    private val _propertyType = MutableStateFlow("")
    val propertyType = _propertyType.asStateFlow()

    private val _lotTopography = MutableStateFlow("")
    val lotTopography = _lotTopography.asStateFlow()

    private val _lotClassification = MutableStateFlow("")
    val lotClassification = _lotClassification.asStateFlow()

    private val _lotHasWater = MutableStateFlow("")
    val lotHasWater = _lotHasWater.asStateFlow()

    private val _lotHasElectricity = MutableStateFlow("")
    val lotHasElectricity = _lotHasElectricity.asStateFlow()

    private val _lotHasSewage = MutableStateFlow("")
    val lotHasSewage = _lotHasSewage.asStateFlow()

    private val _neighborhood = MutableStateFlow("")
    val neighborhood = _neighborhood.asStateFlow()

    private val _municipality = MutableStateFlow("")
    val municipality = _municipality.asStateFlow()

    private val _department = MutableStateFlow("")
    val department = _department.asStateFlow()

    private val _propertyAddress = MutableStateFlow("")
    val propertyAddress = _propertyAddress.asStateFlow()

    private val _savedPosition = MutableStateFlow<LatLng?>(null)
    val savedPosition = _savedPosition.asStateFlow()

    private val _isPositionSaved = MutableStateFlow(false)
    val isPositionSaved = _isPositionSaved.asStateFlow()

    private val _propertySize = MutableStateFlow("")
    val propertySize = _propertySize.asStateFlow()

    private val _propertyBedrooms = MutableStateFlow("")
    val propertyBedrooms = _propertyBedrooms.asStateFlow()

    private val _propertyFloors = MutableStateFlow("")
    val propertyFloors = _propertyFloors.asStateFlow()

    private val _propertyBathrooms = MutableStateFlow("")
    val propertyBathrooms = _propertyBathrooms.asStateFlow()

    private val _propertyParking = MutableStateFlow("")
    val propertyParking = _propertyParking.asStateFlow()

    private val _propertyFurnished = MutableStateFlow("")
    val propertyFurnished = _propertyFurnished.asStateFlow()

    private val _propertyDescription = MutableStateFlow("")
    val propertyDescription = _propertyDescription.asStateFlow()

    private val _currentStep = MutableStateFlow(SalesStep.PROPERTY_LOCATION)
    val currentStep = _currentStep.asStateFlow()

    private val _days = listOf(
        "Dom",
        "Lun",
        "Mar",
        "Mié",
        "Jue",
        "Vie",
        "Sáb"
    )
    val days: List<String> get() = _days

    private val _day = MutableStateFlow(_days[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1])
    val day = _day.asStateFlow()

    private val _propertyPrice = MutableStateFlow("")
    val propertyPrice = _propertyPrice.asStateFlow()

    private val _showStartTimePicker = MutableStateFlow(false)
    val showStartTimePicker = _showStartTimePicker.asStateFlow()

    private val _selectedStartHour = MutableStateFlow("00")
    val selectedStartHour = _selectedStartHour.asStateFlow()

    private val _selectedStartMinute = MutableStateFlow("00")
    val selectedStartMinute = _selectedStartMinute.asStateFlow()

    private val _showEndTimePicker = MutableStateFlow(false)
    val showEndTimePicker = _showEndTimePicker.asStateFlow()

    private val _selectedEndHour = MutableStateFlow("00")
    val selectedEndHour = _selectedEndHour.asStateFlow()

    private val _selectedEndMinute = MutableStateFlow("00")
    val selectedEndMinute = _selectedEndMinute.asStateFlow()

    private val _savedSchedules = MutableStateFlow<List<Schedule>>(emptyList())
    val savedSchedules = _savedSchedules.asStateFlow()

    private val _selectedImagesUris = MutableStateFlow<List<Uri>>(emptyList())
    val selectedImagesUris = _selectedImagesUris.asStateFlow()

    fun setSelectedImagesUris(uris: List<Uri>) {
        _selectedImagesUris.update { uris }
    }

    fun addSchedule(schedule: Schedule) {
        _savedSchedules.update { it + schedule }
    }

    fun setShowEndTimePicker(show: Boolean) {
        _showEndTimePicker.update { show }
    }

    fun setSelectedEndHour(hour: String) {
        _selectedEndHour.update { hour }
    }

    fun setSelectedEndMinute(minute: String) {
        _selectedEndMinute.update { minute }
    }

    fun setShowStartTimePicker(show: Boolean) {
        _showStartTimePicker.update { show }
    }

    fun setSelectedStartHour(hour: String) {
        _selectedStartHour.update { hour }
    }

    fun setSelectedStartMinute(minute: String) {
        _selectedStartMinute.update { minute }
    }

    fun setPropertyPrice(price: String) {
        _propertyPrice.update { price }
    }

    fun setDay(newDay: String) {
        _day.update { newDay }
    }

    fun setCurrentStep(step: SalesStep) {
        _currentStep.update { step }
    }

    fun setPropertyType(propertyType: String) {
        _propertyType.update { propertyType }
    }

    fun setLotTopography(lotTopography: String) {
        _lotTopography.update { lotTopography }
    }

    fun setLotClassification(lotClassification: String) {
        _lotClassification.update { lotClassification }
    }

    fun setLotHasWater(lotHasWater: String) {
        _lotHasWater.update { lotHasWater }
    }

    fun setLotHasElectricity(lotHasElectricity: String) {
        _lotHasElectricity.update { lotHasElectricity }
    }

    fun setLotHasSewage(lotHasSewage: String) {
        _lotHasSewage.update { lotHasSewage }
    }

    fun onNeighborhoodChanged(neighborhood: String) {
        _neighborhood.update { neighborhood }
    }

    fun onMunicipalityChanged(municipality: String) {
        _municipality.update { municipality }
    }

    fun onDepartmentChanged(department: String) {
        _department.update { department }
    }

    fun onPropertyAddressChanged(propertyAddress: String) {
        _propertyAddress.update { propertyAddress }
    }

    fun onSavePosition(position: LatLng) {
        _savedPosition.update { position }
        _isPositionSaved.update { true }
    }

    fun onMarkerPositionChanged(position: LatLng) {
        _savedPosition.update { position }
    }

    fun onPropertySizeChanged(propertySize: String) {
        _propertySize.update { propertySize }
    }

    fun onPropertyBedroomsChanged(propertyBedrooms: String) {
        _propertyBedrooms.update { propertyBedrooms }
    }

    fun onPropertyFloorsChanged(propertyFloors: String) {
        _propertyFloors.update { propertyFloors }
    }

    fun onPropertyBathroomsChanged(propertyBathrooms: String) {
        _propertyBathrooms.update { propertyBathrooms }
    }

    fun onPropertyParkingChanged(propertyParking: String) {
        _propertyParking.update { propertyParking }
    }

    fun onPropertyFurnishedChanged(propertyFurnished: String) {
        _propertyFurnished.update { propertyFurnished }
    }

    fun onPropertyDescriptionChanged(propertyDescription: String) {
        _propertyDescription.update { propertyDescription }
    }

    fun isScheduleCompleted(
        selectedDay: String,
        selectedStartHour: String,
        selectedStartMinute: String,
        selectedFinishHour: String,
        selectedFinishMinute: String
    ): Boolean {
        return selectedDay.isNotEmpty() &&
                selectedStartHour.isNotEmpty() &&
                selectedStartMinute.isNotEmpty() &&
                selectedFinishHour.isNotEmpty() &&
                selectedFinishMinute.isNotEmpty()
    }

    fun uploadProperty(context: Context) {
        val files = convertUrisToFiles(context, _selectedImagesUris.value)

        val request = UploadPropertyRequest(
            propertyType = _propertyType.value,
            neighborhood = _neighborhood.value,
            municipality = _municipality.value,
            department = _department.value,
            propertyAddress = _propertyAddress.value,
            longitude = _savedPosition.value?.longitude ?: 0.0,
            latitude = _savedPosition.value?.latitude ?: 0.0,
            propertySize = _propertySize.value,
            propertyBedrooms = _propertyBedrooms.value,
            propertyFloors = _propertyFloors.value,
            propertyBathrooms = _propertyBathrooms.value,
            propertyParking = _propertyParking.value,
            propertyFurnished = _propertyFurnished.value,
            propertyDescription = _propertyDescription.value,
            propertyPrice = _propertyPrice.value,
            scheduleViewing = _savedSchedules.value,
            //images = _selectedImagesUris.value.map { it.toString() }
            images = files
        )
        viewModelScope.launch {
            authPreferencesManager.getAuthToken().collectLatest { token ->
                Log.d("UploadProperty", "Token=$token")
                if (token.isNullOrEmpty()) {
                    Log.e("UploadProperty", "Error: Token is null or empty")
                    Log.d("UploadProperty", "ImageUris=${_selectedImagesUris.value}")
                    Log.d("UploadProperty", "photos=${files}}")
                } else {
                    propertyRepository.uploadProperty(request, token).collectLatest { response ->
                        response.onSuccess {
                            postProperty()
                            Log.d("UploadProperty", "Success")
                        }
                        response.onFailure {
                            Log.e("UploadProperty", "Error: ${it.throwable.message}")
                        }
                    }
                }
            }
        }
    }

    private fun postProperty() {
        navController.navigate(HomeRoute.route)
    }
}

enum class SalesStep {
    PROPERTY_LOCATION,
    PROPERTY_SPECS,
    PROPERTY_DETAILS,
    PROPERTY_PHOTOS
}
