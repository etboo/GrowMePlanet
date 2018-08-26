package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yarrtest.balloon.screens.game.Layer
import com.yarrtest.balloon.screens.game.LayerHandler

/**
 * Created by etb on 23.08.2018.
 */
class BlackHole(
        private val layerHandler: LayerHandler,
        texture: Texture
): Image(texture), BaseView {

    override fun isShown() = parent != null

    override fun show() {
        layerHandler.addActorOnLayer(this, Layer.BACKGROUND)
    }

    override fun hide() {
        remove()
    }

    override fun resize(newWidth: Float, newHeight: Float) {
        val scaleX = newWidth / width
        val scaleY = newHeight / height

        setScale(scaleX, scaleY)
    }
}