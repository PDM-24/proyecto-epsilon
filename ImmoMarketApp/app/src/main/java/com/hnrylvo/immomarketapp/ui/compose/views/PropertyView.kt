package com.hnrylvo.inmomarket.ui.compose.views

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.compose.icontexts.TextWithIcon
import com.hnrylvo.inmomarket.ui.compose.maps.OnlyViewMap
import com.hnrylvo.inmomarket.ui.compose.sections.PhotoSection

@Composable
fun PropertyView(
    propertyPhotos: List<Uri> = listOf(),
    propertyNeighborhood: String,
    propertyType: String,
    propertySize: String,
    propertyBedrooms: String,
    propertyBathrooms: String,
    propertyParking: String,
    propertyFurnished: String,
    propertyFloors: String,
    propertyDescription: String,
    propertyAddress: String,
    propertyLatitude: Double,
    propertyLongitude: Double,
    ownerName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PropertyPhotos(
            photoList = propertyPhotos
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyMainInfo(
            neighborhood = propertyNeighborhood,
            propertyType = propertyType
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyDetails(
            propertySize = propertySize,
            propertyBedrooms = propertyBedrooms,
            propertyBathrooms = propertyBathrooms,
            propertyParking = propertyParking,
            propertyFurnished = propertyFurnished,
            propertyFloors = propertyFloors
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyDescription(
            propertyDescription = propertyDescription
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyLocation(
            propertyAddress = propertyAddress,
            propertyLatitude = propertyLatitude,
            propertyLongitude = propertyLongitude
        )
        Spacer(modifier = Modifier.padding(26.dp))
        OwnerDetails(
            ownerName = ownerName
        )
        Spacer(modifier = Modifier.padding(46.dp))
    }
}

@Composable
fun OwnerDetails(ownerName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .border(1.dp, SecondaryGreen, RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_profile), contentDescription = null)
            Spacer(modifier = Modifier.padding(8.dp))
            Column {
                Text(
                    text = ownerName,
                    style = MyTypography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryGreen
                )
                Text(
                    text = "Propietario",
                    style = MyTypography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PropertyLocation(
    propertyAddress: String,
    propertyLatitude: Double,
    propertyLongitude: Double
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_location),
            style = MyTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = SecondaryGreen,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = propertyAddress,
            style = MyTypography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        OnlyViewMap(
            latitude = propertyLatitude,
            longitude = propertyLongitude
        )
    }
}

@Composable
fun PropertyDescription(propertyDescription: String) {

    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = stringResource(id = R.string.sales_form_property_description),
            style = MyTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { isExpanded = !isExpanded }
                )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = propertyDescription,
                style = MyTypography.bodyMedium,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2
            )
            if (!isExpanded) {
                Text(
                    text = stringResource(id = R.string.app_read_more),
                    style = MyTypography.bodyMedium,
                    color = SecondaryGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PropertyDetails(
    propertySize: String,
    propertyBedrooms: String,
    propertyBathrooms: String,
    propertyParking: String,
    propertyFurnished: String,
    propertyFloors: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextWithIcon(
                text = stringResource(id = R.string.sales_form_property_size_var, propertySize),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_ruler,
                iconSize = 18.dp,
                padding = 0.dp
            )
            TextWithIcon(
                text = stringResource(id = R.string.sales_form_property_bedrooms_var, propertyBedrooms),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_bed,
                iconSize = 20.dp,
                padding = 0.dp
            )
            TextWithIcon(
                text = stringResource(id = R.string.sales_form_property_bathrooms_var, propertyBathrooms),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_bath,
                iconSize = 20.dp,
                padding = 0.dp
            )
            TextWithIcon(
                text = stringResource(id = R.string.sales_form_property_parking_var, propertyParking),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_parking,
                iconSize = 20.dp,
                padding = 0.dp
            )
            TextWithIcon(
                text = if (propertyFurnished == "Yes")
                    stringResource(id = R.string.sales_form_property_furnished)
                else
                    stringResource(id = R.string.sales_form_property_no_furnished),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_sofa,
                iconSize = 20.dp,
                padding = 0.dp
            )
            TextWithIcon(
                text = stringResource(id = R.string.sales_form_property_floor_var, propertyFloors),
                style = MyTypography.labelSmall,
                icon = R.drawable.ic_floor,
                iconSize = 20.dp,
                padding = 0.dp
            )
        }
    }
}

@Composable
fun PropertyMainInfo(
    neighborhood: String,
    propertyType: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = neighborhood,
            style = MyTypography.titleSmall
        )
        Text(
            text = propertyType,
            style = MyTypography.bodyLarge
        )
    }
}

@Composable
fun PropertyPhotos(
    photoList: List<Uri>
) {
    PhotoSection(
        photoList = photoList
    )
}

@Composable
@Preview(showSystemUi = true)
fun PropertyViewPreview() {
    PropertyView(
        propertyPhotos = listOf(),
        propertyNeighborhood = "La Sultana",
        propertyType = "Casa",
        propertySize = "120",
        propertyBedrooms = "3",
        propertyBathrooms = "2",
        propertyParking = "1",
        propertyFurnished = "No",
        propertyFloors = "2",
        propertyDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        propertyAddress = "La Sultana, Guatemala",
        propertyLatitude = 12.3456789,
        propertyLongitude = 12.3456789,
        ownerName = "John Doe"
    )
}