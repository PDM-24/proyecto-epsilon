package com.hnrylvo.inmomarket.ux.property_view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.hnrylvo.inmomarket.ui.compose.buttons.SchedulePropertyViewingButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.views.PropertyView

@Composable
fun PropertyViewScreen(navController: NavController, propertyId: String) {
    val context = LocalContext.current

    val viewModel = remember {
        PropertyViewModel(context = context, navController = navController)
    }

    val propertyList by viewModel.propertyInfo.collectAsState(null)
    val isLoading by viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.getPropertyById(propertyId)
    }

    AppScaffold {
        Log.d("PropertyViewScreen", "Property List: $propertyList")
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                propertyList?.let { property ->
                    PropertyView(
                        propertyNeighborhood = property.neighborhood,
                        propertyType = property.propertyType,
                        propertySize = property.propertySize,
                        propertyBedrooms = property.propertyBedrooms,
                        propertyBathrooms = property.propertyBathrooms,
                        propertyParking = property.propertyParking,
                        propertyFurnished = property.propertyFurnished,
                        propertyFloors = property.propertyFloors,
                        propertyDescription = property.propertyDescription,
                        propertyAddress = property.propertyAddress,
                        propertyLatitude = property.latitude,
                        propertyLongitude = property.longitude,
                        ownerName = "${property.seller.name} ${property.seller.lastName}"
                    )
                    SchedulePropertyViewingButton(
                        propertyPrice = property.propertyPrice,
                        onClick = {}
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PropertyViewScreenPreview() {
    PropertyViewScreen(
        navController = NavController(LocalContext.current),
        propertyId = "1"
    )

}