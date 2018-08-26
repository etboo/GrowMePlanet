package com.etb.growmyplanet.di

import com.etb.growmyplanet.managers.level.LevelManager
import com.etb.growmyplanet.managers.ScoreManager
import com.etb.growmyplanet.services.StoreService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Managers {

    @Singleton
    @Provides
    fun provideLevelManager(storeService: StoreService) = LevelManager(storeService)

    @Singleton
    @Provides
    fun provideScoreManager(storeService: StoreService) = ScoreManager(storeService)

}