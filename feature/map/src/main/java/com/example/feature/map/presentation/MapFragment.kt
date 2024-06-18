package com.example.feature.map.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.component.constants.Constant
import com.example.component.R
import com.example.feature.map.data.model.LocationModel
import com.example.feature.map.databinding.FragmentMapBinding
import com.example.feature.map.domain.usecase.CreateCustomPlaceMarkUseCase
import com.example.feature.map.presentation.viewpager.ViewPagerAdapter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import javax.inject.Inject

class MapFragment : Fragment(), UserLocationObjectListener, MapObjectTapListener, InputListener {

	private lateinit var mapView: MapView
	private lateinit var binding: FragmentMapBinding
	private val viewModelMapViewModel: MapViewModel by viewModels()

	@Inject
	lateinit var drawCustomPlaceMarkUseCase: CreateCustomPlaceMarkUseCase
	private val adapter = ViewPagerAdapter()
	private var selectPlaceMarkID: Int? = null
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
		mapView.map.addInputListener(this)

		val viewPager = binding.pager
		viewPager.adapter = adapter

		binding.imMenu.setOnClickListener {
			//findNavController().navigate(R.id.action_mapFragment_to_profileFragment)
		}

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


		binding.buttonFind.setOnClickListener {
			if (selectPlaceMarkID != null) {
				val bundle = Bundle()
				val cafeName = viewModelMapViewModel.getModelFromId(selectPlaceMarkID!!)
				bundle.putString(Constant.NAME_OF_CAFE, cafeName.name)
				parentFragmentManager.setFragmentResult(Constant.PLACEMARK_INFO, bundle)
				//findNavController().navigate(R.id.action_mapFragment_to_mapOfCafeFragment)
			}
		}
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
		userLocationView.accuracyCircle.fillColor = R.color.purple_500
	}

	override fun onObjectRemoved(p0: UserLocationView) {}

	override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {}

	private fun addPlaceMarks(list: MutableList<LocationModel>) {
		list.forEach {
			binding.mapViewYandex.map.mapObjects
				.addPlacemark(
					it.location,
					ImageProvider.fromBitmap(drawCustomPlaceMarkUseCase.drawBitmap(it.name, it.open_time, it.load, requireContext(), resources))
				).apply {
					addTapListener(this@MapFragment)
					userData = it
				}

		}
	}

	private fun moveCamera(point: Point) {
		val newPoint = Point(point.latitude - 0.0003, point.longitude)
		mapView.map.move(
			CameraPosition(newPoint, 18.0f, 0.0f, 0.0f),
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
		binding.pager.visibility = View.VISIBLE
		val id = (mapObject.userData as LocationModel).id
		selectPlaceMarkID = id
		val model = viewModelMapViewModel.getModelFromId(id)
		adapter.updateData(model)
		moveCamera(model.location)
		return true
	}

	override fun onMapTap(p0: Map, p1: Point) {
		binding.pager.visibility = View.GONE
		selectPlaceMarkID = null
	}

	override fun onMapLongTap(p0: Map, p1: Point) {

	}
}

