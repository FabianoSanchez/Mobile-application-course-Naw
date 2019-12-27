package com.example.recyclerview.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.recyclerview.model.ColorItem
import com.example.recyclerview.repository.ColorsRepository

class MainViewModel (application: Application) : AndroidViewModel(application){


    private val colorsRepository = ColorsRepository()

    val colorItems = MutableLiveData<List<ColorItem>>().apply{
        value = colorsRepository.getColorItems()
    }


}