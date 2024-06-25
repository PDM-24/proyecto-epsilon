package com.hnrylvo.inmomarket.ui.compose.icontexts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen

@Composable
fun ProfileSection(
    name: String,
    otherInfo: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = ""
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = name,
                style = MyTypography.bodyLarge,
                color = SecondaryGreen
            )
            Text(
                text = otherInfo,
                style = MyTypography.bodySmall
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ProfilePreview() {
    ProfileSection(
        name = "John Doe",
        otherInfo = "JohnDoe@gmail.com"
    )
}
