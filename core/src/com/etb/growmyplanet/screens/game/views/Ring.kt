package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.screens.game.Layer
import com.etb.growmyplanet.screens.game.LayerHandler
import com.etb.growmyplanet.setCenterPosition

/**
 * Created by etb on 22.08.2018.
 */

class Ring(
        private val layerHandler: LayerHandler,
        frontTexture: Texture,
        backTexture: Texture
) : BaseView {

    private val frontImage = Image(frontTexture)
    private val backImage = Image(backTexture)

    init {
        frontImage.setOrigin(Align.center)
        backImage.setOrigin(Align.center)
    }

    override fun isShown() = frontImage.parent != null && backImage.parent != null

    override fun show() {
        layerHandler.addActorOnLayer(frontImage, Layer.FOREGROUND)
        layerHandler.addActorOnLayer(backImage, Layer.BACKGROUND)
    }

    override fun hide() {
        frontImage.remove()
        backImage.remove()
    }

    override fun changePosition(x: Float, y: Float) {
        frontImage.setCenterPosition(x, y )
        backImage.setCenterPosition(x, y)
    }

    override fun resize(newSize: Float) {
        val scaleKoef = newSize / frontImage.width

        frontImage.setScale(scaleKoef * 1.23f, scaleKoef)
        backImage.setScale(scaleKoef * 1.23f, scaleKoef)
    }
}