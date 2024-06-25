package com.hnrylvo.inmomarket.ui.compose.forms

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.pickers.PhotoPicker

@Composable
fun PropertyPhotosForm(
    selectedImageUris: List<Uri> = emptyList(),
    setSelectedImageUris: (List<Uri>) -> Unit = {}
) {

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->  setSelectedImageUris(uris) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_photos_title),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PropertyPhotosPicker(
            multiplePhotoPickerLauncher = multiplePhotoPickerLauncher
        )
        Spacer(modifier = Modifier.padding(26.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(26.dp))
        PropertyPhotosPreview(
            selectedImageUris = selectedImageUris
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun PropertyPhotosPreview(
    selectedImageUris: List<Uri> = emptyList()
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sales_form_property_post_photos)
        )
        if (selectedImageUris.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.sales_form_property_no_photos),
                style = MyTypography.bodyMedium
            )
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(selectedImageUris) { uri ->
                    AsyncImage(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(150.dp)
                            .border(1.dp, LightGreen, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp)),
                        model = uri.toString(),
                        contentDescription = stringResource(id = R.string.app_image_description),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyPhotosPicker(
    multiplePhotoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
) {
    PhotoPicker(
        onClick = {
            multiplePhotoPickerLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
    )
}

@Composable
@Preview(showSystemUi = true)
fun PropertyPhotosFormPreview() {
    PropertyPhotosForm()
}