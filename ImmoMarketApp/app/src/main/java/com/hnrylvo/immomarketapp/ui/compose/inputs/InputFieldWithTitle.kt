package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography

@Composable
fun InputFieldWithTitle(
    title: Int,
    value: String,
    placeholderId: Int,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    maxWidth : Float = 1f
) {
    Column {
        Text(
            text = stringResource(id = title),
            style = MyTypography.bodyLarge
        )
        InputField(
            value = value,
            placeholderId = placeholderId,
            keyboardType = keyboardType,
            onValueChange = onValueChange,
            maxWidth = maxWidth
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun InputFieldWithTitlePreview() {
    InputFieldWithTitle(
        title = R.string.sales_form_property_department,
        value = "",
        placeholderId = R.string.sales_form_property_department_input,
        keyboardType = KeyboardType.Text,
        onValueChange = {}
    )
}
