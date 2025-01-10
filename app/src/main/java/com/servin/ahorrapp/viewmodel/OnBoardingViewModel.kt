package com.servin.ahorrapp.viewmodel

import androidx.lifecycle.ViewModel
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.servin.ahorrapp.R
import com.servin.ahorrapp.data.PageData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {


    private val _composition =
        MutableStateFlow<LottieCompositionSpec>(LottieCompositionSpec.RawRes(0))
    val composition: StateFlow<LottieCompositionSpec> = _composition

    private val _items = ArrayList<PageData>()
    val items = _items


    init {
        LoadItems()
    }


    private fun LoadItems() {
        _items.add(
            PageData(
                "Bienvenido",
                "AhorrApp es una aplicación que le permitira ahorrar dinero mediante dinamicas",
                R.raw.welcome
            )
        )
        _items.add(
            PageData(
                "¿Cómo funciona?",
                "Selecciona la dinamica con la que quieres ahorrar y sigue las instrucciones",
                R.raw.save
            )
        )

    }

    fun setImage(imageRes: Int) {
        _composition.value = LottieCompositionSpec.RawRes(imageRes)
    }

}