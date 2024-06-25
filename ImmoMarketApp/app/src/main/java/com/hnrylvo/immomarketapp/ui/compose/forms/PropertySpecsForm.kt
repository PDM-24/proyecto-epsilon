package com.hnrylvo.inmomarket.ui.compose.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.inputs.MiniDropDownMenuWithTitle
import com.hnrylvo.inmomarket.ui.compose.inputs.MiniInputFieldWithTitle
import com.hnrylvo.inmomarket.ui.compose.inputs.TextInputFieldWithTitle

@Composable
fun PropertySpecsForm(
    propertyType: String,
    propertyDescription: String,
    onPropertyDescriptionChange: (String) -> Unit,
    propertySize: String,
    onPropertySizeChange: (String) -> Unit,
    propertyBedrooms: String,
    onPropertyBedroomsChange: (String) -> Unit,
    propertyFloor: String,
    onPropertyFloorChange: (String) -> Unit,
    propertyBathrooms: String,
    onPropertyBathroomsChange: (String) -> Unit,
    propertyParking: String,
    onPropertyParkingChange: (String) -> Unit,
    propertyFurnished: String,
    onPropertyFurnishedChange: (String) -> Unit,

    //terreno
    lotHasWater: String,
    onLotHasWaterChange: (String) -> Unit,
    lotHasEnergy: String,
    onLotHasEnergyChange: (String) -> Unit,
    lotHasSewage: String,
    onLotHasSewageChange: (String) -> Unit,
    lotTopography: String,
    onLotTopographyChange: (String) -> Unit,
    lotClassification: String,
    onLotClassificationChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_specs_title),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(25.dp))
        if (propertyType == "Terreno/lote") {
            LotSpecs(
                lotSize = propertySize,
                onLotSizeChange = onPropertySizeChange,
                lotHasWater = lotHasWater,
                onLotHasWaterChange = onLotHasWaterChange,
                lotHasEnergy = lotHasEnergy,
                onLotHasEnergyChange = onLotHasEnergyChange,
                lotHasSewage = lotHasSewage,
                onLotHasSewageChange = onLotHasSewageChange,
                lotTopography = lotTopography,
                onLotTopographyChange = onLotTopographyChange,
                lotClassification = lotClassification,
                onLotClassificationChange = onLotClassificationChange
            )
        }
        else{
            PropertySpecs(
                propertySize = propertySize,
                onPropertySizeChange = onPropertySizeChange,
                propertyBedrooms = propertyBedrooms,
                onPropertyBedroomsChange = onPropertyBedroomsChange,
                propertyFloor = propertyFloor,
                onPropertyFloorChange = onPropertyFloorChange,
                propertyBathrooms = propertyBathrooms,
                onPropertyBathroomsChange = onPropertyBathroomsChange,
                propertyParking = propertyParking,
                onPropertyParkingChange = onPropertyParkingChange,
                propertyFurnished = propertyFurnished,
                onPropertyFurnishedChange = onPropertyFurnishedChange
            )
        }
        Spacer(modifier = Modifier.padding(36.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(36.dp))
        PropertyDetails(
            propertyDescription = propertyDescription,
            onPropertyDescriptionChange = onPropertyDescriptionChange
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun PropertyDetails(
    propertyDescription: String,
    onPropertyDescriptionChange: (String) -> Unit
) {
    TextInputFieldWithTitle(
        title = R.string.sales_form_property_add_description,
        value = propertyDescription,
        placeholderId = R.string.sales_form_property_details,
        keyboardType = KeyboardType.Text,
        onValueChange = onPropertyDescriptionChange
    )
}

@Composable
fun LotSpecs(
    lotSize: String,
    onLotSizeChange: (String) -> Unit,
    lotHasWater: String,
    onLotHasWaterChange: (String) -> Unit,
    lotHasEnergy: String,
    onLotHasEnergyChange: (String) -> Unit,
    lotHasSewage: String,
    onLotHasSewageChange: (String) -> Unit,
    lotTopography: String,
    onLotTopographyChange: (String) -> Unit,
    lotClassification: String,
    onLotClassificationChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_size),
                value = lotSize,
                keyboardType = KeyboardType.Number,
                onValueChange = onLotSizeChange,
                icon = R.drawable.ic_ruler,
                suffix = "m²"
            )
            MiniDropDownMenuWithTitle(
                title = R.string.sales_form_property_topography,
                items = listOf(
                    stringResource(id = R.string.sales_form_property_topography_flat),
                    stringResource(id = R.string.sales_form_property_topography_slope),
                    stringResource(id = R.string.sales_form_property_topography_mountainous)
                ),
                selectedOption = lotTopography,
                onOptionSelected = { onLotTopographyChange(it) }
            )
            MiniDropDownMenuWithTitle(
                title = R.string.sales_form_property_classification,
                items = listOf(
                    stringResource(id = R.string.sales_form_property_classification_residential),
                    stringResource(id = R.string.sales_form_property_classification_commercial),
                    stringResource(id = R.string.sales_form_property_classification_industrial)
                ),
                selectedOption = lotClassification,
                onOptionSelected = onLotClassificationChange
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniDropDownMenuWithTitle(
                title = R.string.sales_form_property_has_water,
                items = listOf(
                    stringResource(id = R.string.app_yes),
                    stringResource(id = R.string.app_no)
                ),
                selectedOption = lotHasWater,
                onOptionSelected = onLotHasWaterChange
            )
            MiniDropDownMenuWithTitle(
                title = R.string.sales_form_property_has_electricity,
                items = listOf(
                    stringResource(id = R.string.app_yes),
                    stringResource(id = R.string.app_no)
                ),
                selectedOption = lotHasEnergy,
                onOptionSelected = onLotHasEnergyChange
            )
            MiniDropDownMenuWithTitle(
                title = R.string.sales_form_property_has_sewage,
                items = listOf(
                    stringResource(id = R.string.app_yes),
                    stringResource(id = R.string.app_no)
                ),
                selectedOption = lotHasSewage,
                onOptionSelected = onLotHasSewageChange
            )
        }
    }
}

