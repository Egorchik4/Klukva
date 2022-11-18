package com.example.klukva.di

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.klukva.domain.usecases.CreateCustomPlaceMarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

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