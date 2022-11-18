package com.example.klukva.presentation.screens.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.klukva.R
import com.example.klukva.databinding.FragmentMapBinding
import com.example.klukva.domain.models.LocationModel
import com.example.klukva.domain.usecases.CreateCustomPlaceMarkUseCase
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment(), UserLocationObjectListener, MapObjectTapListener {

	lateinit var mapView: MapView
	lateinit var binding: FragmentMapBinding
	private val viewModelMapViewModel: MapViewModel by viewModels()
	@Inject lateinit var drawCastomPlaceMarkUseCase: CreateCustomPlaceMarkUseCase

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMapBinding.inflate(inflater, container, false)
		mapView = binding.mapViewYandex
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		checkPermission()
		val mapKit: MapKit = MapKitFactory.getInstance()
		val userLocationLayer: UserLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
		userLocationLayer.isVisible = true
		userLocationLayer.setObjectListener(this)

		viewModelMapViewModel.dataMapPointLive.observe(viewLifecycleOwner) {
			addPlaceMarks(it)
		}

		binding.userLocationFab.setOnClickListener {
			val point: Point? = userLocationLayer.cameraPosition()?.target
			if (point != null) {
				moveCamera(point)
			}
		}

		mapKit.createLocationManager().requestSingleUpdate(object : LocationListener {
			override fun onLocationUpdated(location: Location) {
				moveCamera(location.position)
			}

			override fun onLocationStatusUpdated(locationStatus: LocationStatus) {}
		})
	}

	override fun onStart() {
		mapView.onStart()
		MapKitFactory.getInstance().onStart()
		super.onStart()
	}

	override fun onStop() {
		mapView.onStop()
		MapKitFactory.getInstance().onStop()
		super.onStop()
	}

	override fun onObjectAdded(userLocationView: UserLocationView) {
		userLocationView.pin.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.user_arrow))
		//userLocationView.arrow.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.user_arrow))
		userLocationView.accuracyCircle.fillColor = R.color.purple_500
	}

	override fun onObjectRemoved(p0: UserLocationView) {}

	override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {}

	private fun addPlaceMarks(list: MutableList<LocationModel>) {
		binding.mapViewYandex.map.mapObjects
		list.forEach {
			binding.mapViewYandex.map.mapObjects
				.addPlacemark(
					it.point,
					ImageProvider.fromBitmap(drawCastomPlaceMarkUseCase.drawBitmap(it.name,it.time,it.load,requireContext(),resources))
				).apply {
					addTapListener(this@MapFragment)
					userData = it.id
				}

		}
	}

	private fun moveCamera(point: Point) {
		mapView.map.move(
			CameraPosition(point, 18.0f, 0.0f, 0.0f),
			Animation(Animation.Type.SMOOTH, 1f), null
		)
	}

	private fun checkPermission() {
		if ((ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
				PackageManager.PERMISSION_GRANTED)
			|| (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
				PackageManager.PERMISSION_GRANTED)
		) {
			ActivityCompat.requestPermissions(
				requireActivity(), arrayOf(
					Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.ACCESS_COARSE_LOCATION
				), 0
			)
		}
	}

	override fun onMapObjectTap(mapObject: MapObject, point: Point): Boolean {
		moveCamera(point)
		return true
	}

}