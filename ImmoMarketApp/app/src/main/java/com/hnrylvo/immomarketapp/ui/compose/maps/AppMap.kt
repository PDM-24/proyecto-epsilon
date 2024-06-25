package com.hnrylvo.inmomarket.ui.compose.maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.inmomarket.ui.compose.buttons.TertiaryButton
import com.hnrylvo.inmomarket.utils.LocationProvider

@Composable
fun AppMap(
    context: Context,
    markerPosition: LatLng?,
    onMarkerPositionChanged: (LatLng) -> Unit,
    onSavePosition: (LatLng) -> Unit,
    isPositionSaved: Boolean
) {
    val hasLocationPermission = remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasLocationPermission.value = isGranted
        }
    )

    LaunchedEffect(key1 = context) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                hasLocationPermission.value = true
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    val locationProvider = LocationProvider(context)

    // Estado de la cámara del mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(13.6929, -89.2182), 10f)
    }

    if (hasLocationPermission.value) {
        LaunchedEffect(key1 = hasLocationPermission.value) {
            locationProvider.getCurrentLocation { latLng ->
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 10f)
                onMarkerPositionChanged(latLng)
            }
        }
    }

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
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = hasLocationPermission.value),
        uiSettings = MapUiSettings(myLocationButtonEnabled = true, mapToolbarEnabled = true),
        onMapClick = { latLng ->
            if (!isPositionSaved) {
                onMarkerPositionChanged(latLng)
            }
        }
    ) {
        markerPosition?.let { position ->
            Marker(
                state = MarkerState(position = position),
                title = stringResource(id = R.string.app_selected)
            )
        }
    }

    markerPosition?.let { position ->
        if (!isPositionSaved) {
            TertiaryButton(
                modifier = Modifier.padding(top = 16.dp),
                text = R.string.sales_form_property_save_location,
                onClick = { onSavePosition(position) }
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun AppMapPreview() {
    AppMap(
        context = LocalContext.current,
        markerPosition = null,
        onMarkerPositionChanged = {},
        onSavePosition = {},
        isPositionSaved = false
    )
}