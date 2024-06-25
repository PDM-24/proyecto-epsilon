package com.hnrylvo.inmomarket.ux.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.inmomarket.data.networking.model.response.GetAllPropertyResponse
import com.hnrylvo.inmomarket.ui.compose.cards.HorizontalCard
import com.hnrylvo.inmomarket.ui.compose.cards.PrimaryCard
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.headers.TopSearchBar
import com.hnrylvo.inmomarket.ux.sidebar.SideBarRoute

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = remember {
        HomeViewModel(navController = navController, context = context)
    }

    LaunchedEffect(Unit) {
        viewModel.getProperties()
    }

    AppScaffold(
        topBar = { TopBar(navController) }
    ) {
        Home(viewModel)
    }
}

@Composable
fun TopBar(navController: NavController) {
    TopSearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        maxWidth = 0.6f,
        onProfileClick = {
            navController.navigate(route = SideBarRoute.route)
        }
    )
}

@Composable
fun Home(homeViewModel: HomeViewModel) {

    val propertyList by homeViewModel.propertyList.collectAsState()
    val isLoading by homeViewModel.isLoading

    Log.d("HomeScreen", "Property List: $propertyList")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            TodaysPicks(
                viewModel = homeViewModel,
                propertyList = propertyList
            )
            Spacer(modifier = Modifier.height(36.dp))
            AllPosts(
                propertyList = propertyList,
                viewModel = homeViewModel
            )
        }
    }
}

@Composable
fun AllPosts(
    propertyList : List<GetAllPropertyResponse>,
    viewModel: HomeViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.home_all),
            style = MyTypography.titleSmall,
        )
        Text(
            text = stringResource(id = R.string.home_more),
            style = MyTypography.labelSmall,
        )
    }
    Spacer(modifier = Modifier.height(26.dp))
    propertyList.forEach { property ->
        HorizontalCard(
            imageUrl = "https://www.bankrate.com/2022/07/20093642/what-is-house-poor.jpg?auto=webp&optimize=high&crop=16:9&width=912",
            productPrice = property.propertyPrice,
            productType = property.propertyType,
            size = property.propertySize,
            habitations = property.propertyBedrooms,
            bathrooms = property.propertyBathrooms,
            municipality = property.municipality,
            department = property.department,
            onClick = {viewModel.navigateToDetail(id = property.propertyId)}
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TodaysPicks(
    viewModel: HomeViewModel,
    propertyList: List<GetAllPropertyResponse>
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.home_todays_picks),
                style = MyTypography.titleSmall,
            )
            Text(
                text = stringResource(id = R.string.home_more),
                style = MyTypography.labelSmall,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        )
        {
            propertyList.take(5).forEach { property ->
                item {
                    PrimaryCard(
                        productType = property.propertyType,
                        productPrice = property.propertyPrice,
                        neighborhood = property.neighborhood,
                        municipality = property.municipality,
                        department = property.department,
                        imageUrl = "https://res.cloudinary.com/sentral/image/upload/w_1000,h_1000,q_auto:eco,c_fill/f_auto/v1684782440/miro_hero_building_exterior_2000x1125.jpg",
                        onClick = { viewModel.navigateToDetail(id = property.propertyId) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}