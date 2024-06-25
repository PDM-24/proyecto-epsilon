package com.hnrylvo.inmomarket.ux.sidebar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.inmomarket.ui.compose.buttons.PrimaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.headers.GoBackWithTitleHeader
import com.hnrylvo.inmomarket.ui.compose.icontexts.ProfileSection
import com.hnrylvo.inmomarket.ui.compose.icontexts.TextWithIcon
import com.hnrylvo.inmomarket.ux.sales_form.SalesFormRoute

@Composable
fun SideBarScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = SideBarViewModel(context)
    AppScaffold(
        topBar = { TopBar(navController) }
    ) {
        SideBar(navController, viewModel)
    }
}

@Composable
fun TopBar(navController: NavController) {
    GoBackWithTitleHeader(
        title = R.string.app_name,
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun SideBar(navController: NavController, viewModel: SideBarViewModel) {
    Column {
        ProfileInfo()
        Spacer(modifier = Modifier.height(16.dp))
        ContactedProperties()
        Spacer(modifier = Modifier.height(16.dp))
        FavoriteProperties()
        Spacer(modifier = Modifier.height(16.dp))
        MyProperties()
        Spacer(modifier = Modifier.height(16.dp))
        BuyProperties()
        Spacer(modifier = Modifier.height(36.dp))
        SellPropertiesButton(
            navController = navController
        )
        Spacer(modifier = Modifier.height(36.dp))
        Help()
        Spacer(modifier = Modifier.height(16.dp))
        Logout(viewModel)
    }
}

@Composable
fun Logout(
    viewModel: SideBarViewModel
) {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_logout),
        icon = R.drawable.ic_logout,
        //onClick = { viewModel.logOut() }
    )
}

@Composable
fun Help() {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_help),
        icon = R.drawable.ic_help
    )
}

@Composable
fun SellPropertiesButton(
    navController: NavController
) {
    PrimaryButton(
        buttonText = R.string.sidebar_sell_properties,
        onClick = { navController.navigate(route = SalesFormRoute.route) },
        enabled = true
    )
}

@Composable
fun BuyProperties() {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_buy_properties),
        icon = R.drawable.ic_buy
    )
}

@Composable
fun MyProperties() {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_my_properties),
        icon = R.drawable.ic_my_properties
    )
}

@Composable
fun FavoriteProperties() {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_favorite_properties),
        icon = R.drawable.ic_favorite
    )
}

@Composable
fun ContactedProperties() {
    TextWithIcon(
        text = stringResource(id = R.string.sidebar_contacted_properties),
        icon = R.drawable.ic_request
    )
}

@Composable
fun ProfileInfo() {
    HorizontalDivider()
    ProfileSection(
        name = "Henry Lovo",
        otherInfo = "henrylovo90@gmail.com"
    )
    HorizontalDivider()
}

@Composable
@Preview
fun SideBarScreenPreview() {
    SideBarScreen(
        navController = NavController(LocalContext.current)
    )
}