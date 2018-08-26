package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yarrtest.balloon.screens.game.Layer
import com.yarrtest.balloon.screens.game.LayerHandler

/**
 * Created by etb on 22.08.2018.
 */
class Planet(
        private val layerHandler: LayerHandler,
        texture: Texture
): Image(texture), BaseView {

    override fun isShown() = parent != null

    override fun show() {
        layerHandler.addActorOnLayer(this, Layer.PLAYER_LEVEL)
    }

    override fun hide() {
        remove()
    }

    override fun resize(newWidth: Float, newHeight: Float) {
        val scaleX = newWidth / width
        val scaleY = newHeight / height

        setScale(scaleX, scaleY)
    }

    fun startFallingAnimation() {

    }

    fun startAbsorbAnimation(center: Vector2) {

    }
}