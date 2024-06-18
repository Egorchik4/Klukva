package com.example.feature.map.domain.usecase

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.example.component.R
import javax.inject.Inject

class CreateCustomPlaceMarkUseCase @Inject constructor(var paintText: Paint, var mTextBoundRect: Rect, var canvas: Canvas) {


	fun drawBitmap(textName: String, textTime: String, textLoad: String, context: Context,resources: Resources): Bitmap {
		val bitmapPoint = BitmapFactory.decodeResource(context.resources, R.drawable.point)

		paintText.color = ContextCompat.getColor(context,R.color.main)
		paintText.isAntiAlias = true
		paintText.textSize = resources.getDimension(R.dimen.myFontSize)
		paintText.textAlign = Paint.Align.LEFT

		val textWidth1 = paintText.measureText(textName)
		val textWidth2 = paintText.measureText(textTime)
		val textWidth3 = paintText.measureText(textLoad)

		val textWidth =
			if(textWidth1 >= textWidth2){
				textWidth1
			}else{
				textWidth2
			}

		paintText.getTextBounds(textName, 0, textName.length, mTextBoundRect)
		val textHeight1 = mTextBoundRect.height()

		paintText.getTextBounds(textTime, 0, textTime.length, mTextBoundRect)
		val textHeight2 = mTextBoundRect.height()

		paintText.getTextBounds(textLoad, 0, textLoad.length, mTextBoundRect)
		val textHeight3 = mTextBoundRect.height()

		val textHeight = textHeight1+textHeight2

		val picWidth:Int = if(bitmapPoint.width>=textWidth.toInt()){
			bitmapPoint.width
		}else{
			textWidth.toInt()
		}

		val picHeight:Int = (bitmapPoint.height + textHeight)
		val bitmap = Bitmap.createBitmap(picWidth, picHeight, Bitmap.Config.ARGB_8888)
		canvas.setBitmap(bitmap)


		val otstupLeft = (picWidth - bitmapPoint.width)/2
		val otstupTop = textHeight
		canvas.drawBitmap(bitmapPoint,otstupLeft.toFloat(),otstupTop.toFloat(),paintText)

		val width = canvas.width

		val centerXName = width/2 - textWidth1/2
		canvas.drawText(textName,
						centerXName,
						textHeight1.toFloat(),
						paintText)

		val centerXTextTime = width/2 - textWidth2/2
		canvas.drawText(textTime,
						centerXTextTime,
						textHeight2.toFloat()+textHeight1.toFloat(),
						paintText)

		val centerXText = width/2 - textWidth3/2
		val indent = resources.getDimension(R.dimen.myIndent)
		canvas.drawText(textLoad,
						centerXText,
						textHeight3.toFloat()+textHeight2.toFloat()+textHeight1.toFloat()+indent,
						paintText)
		return bitmap
	}
}