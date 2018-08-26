package com.etb.growmyplanet.base

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture

private const val DEFAULT_HEIGHT = 640.0f
private const val DEFAULT_WIDTH = 1136.0f

open class BaseGame(
        private val appConfig: AppConfig
) : ApplicationAdapter() {

    private lateinit var assetManager: AssetManager
    private var screen: Screen? = null


    override fun create() {
        if (this.appConfig.isDebug) {
            Gdx.app.logLevel = 3
        }

        this.assetManager = AssetManager()
        Texture.setAssetManager(this.assetManager)
    }

    override fun pause() {
        this.screen?.pause()
    }

    override fun resume() {
        this.screen?.resume()
        this.screen?.show()
    }

    override fun resize(width: Int, height: Int) {
        this.screen?.resize(width, height)
    }

    override fun render() {
        this.screen?.render(Math.min(Gdx.graphics.deltaTime, appConfig.minDeltaTime))
    }

    override fun dispose() {
        this.screen?.dispose()
        this.assetManager.dispose()
    }

    fun setScreen(screen: Screen) {
        this.screen?.pause()
        this.screen?.hide()
        this.screen?.dispose()

        screen.resume()
        screen.show()
        screen.resize(Gdx.graphics.width, Gdx.graphics.height)
        this.screen = screen
    }
}
