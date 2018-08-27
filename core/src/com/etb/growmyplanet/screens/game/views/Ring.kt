package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.screens.game.Layer
import com.etb.growmyplanet.screens.game.LayerHandler

/**
 * Created by etb on 22.08.2018.
 */

class Ring(
        private val layerHandler: LayerHandler,
        frontTexture: Texture,
        backTexture: Texture
) : BaseView {

    private val overlapKoef = .01f

    private val frontImage = Image(frontTexture)
    private val backImage = Image(backTexture)

    init {
        frontImage.setOrigin(Align.top)
        backImage.setOrigin(Align.bottom)
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
        val backImageRealHeight = backImage.height * backImage.scaleY
        val frontImageRealHeight = frontImage.height * frontImage.scaleY
        val overlapOffset = overlapKoef * frontImageRealHeight

        val horizontalOffset = frontImage.width  * frontImage.scaleY * 0.1f

        frontImage.setCenterPosition(x, y - (frontImageRealHeight - overlapOffset) / 2 )
        backImage.setCenterPosition(x - horizontalOffset, y + (backImageRealHeight - overlapOffset) / 2)
    }

    override fun resize(newSize: Float) {
        val scaleKoef = newSize / frontImage.width

        frontImage.setScale(scaleKoef)
        backImage.setScale(scaleKoef)

        backImage.x = frontImage.x + frontImage.width  * frontImage.scaleY * 0.1f

    }
}