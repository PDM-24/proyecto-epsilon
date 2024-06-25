package com.hnrylvo.inmomarket.ui.compose.forms

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.compose.inputs.DropDownMenuWithTitle
import com.hnrylvo.inmomarket.ui.compose.inputs.InputFieldWithTitle
import com.hnrylvo.inmomarket.ui.compose.maps.AppMap

@Composable
fun PropertyLocationForm(
    propertyType: String,
    setPropertyType: (String) -> Unit,
    neighborhood: String,
    onNeighborhoodChanged: (String) -> Unit,
    municipality: String,
    onMunicipalityChanged: (String) -> Unit,
    department: String,
    onDepartmentChanged: (String) -> Unit,
    propertyAddress: String,
    onPropertyAddressChanged: (String) -> Unit,
    savedPosition: LatLng?,
    onMarkerPositionChanged: (LatLng) -> Unit,
    onSavedPositionChanged: (LatLng) -> Unit,
    isPositionSaved: Boolean
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_title),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.height(36.dp))
        PropertyTypeInput(
            propertyType = propertyType,
            onPropertyTypeChanged = {setPropertyType(it)}
        )
        Spacer(modifier = Modifier.height(65.dp))
        Text(
            text = stringResource(id = R.string.sales_form_location_title),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.height(36.dp))
        NeighborhoodInput(
            neighborhood = neighborhood,
            onNeighborhoodChanged = {onNeighborhoodChanged(it)}
        )
        Spacer(modifier = Modifier.height(36.dp))
        MunicipalityInput(
            municipality = municipality,
            onMunicipalityChanged = {onMunicipalityChanged(it)}
        )
        Spacer(modifier = Modifier.height(36.dp))
        DepartmentInput(
            department = department,
            onDepartmentChanged = {onDepartmentChanged(it)}
        )
        Spacer(modifier = Modifier.height(36.dp))
        AddressInput(
            propertyAddress = propertyAddress,
            onPropertyAddressChanged = {onPropertyAddressChanged(it)}
        )
        Spacer(modifier = Modifier.height(36.dp))
        MapLocationInput(
            markerPosition = savedPosition,
            onMarkerPositionChanged = {onMarkerPositionChanged(it)},
            onSavePosition = {onSavedPositionChanged(it)},
            isPositionSaved = isPositionSaved,
            context = context
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun MapLocationInput(
    markerPosition: LatLng?,
    onMarkerPositionChanged: (LatLng) -> Unit,
    onSavePosition: (LatLng) -> Unit,
    isPositionSaved: Boolean,
    context: Context
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_location),
            style = MyTypography.bodyLarge,
        )
        AppMap(
            context = context,
            markerPosition = markerPosition,
            onMarkerPositionChanged = onMarkerPositionChanged,
            onSavePosition = onSavePosition,
            isPositionSaved = isPositionSaved
        )
    }
}

@Composable
fun AddressInput(
    propertyAddress: String,
    onPropertyAddressChanged: (String) -> Unit
) {
    InputFieldWithTitle(
        title = R.string.sales_form_property_address,
        value = propertyAddress,
        placeholderId = R.string.sales_form_property_address_input,
        keyboardType = KeyboardType.Text,
        onValueChange = { onPropertyAddressChanged(it) },
        maxWidth = 0.8f
    )
}

@Composable
fun NeighborhoodInput(
    neighborhood: String,
    onNeighborhoodChanged: (String) -> Unit
) {
    InputFieldWithTitle(
        title = R.string.sales_form_property_neighborhood,
        value = neighborhood,
        placeholderId = R.string.sales_form_property_neighborhood_input,
        keyboardType = KeyboardType.Text,
        onValueChange = { onNeighborhoodChanged(it) },
        maxWidth = 0.8f
    )
}

@Composable
fun MunicipalityInput(
    municipality: String,
    onMunicipalityChanged: (String) -> Unit
) {
    InputFieldWithTitle(
        title = R.string.sales_form_property_municipality,
        value = municipality,
        placeholderId = R.string.sales_form_property_municipality_input,
        keyboardType = KeyboardType.Text,
        onValueChange = { onMunicipalityChanged(it) },
        maxWidth = 0.8f
    )
}

@Composable
fun DepartmentInput(
    department: String,
    onDepartmentChanged: (String) -> Unit
) {
    InputFieldWithTitle(
        title = R.string.sales_form_property_department,
        value = department,
        placeholderId = R.string.sales_form_property_department_input,
        keyboardType = KeyboardType.Text,
        onValueChange = { onDepartmentChanged(it) },
        maxWidth = 0.8f
    )
}

@Composable
fun PropertyTypeInput(
    propertyType: String,
    onPropertyTypeChanged: (String) -> Unit
) {
    DropDownMenuWithTitle(
        title = R.string.sales_form_property_types,
        placeHolderText = R.string.app_select_an_option,
        items = listOf(
            stringResource(id = R.string.sales_form_property_type_house),
            stringResource(id = R.string.sales_form_property_type_apartment),
            stringResource(id = R.string.sales_form_property_type_beach_house),
            stringResource(id = R.string.sales_form_property_type_land)
        ),
        selectedOption = propertyType,
        onOptionSelected = { onPropertyTypeChanged(it) }
    )
}


@Composable
@Preview(showSystemUi = true)
fun PropertyLocationFormPreview() {
    PropertyLocationForm(
        propertyType = "",
        setPropertyType = {},
        neighborhood = "",
        onNeighborhoodChanged = {},
        municipality = "",
        onMunicipalityChanged = {},
        department = "",
        onDepartmentChanged = {},
        propertyAddress = "",
        onPropertyAddressChanged = {},
        savedPosition = null,
        onMarkerPositionChanged = {},
        onSavedPositionChanged = {},
        isPositionSaved = false
    )
}