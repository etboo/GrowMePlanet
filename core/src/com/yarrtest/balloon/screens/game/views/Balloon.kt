package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yarrtest.balloon.screens.game.Layer
import com.yarrtest.balloon.screens.game.LayerHandler

/**
 * Created by etb on 22.08.2018.
 */
class Balloon(
        private val layerHandler: LayerHandler,
        texture: Texture
): Image(texture), BaseView {

    override fun attach() {
        layerHandler.addActorOnLayer(this, Layer.PLAYER_LEVEL)
    }

    override fun detach() {
        remove()
    }
}