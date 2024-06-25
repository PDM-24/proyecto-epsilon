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
fun MiniInputFieldWithTitle(
    title: String,
    value: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    icon: Int,
    suffix: String = ""
) {
    Column {
        Text(
            text = title,
            style = MyTypography.bodyMedium
        )

        MiniInputField(
            value = value,
            keyboardType = keyboardType,
            onValueChange = onValueChange,
            icon = icon,
            iconDescription = title,
            suffix = suffix
        )
    }
}

@Composable
@Preview
fun MiniInputFieldWithTitlePreview() {
    MiniInputFieldWithTitle(
        title = stringResource(id = R.string.app_name),
        value = "",
        keyboardType = KeyboardType.Text,
        onValueChange = {},
        icon = R.drawable.ic_ruler
    )
}
