package com.undabot.jobfair.booths.view

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.undabot.jobfair.BuildConfig
import com.undabot.jobfair.R
import com.undabot.jobfair.booths.di.BoothsModule
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.booths.entities.MapConstants.Companion.CAMERA_ROTATION
import com.undabot.jobfair.booths.entities.MapConstants.Companion.MAX_ZOOM_PREFERENCE
import com.undabot.jobfair.booths.entities.MapConstants.Companion.MIN_ZOOM_PREFERENCE
import com.undabot.jobfair.booths.entities.MapConstants.Companion.OVERLAY_NORTH_EAST_LOCATION
import com.undabot.jobfair.booths.entities.MapConstants.Companion.OVERLAY_SOUTH_WEST_LOCATION
import com.undabot.jobfair.booths.entities.MapConstants.Companion.RESTRICTION_NORTH_EAST_LOCATION
import com.undabot.jobfair.booths.entities.MapConstants.Companion.RESTRICTION_SOUTH_WEST_LOCATION
import com.undabot.jobfair.booths.entities.MapConstants.Companion.START_LOCATION
import com.undabot.jobfair.booths.view.models.BoothViewModel
import com.undabot.jobfair.booths.view.models.BoothsViewModel
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.utils.GlideApp
import kotlinx.android.synthetic.main.booths_screen.*
import javax.inject.Inject

class BoothsScreen : BaseFragment(), BoothsContract.View, OnMapReadyCallback {

    companion object {
        private val EXTRA_BOOTH_LOCATION = "extra_booth_location"

        fun newInstance(boothLocation: LocationInfo): BoothsScreen =
                BoothsScreen().apply {
                    arguments = Bundle().apply {
                        putParcelable(EXTRA_BOOTH_LOCATION, boothLocation)
                    }
                }
    }

    @Inject
    lateinit var coordinator: BoothsContract.Coordinator

    private lateinit var map: GoogleMap
    private var markerPixelSize: Int = 0
    private lateinit var markerPlaceholder: BitmapDescriptor

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        markerPixelSize = resources.getDimensionPixelSize(R.dimen.booth_marker_size)
        coordinator.bind(this)
        createMarkerPlaceholder()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.booths_screen, container, false)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun showGeneralError() {
        hideLoading()
        showGeneralErrorMessage()
    }

    override fun onDestroyView() {
        map.clear()
        super.onDestroyView()
    }

    override fun showBoothsWith(boothsViewModel: BoothsViewModel) {
        boothsViewModel.list.forEach { addBoothOnMap(it) }
        hideLoading()
    }

    override fun showCompanyDetails(company: Company) {
        CompanyDetailsContainerScreen.startWith(
                context!!, arrayListOf(CompanyViewModel(company.id, company.image.thumb, company.name)))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        setupMap(googleMap)
        coordinator.onMapReady()
    }

    private fun addBoothOnMap(booth: BoothViewModel) {
        val boothMarker = map.addMarker(
                MarkerOptions()
                        .position(latLngFrom(booth))
                        .icon(markerPlaceholder)
                        .anchor(0.5f, 0.5f)
                        .title(booth.title)
                        .snippet(booth.snippet))
        boothMarker.tag = booth.company

        GlideApp.with(context!!)
                .asBitmap()
                .apply(RequestOptions().circleCrop())
                .load(booth.company.image.thumb)
                .into(object : SimpleTarget<Bitmap>(markerPixelSize, markerPixelSize) {
                    override fun onResourceReady(resource: Bitmap,
                                                 transition: Transition<in Bitmap>?) {
                        boothMarker.setIcon(BitmapDescriptorFactory.fromBitmap(resource))
                    }
                })
        if (hasSpecificBooth()) {
            focusOnMarker(booth, boothMarker)
        }
    }

    private fun hasSpecificBooth() = arguments != null && arguments!![EXTRA_BOOTH_LOCATION] != null

    private fun focusOnMarker(booth: BoothViewModel, boothMarker: Marker) {
        val locationInfo: LocationInfo = arguments?.getParcelable(EXTRA_BOOTH_LOCATION)!!
        if (locationInfo.geolocation == booth.location) {
            boothMarker.showInfoWindow()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder()
                    .target(LatLng(locationInfo.geolocation.latitude, locationInfo.geolocation.longitude))
                    .bearing(CAMERA_ROTATION)
                    .zoom(MAX_ZOOM_PREFERENCE)
                    .build()))
        }
    }

    private fun setupMap(googleMap: GoogleMap) {
        map = googleMap
        setupMapStyle()

        val mapOverlay = GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.floor_overlay))
                .positionFromBounds(overlayBounds())
        mapOverlay.clickable(false)
        map.addGroundOverlay(mapOverlay)
        map.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.Builder()
                .target(START_LOCATION)
                .bearing(CAMERA_ROTATION)
                .zoom(MIN_ZOOM_PREFERENCE)
                .build()))
        map.setMinZoomPreference(MIN_ZOOM_PREFERENCE)
        map.setMaxZoomPreference(MAX_ZOOM_PREFERENCE)
        map.setOnInfoWindowClickListener { handleMarkerInfoClick(it) }
        map.isBuildingsEnabled = false
        map.uiSettings.isRotateGesturesEnabled = false
        map.setLatLngBoundsForCameraTarget(
                LatLngBounds(RESTRICTION_SOUTH_WEST_LOCATION,
                        RESTRICTION_NORTH_EAST_LOCATION))
        showDebugMarker()
    }

    private fun setupMapStyle() {
        try {
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))
        } catch (e: Resources.NotFoundException) {
            Log.e("BoothsScreen", e.message, e)
        }
        map.uiSettings.isCompassEnabled = false
    }

    private fun overlayBounds() = LatLngBounds(
            OVERLAY_SOUTH_WEST_LOCATION,
            OVERLAY_NORTH_EAST_LOCATION)

    private fun handleMarkerInfoClick(marker: Marker) {
        if (marker.tag is Company) {
            coordinator.onCompanyPressed(marker.tag as Company)
        }
    }

    private fun latLngFrom(booth: BoothViewModel) =
            LatLng(booth.location.latitude, booth.location.longitude)

    private fun createMarkerPlaceholder() {
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.ic_placeholder_booth_marker)!!
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        markerPlaceholder = BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun showDebugMarker() {
        if (!BuildConfig.DEBUG) {
            return
        }
        val ferMarker = map.addMarker(MarkerOptions()
                .position(START_LOCATION)
                .title(START_LOCATION.toString()))

        map.setOnCameraMoveListener {
            val position = map.cameraPosition.target
            ferMarker.position = position
            ferMarker.title = "$position"
            ferMarker.snippet = "zoom:${map.cameraPosition.zoom}"
            if (ferMarker.isInfoWindowShown) ferMarker.showInfoWindow()
        }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BoothsModule()).inject(this)
    }
}