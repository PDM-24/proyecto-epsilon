package com.hnrylvo.inmomarket.ux.property_view

object PropertyViewRoute {
    private const val PATH = "property_view"

    fun createRoute(propertyId : String) = "$PATH/$propertyId"

    val routeDefinition = "$PATH/{${Arg.PROPERTY_ID}}"

    object Arg {
        const val PROPERTY_ID = "property_id"
    }
}