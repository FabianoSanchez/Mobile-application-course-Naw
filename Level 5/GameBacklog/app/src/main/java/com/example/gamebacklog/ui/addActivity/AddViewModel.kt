package com.example.gamebacklog.ui.addActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class AddViewModel(application: Application) : AndroidViewModel(application){

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)




}