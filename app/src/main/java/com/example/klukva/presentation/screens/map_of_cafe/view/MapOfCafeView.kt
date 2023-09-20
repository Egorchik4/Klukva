package com.example.klukva.presentation.screens.map_of_cafe.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.klukva.R
import com.example.klukva.domain.models.CoordinateTableInCafeModel
import com.example.klukva.domain.models.ScaleOfCanvasModel

class MapOfCafeView : View {

	private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private var list: List<CoordinateTableInCafeModel> = listOf(
		CoordinateTableInCafeModel(20F, 0F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(20F, 400F, 0F, 0F, ContextCompat.getColor(context, R.color.place_color_reserved_table)),
		CoordinateTableInCafeModel(20F, 800F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(20F, 1200F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(220F, 0F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(220F, 400F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(220F, 800F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(400F, 0F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(400F, 400F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(400F, 800F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(620F, 0F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(620F, 400F, 0F, 0F, ContextCompat.getColor(context, R.color.main)),
		CoordinateTableInCafeModel(620F, 800F, 0F, 0F, ContextCompat.getColor(context, R.color.main))
	)

	constructor(context: Context?) : super(context) {}
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

	//var myCanvas: Canvas? = null
	var widthPointOfAllImages = 0F
	var heightPointOfAllImages = 0F

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		// можно достать сколько предоставлено пространства width и height
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		val widthMode = MeasureSpec.getMode(widthMeasureSpec)
		var width = MeasureSpec.getSize(widthMeasureSpec)  //предоставленные размеры view = canvas.width
		val heightMode = MeasureSpec.getMode(heightMeasureSpec)
		var height = MeasureSpec.getSize(heightMeasureSpec) //предоставленные размеры view = canvas.height
		setMeasuredDimension(width, height)
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		Log.e("eee", "Before ${canvas.height}")
		Log.e("eee", "Before ${canvas.width}")
		//canvas.scale(0.5F,0.5F) // для рисования, не работает как надо
		Log.e("eee", "After ${canvas.height}")
		Log.e("eee", "After ${canvas.width}")
		drawFourPersonPlace(list, canvas)
	}

	fun drawTwoPersonPlace() {


	}

	private fun drawFourPersonPlace(list: List<CoordinateTableInCafeModel>, canvas: Canvas?) {
		if (canvas != null) {
			paint.style = Paint.Style.FILL
			for (i in list.indices) {
				paint.color = list[i].color
				canvas.drawCircle(30F + list[i].startX, 20F + list[i].startY, 20F, paint)
				canvas.drawCircle(90F + list[i].startX, 20F + list[i].startY, 20F, paint)

				// создаём пустой прямоугольник и задаём координаты верхней левой и нижней правой точек
				// x,y,x,y
				canvas.drawRect(0F + list[i].startX, 50F + list[i].startY, 120F + list[i].startX, 130F + list[i].startY, paint)

				canvas.drawCircle(30F + list[i].startX, 160F + list[i].startY, 20F, paint)
				canvas.drawCircle(90F + list[i].startX, 160F + list[i].startY, 20F, paint)

				list[i].endX = 120F + list[i].startX
				list[i].endY = 160F + list[i].startY + 20F

				// левая врхняя точка
				Log.e("eee", "list[i].startX:${list[i].startX} list[i].startY:${list[i].startY}")

				// правая нижняя точка
				Log.e("eee", "list[i].endX:${list[i].endX} list[i].endY:${list[i].endY}")
				if (widthPointOfAllImages < list[i].endX) {
					widthPointOfAllImages = list[i].endX  // находим самую большую длину(x)
				}
				if (heightPointOfAllImages < list[i].endY) {
					heightPointOfAllImages = list[i].endY // находим самую большую высоту(y)
				}
			}
			//val scale = setScale(canvas.width,canvas.height, widthPointOfAllImages, heightPointOfAllImages)  // установка масштаба
			//canvas.scale(1/scale.scaleX,1/scale.scaleY)
			//myCanvas?.scale(0.5F,0.5F)
		}
	}

	override fun onTouchEvent(event: MotionEvent): Boolean {
		return when (event.action) {
			MotionEvent.ACTION_DOWN -> {
				listenTouchCoordinate(event.x, event.y)
				true
			}

			MotionEvent.ACTION_MOVE -> true
			MotionEvent.ACTION_UP   -> true
			else                    -> super.onTouchEvent(event)
		}
	}

	private fun listenTouchCoordinate(x: Float, y: Float) {
		// учитывать масштаб!!!
		for (i in list.indices) {
			if (list[i].startX <= x && list[i].startY <= y
				&& list[i].endX >= x && list[i].endY >= y
			) {
				if (list[i].color == ContextCompat.getColor(context, R.color.main)) {
					list[i].color = ContextCompat.getColor(context, R.color.place_color_reserved_table)
				} else {
					list[i].color = ContextCompat.getColor(context, R.color.main)
				}
			}
		}
		invalidate()
	}

	private fun setScale(enableX: Int, enableY: Int, contentX: Float, contentY: Float): ScaleOfCanvasModel {
		Log.e("eee", "enableX ${enableX}")
		Log.e("eee", "contentX ${contentX}")
		Log.e("eee", "enableY ${enableY}")
		Log.e("eee", "contentY ${contentY}")

		var scaleX = 0F
		var scaleY = 0F
		if (contentX > enableX || contentY > enableY) {
			scaleX = contentX / enableX.toFloat()
			scaleY = contentY / enableY.toFloat()
		}
		Log.e("eee", "scaleX ${scaleX}")
		Log.e("eee", "scaleY ${scaleY}")
		return ScaleOfCanvasModel(scaleX = scaleX, scaleY = scaleY)
	}


}