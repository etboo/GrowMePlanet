package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image

/**
 * Created by etb on 22.08.2018.
 */
class Balloon(
        private val targetStage: Stage,
        texture: Texture
): Group(), BaseView {

    init {
        Image(texture).also {
            addActor(it)
        }
    }

    override fun attach() {
        targetStage.addActor(this)
    }

    override fun detach() {
        remove()
    }
}