package com.hnrylvo.inmomarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hnrylvo.immomarketapp.ux.login.LoginRoute
import com.hnrylvo.immomarketapp.ux.login.LoginScreen
import com.hnrylvo.inmomarket.data.preferences.AuthPreferencesManager
import com.hnrylvo.inmomarket.ux.home.HomeRoute
import com.hnrylvo.inmomarket.ux.home.HomeScreen
import com.hnrylvo.inmomarket.ux.property_view.PropertyViewRoute
import com.hnrylvo.inmomarket.ux.property_view.PropertyViewScreen
import com.hnrylvo.inmomarket.ux.register.RegisterRoute
import com.hnrylvo.inmomarket.ux.register.RegisterScreen
import com.hnrylvo.inmomarket.ux.sales_form.SalesFormRoute
import com.hnrylvo.inmomarket.ux.sales_form.SalesFormScreen
import com.hnrylvo.inmomarket.ux.sidebar.SideBarRoute
import com.hnrylvo.inmomarket.ux.sidebar.SideBarScreen

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val authPreferencesManager = AuthPreferencesManager(context)
    val token = authPreferencesManager.getAuthToken().collectAsState(initial = "")
    val startRoute = if (token.value.isNullOrEmpty()) {
        LoginRoute.route
    } else {
        HomeRoute.route
    }

    val navController = rememberNavController()
    NavHost(navController, startDestination = startRoute) {
        composable(LoginRoute.route) {
            LoginScreen(navController)
        }
        composable(RegisterRoute.route) {
            RegisterScreen(navController)
        }
        composable(HomeRoute.route) {
            HomeScreen(navController)
        }
        composable(SideBarRoute.route) {
            SideBarScreen(navController)
        }
        composable(SalesFormRoute.route) {
            SalesFormScreen(navController)
        }
        composable(
            route = PropertyViewRoute.routeDefinition,
            arguments = listOf(
                navArgument(PropertyViewRoute.Arg.PROPERTY_ID) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            val propertyId = it.arguments?.getString(PropertyViewRoute.Arg.PROPERTY_ID) ?: ""
            PropertyViewScreen(navController, propertyId)
        }
    }
}