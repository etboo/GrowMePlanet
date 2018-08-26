package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.screens.game.Layer
import com.etb.growmyplanet.screens.game.LayerHandler

/**
 * Created by etb on 23.08.2018.
 */
class BlackHole(
        private val layerHandler: LayerHandler,
        texture: Texture
): Image(texture), BaseView {

    init {
        setOrigin(Align.center)
    }

    override fun isShown() = parent != null

    override fun show() {
        layerHandler.addActorOnLayer(this, Layer.BACKGROUND)
    }

    override fun changePosition(x: Float, y: Float) {
        setCenterPosition(x, y)
    }

    override fun hide() {
        remove()
    }

    override fun resize(newSize: Float) {
        val scaleKoef = newSize / width
        setScale(scaleKoef)
    }
}