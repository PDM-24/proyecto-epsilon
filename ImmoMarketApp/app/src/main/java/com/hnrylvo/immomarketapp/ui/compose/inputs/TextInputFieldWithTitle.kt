package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography

@Composable
fun TextInputFieldWithTitle(
    title: Int,
    value: String,
    placeholderId: Int,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    maxWidth : Float = 0.8f
) {
    Column {
        Text(
            text = stringResource(id = title),
            style = MyTypography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextInputField(
            value = value,
            placeholderId = placeholderId,
            keyboardType = keyboardType,
            onValueChange = onValueChange,
            maxWidth = maxWidth
        )
    }
}

@Composable
@Preview
fun TextInputFieldWithTitlePreview() {
    TextInputFieldWithTitle(
        title = R.string.app_name,
        value = "",
        placeholderId = R.string.sales_form_property_details,
        keyboardType = KeyboardType.Text,
        onValueChange = {}
    )
}