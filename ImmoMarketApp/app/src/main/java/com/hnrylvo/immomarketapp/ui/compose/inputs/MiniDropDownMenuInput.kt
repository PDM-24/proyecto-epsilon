package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
fun MiniDropDownMenuInput(
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
            textStyle = MyTypography.bodySmall,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
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
                .width(110.dp)
                .height(45.dp)
                .menuAnchor()
                .border(
                    width = 1.dp,
                    color = LightGreen,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
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
fun MiniDropDownMenuPreview() {
    MiniDropDownMenuInput(
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