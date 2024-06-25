package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.BackgroundGreen
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenuInput(
    placeHolderText: Int,
    items: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = selected,
            textStyle = MyTypography.labelSmall,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = BackgroundGreen,
                unfocusedIndicatorColor = BackgroundGreen,
                unfocusedContainerColor = White
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .menuAnchor()
                .border(
                    width = 1.dp,
                    color = LightGreen,
                    RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = placeHolderText),
                    style = MyTypography.labelSmall
                )
            }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(
                        text = item,
                        style = MyTypography.labelSmall,
                    ) },
                    onClick = {
                        onSelect(item)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppDropDownMenuPreview() {
    AppDropDownMenuInput(
        placeHolderText = R.string.app_select_an_option,
        items = listOf(
            stringResource(id = R.string.sales_form_property_type_house),
            stringResource(id = R.string.sales_form_property_type_beach_house),
            stringResource(id = R.string.sales_form_property_type_apartment),
            stringResource(id = R.string.sales_form_property_type_land)
        ),
        selected = "",
        onSelect = {}
    )
}