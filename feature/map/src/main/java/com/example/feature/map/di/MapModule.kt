package com.example.feature.map.di

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.feature.map.domain.usecase.CreateCustomPlaceMarkUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapModule {

	@Provides
	@Singleton
	fun provideCreateCustomPlaceMarkUseCase(paintText: Paint, mTextBoundRect: Rect, canvas: Canvas): CreateCustomPlaceMarkUseCase {
		return CreateCustomPlaceMarkUseCase(paintText, mTextBoundRect, canvas)
	}

	@Provides
	@Singleton
	fun providePaint(): Paint {
		return Paint()
	}

	@Provides
	@Singleton
	fun provideRect(): Rect {
		return Rect()
	}

	@Provides
	@Singleton
	fun provideCanvas(): Canvas {
		return Canvas()
	}

}