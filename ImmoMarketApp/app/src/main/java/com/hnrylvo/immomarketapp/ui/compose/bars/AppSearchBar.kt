package com.hnrylvo.inmomarket.ui.compose.bars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGray
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    query: String,
    onQueryChange: () -> Unit,
    onSearch: () -> Unit,
    active: Boolean,
    onActiveChange: () -> Unit,
    maxWidth: Float = 1f,
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .height(48.dp),
        query = query,
        onQueryChange = {
            onQueryChange()
        },
        onSearch = {
            onSearch()
        },
        active = active,
        onActiveChange = {
            onActiveChange()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.app_searchIcon),
                tint = SecondaryGreen
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.app_search),
                style = MyTypography.labelSmall,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.app_searchIcon),
                tint = SecondaryGreen
            )
        },
        colors = SearchBarDefaults.colors(LightGray)
    ) {

    }
}

@Composable
@Preview(showSystemUi = true)
fun AppSearchBarPreview() {
    AppSearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {}
    )
}