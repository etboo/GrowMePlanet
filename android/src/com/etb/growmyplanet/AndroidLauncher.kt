package com.etb.growmyplanet

import android.os.Bundle
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.etb.growmyplanet.services.EmptyStoreServiceImpl

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(createGame(), config)
    }

    private fun createGame(): ApplicationListener {
        return GrowMyPlanetGame(
                EmptyStoreServiceImpl(),
                AndroidConfig()
        )
    }
}
