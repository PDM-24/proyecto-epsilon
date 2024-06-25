package com.hnrylvo.inmomarket.ui.compose.maps

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.theme.LightGreen

@Composable
fun OnlyViewMap(
    latitude: Double,
    longitude: Double
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 10f)
    }

    val coordinates = LatLng(latitude, longitude)
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(350.dp)
            .border(
                width = 2.dp,
                color = LightGreen,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp)),
        uiSettings = MapUiSettings(mapToolbarEnabled = true),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(position = coordinates),
            title = stringResource(id = R.string.sales_form_property_exact_location),
            snippet = "$latitude, $longitude"
        )
    }
}

@Composable
@Preview
fun OnlyViewMapPreview() {
    OnlyViewMap(
        latitude = 10.0,
        longitude = 10.0
    )
}