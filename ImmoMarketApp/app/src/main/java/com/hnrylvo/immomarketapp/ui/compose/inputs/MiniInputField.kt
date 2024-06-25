package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.immomarketapp.ui.theme.White

@Composable
fun MiniInputField(
    value: String,
    placeholderId: String = "",
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    icon: Int,
    iconDescription: String = "",
    suffix: String = ""
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(110.dp)
            .height(45.dp)
            .border(color = LightGreen, width = 1.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp)),
        placeholder = {
            Text(
                text = placeholderId,
                color = SecondaryGreen,
                style = MyTypography.bodySmall
            )
        },
        suffix = {
            Text(
                text = suffix,
                color = SecondaryGreen,
                style = MyTypography.bodySmall,
                textAlign = TextAlign.Center
            )
        },
        textStyle = MyTypography.bodySmall,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = icon),
                contentDescription = iconDescription
            )
        }
    )
}

@Composable
@Preview(showSystemUi = true)
fun MiniInputFieldPreview() {
    MiniInputField(
        value = "dfgfdgdfg",
        keyboardType = KeyboardType.Email,
        onValueChange = {},
        icon = R.drawable.ic_ruler,
    )
}