@Composable
fun PropertySpecs(
    propertySize: String,
    onPropertySizeChange: (String) -> Unit,
    propertyBedrooms: String,
    onPropertyBedroomsChange: (String) -> Unit,
    propertyFloor: String,
    onPropertyFloorChange: (String) -> Unit,
    propertyBathrooms: String,
    onPropertyBathroomsChange: (String) -> Unit,
    propertyParking: String,
    onPropertyParkingChange: (String) -> Unit,
    propertyFurnished: String,
    onPropertyFurnishedChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_size),
                value = propertySize,
                keyboardType = KeyboardType.Number,
                onValueChange = onPropertySizeChange,
                icon = R.drawable.ic_ruler,
                suffix = "m²"
            )
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_bedrooms),
                value = propertyBedrooms,
                keyboardType = KeyboardType.Number,
                onValueChange = onPropertyBedroomsChange,
                icon = R.drawable.ic_bed
            )
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_floor),
                value = propertyFloor,
                keyboardType = KeyboardType.Number,
                onValueChange = onPropertyFloorChange,
                icon = R.drawable.ic_floor
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_bathrooms),
                value = propertyBathrooms,
                keyboardType = KeyboardType.Number,
                onValueChange = onPropertyBathroomsChange,
                icon = R.drawable.ic_bath
            )
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_parking),
                value = propertyParking,
                keyboardType = KeyboardType.Number,
                onValueChange = onPropertyParkingChange,
                icon = R.drawable.ic_parking
            )
            MiniInputFieldWithTitle(
                title = stringResource(id = R.string.sales_form_property_furnished),
                value = propertyFurnished,
                keyboardType = KeyboardType.Text,
                onValueChange = onPropertyFurnishedChange,
                icon = R.drawable.ic_sofa
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PropertySpecsFormPreview() {
    PropertySpecsForm(
        propertyDescription = "",
        onPropertyDescriptionChange = {},
        propertySize = "",
        onPropertySizeChange = {},
        propertyBedrooms = "",
        onPropertyBedroomsChange = {},
        propertyFloor = "",
        onPropertyFloorChange = {},
        propertyBathrooms = "",
        onPropertyBathroomsChange = {},
        propertyParking = "",
        onPropertyParkingChange = {},
        propertyFurnished = "",
        onPropertyFurnishedChange = {},
        propertyType = "Terreno/lote",
        lotHasWater = "",
        onLotHasWaterChange = {},
        lotHasEnergy = "",
        onLotHasEnergyChange = {},
        lotHasSewage = "",
        onLotHasSewageChange = {},
        lotTopography = "",
        onLotTopographyChange = {},
        lotClassification = "",
        onLotClassificationChange = {}
    )
}
