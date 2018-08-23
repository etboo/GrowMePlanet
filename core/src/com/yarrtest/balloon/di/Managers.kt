package com.yarrtest.balloon.di

import com.yarrtest.balloon.managers.LevelManager
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.services.StoreService
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