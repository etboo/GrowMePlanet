package com.etb.growmyplanet.di

import com.etb.growmyplanet.managers.ScoreManager
import com.etb.growmyplanet.managers.level.LevelManager
import com.etb.growmyplanet.services.StoreService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Managers {

    @Singleton
    @Provides
    fun provideLevelManager(
            storeService: StoreService,
            gson: Gson
    ) = LevelManager(storeService, gson)

    @Singleton
    @Provides
    fun provideScoreManager(storeService: StoreService) = ScoreManager(storeService)

}