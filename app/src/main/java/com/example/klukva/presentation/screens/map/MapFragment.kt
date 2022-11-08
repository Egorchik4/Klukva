package com.example.klukva.presentation.screens.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.klukva.R
import com.example.klukva.databinding.FragmentMapBinding
import com.example.klukva.domain.models.LocationModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment(), UserLocationObjectListener {

	lateinit var mapView: MapView
	lateinit var binding: FragmentMapBinding
	private val viewModelMapViewModel: MapViewModel by viewModels()

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
		//setAnchor()
		userLocationView.pin.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.user_arrow))
		userLocationView.arrow.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.user_arrow))
		userLocationView.accuracyCircle.fillColor = R.color.purple_500
	}

	override fun onObjectRemoved(p0: UserLocationView) {}

	override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {}

	private fun addPlaceMarks(list: MutableList<LocationModel>) {
		binding.mapViewYandex.map.mapObjects.addPlacemark(Point(56.456727, 84.955671),
														  ImageProvider.fromBitmap(drawBitmap()))

		//binding.mapViewYandex.map.mapObjects.addPlacemarks()
		list.forEach {
			binding.mapViewYandex.map.mapObjects.addPlacemark(
				it.point,
				ImageProvider.fromResource(this.context, R.drawable.point)
			)
		}
	}


	private fun drawBitmap(): Bitmap{
		var mTextBoundRect: Rect = Rect()
		var paintText = Paint()
		var bitmapPoint = BitmapFactory.decodeResource(resources,R.drawable.point)  // перевод изображения в bitmap
		var textName = "Молчание ягнят"
		var textTime = "C 9 до 21"
		var text = "4"
		// задаём параметры кисти для рисования текста (размер, цвет и т.д.)
		paintText.color = Color.BLACK
		paintText.isAntiAlias = true
		paintText.textSize = 30F
		paintText.textAlign = Paint.Align.LEFT

		// определяем ширину и высоту bitmap(холста) c учётом текста
		var textWidth1 = paintText.measureText(textName) // ширина 1 текста
		Log.e("eee","textWidth1: $textWidth1")
		var textWidth2 = paintText.measureText(textTime) // ширина 2 текста
		Log.e("eee","textWidth2: $textWidth2")
		var textWidth3 = paintText.measureText(text) // ширина 3 текста
		Log.e("eee","textWidth3: $textWidth3")

		// определение самого длинного текста, для формирования ширины холста!!!
		var textWidth =
			if(textWidth1 >= textWidth2){
				textWidth1
			}else{
				textWidth2
			}
		Log.e("eee","textWidth: $textWidth")

		paintText.getTextBounds(textName, 0, textName.length, mTextBoundRect)
		var textHeight1 = mTextBoundRect.height() // высота 1 текста
		Log.e("eee","textHeight1: $textHeight1")

		paintText.getTextBounds(textTime, 0, textTime.length, mTextBoundRect)
		var textHeight2 = mTextBoundRect.height() // высота 2 текста
		Log.e("eee","textHeight2: $textHeight2")

		paintText.getTextBounds(text, 0, text.length, mTextBoundRect)
		var textHeight3 = mTextBoundRect.height() // высота 3 текста
		Log.e("eee","textHeight3: $textHeight3")

		var textHeight = textHeight1+textHeight2 // общий отсуп сверху, для смещения картинки и формирования высоты холста!!!
		Log.e("eee","textHeight: $textHeight")

		// определяем что шире картика или текст и выбираем, что больше
		var picWidth:Int = if(bitmapPoint.width>=textWidth.toInt()){
			bitmapPoint.width
		}else{
			textWidth.toInt()
		}

		var picHeight:Int = (bitmapPoint.height+textHeight).toInt()  // высота картинки+сумма высот текстов
		Log.e("eee","bitmapPoint.width: ${bitmapPoint.width} bitmapPoint.height: ${bitmapPoint.height}")
		Log.e("eee","picWidth: $picWidth picHeight: $picHeight")

		var bitmap = Bitmap.createBitmap(picWidth, picHeight, Bitmap.Config.ARGB_8888) // создаём bitmap, на котором рисуем
		var canvas = Canvas(bitmap)


		var otstupLeft = (picWidth-bitmapPoint.width)/2 // отствуп слева
		var otstupTop = textHeight // отступ сверху, для текстов
		canvas.drawBitmap(bitmapPoint,otstupLeft.toFloat(),otstupTop.toFloat(),paintText)  //рисуем картинку

		var width = canvas.width
		var height = canvas.height
		var centerY = textHeight  // середина по высоте!!!


		var centerXName = width/2 - textWidth1/2  // середина по длине!!!
		canvas.drawText(textName,
						centerXName,
						textHeight1.toFloat(),
						paintText)

		var centerXTextTime = width/2 - textWidth2/2  // середина по длине!!!
		canvas.drawText(textTime,
						centerXTextTime,
						textHeight2.toFloat()+textHeight1.toFloat(),
						paintText)

		var centerXText = width/2 - textWidth3/2  // середина по длине!!!
		var otstup = 50F
		canvas.drawText(text,
						centerXText,
						textHeight3.toFloat()+textHeight2.toFloat()+textHeight1.toFloat()+otstup,
						paintText) // рисуем текст


		return bitmap
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

}