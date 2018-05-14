package com.undabot.jobfair.booths.entities

import com.google.android.gms.maps.model.LatLng

class MapConstants {
    companion object {
        val START_LOCATION = LatLng(45.8011031, 15.9710031)
        val OVERLAY_SOUTH_WEST_LOCATION = LatLng(45.800015, 15.970431)
        val OVERLAY_NORTH_EAST_LOCATION = LatLng(45.801461, 15.97194)
        val MIN_ZOOM_PREFERENCE = 20f
        val MAX_ZOOM_PREFERENCE = 21f
        val CAMERA_ROTATION = 90f
        val RESTRICTION_SOUTH_WEST_LOCATION = LatLng(45.8001, 15.9705)
        val RESTRICTION_NORTH_EAST_LOCATION = LatLng(45.8015, 15.97155)
    }
}