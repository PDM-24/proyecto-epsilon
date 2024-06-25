package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography

@Composable
fun MiniDropDownMenuWithTitle(
    title: Int,
    items: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = title),
            style = MyTypography.bodyMedium
        )
        MiniDropDownMenuInput(
            items = items,
            selected = selectedOption,
            onSelect = onOptionSelected
        )
    }
}

@Composable
@Preview
fun MiniDropDownMenuWithTitlePreview() {
    MiniDropDownMenuWithTitle(
        title = R.string.app_name,
        items = listOf("Option 1", "Option 2", "Option 3"),
        selectedOption = "Option 1",
        onOptionSelected = {}
    )
}