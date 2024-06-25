package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography

@Composable
fun DropDownMenuWithTitle(
    title: Int,
    placeHolderText: Int,
    items: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = title),
            style = MyTypography.bodyLarge
        )
        AppDropDownMenuInput(
            placeHolderText = placeHolderText,
            items = items,
            selected = selectedOption,
            onSelect = { selected ->
                onOptionSelected(selected)
            }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun DropDownMenuWithTitlePreview() {
    DropDownMenuWithTitle(
        title = R.string.sales_form_property_types,
        placeHolderText = R.string.app_select_an_option,
        items = listOf(
            stringResource(id = R.string.sales_form_property_type_house),
            stringResource(id = R.string.sales_form_property_type_apartment),
            stringResource(id = R.string.sales_form_property_type_beach_house),
            stringResource(id = R.string.sales_form_property_type_land)
        ),
        selectedOption = "hola",
        onOptionSelected = {}
    )
}
