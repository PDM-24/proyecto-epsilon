package com.hnrylvo.inmomarket.ui.compose.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGreen
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.immomarketapp.ui.theme.White

@Composable
fun InputField(
    value: String,
    placeholderId: Int,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    maxWidth: Float = 1f,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .height(50.dp)
            .border(color = LightGreen, width = 1.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
        placeholder = {
            Text(
                text = stringResource(id = placeholderId),
                color = SecondaryGreen,
                style = MyTypography.labelSmall
            )
        },
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
    )
}

@Composable
@Preview
fun InputFieldPreview() {
    InputField(
        value = "",
        placeholderId = R.string.login_email,
        keyboardType = KeyboardType.Email,
        onValueChange = {}
    )
}