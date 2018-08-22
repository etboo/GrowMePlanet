package com.yarrtest.balloon.base

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by etb on 21.08.2018.
 */
class BaseStage<out T : Viewport>(
        private val baseViewport: T
) : Stage(baseViewport) {

    override fun getViewport(): T {
        return this.baseViewport
    }
}