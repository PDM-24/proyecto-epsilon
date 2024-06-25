package com.hnrylvo.inmomarket.ui.compose.headers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PersonPinCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.bars.AppSearchBar
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen

@Composable
fun TopSearchBar(
    query: String,
    onQueryChange: () -> Unit,
    onSearch: () -> Unit,
    active: Boolean,
    onActiveChange: () -> Unit,
    maxWidth: Float = 0.6f,
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = active,
            onActiveChange = onActiveChange,
            maxWidth = maxWidth,
        )
        IconButton(
            onClick = { onNotificationClick() },
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(id = R.string.app_notificationIcon),
                tint = SecondaryGreen,
                modifier = Modifier.size(30.dp)
            )
        }
        IconButton(
            onClick = { onProfileClick() },
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonPinCircle,
                contentDescription = stringResource(id = R.string.app_notificationIcon),
                tint = SecondaryGreen,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun TopBarHeaderPreview(){
    TopSearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {}
    )
}