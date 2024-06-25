package com.hnrylvo.inmomarket.ui.compose.pickers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.theme.LightGreen
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.theme.White

@Composable
fun PhotoPicker(
    onClick: () -> Unit
) {
    Column {
        Button(
            modifier = Modifier
                .width(250.dp)
                .height(200.dp),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = White,
                contentColor = SecondaryGreen
            ),
            border = BorderStroke(1.dp, LightGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.sales_form_property_photos_add),
                    style = MyTypography.bodyMedium,
                    color = SecondaryGreen
                )
            }
        }
        Text(
            text = stringResource(id = R.string.sales_form_property_photos_warning),
            style = MyTypography.bodySmall,
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PhotoPickerPreview() {
    PhotoPicker(
        onClick = {}
    )
}