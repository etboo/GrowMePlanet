package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.graphics.Texture
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

    override fun setSize(value: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attach() {
        layerHandler.addActorOnLayer(this, Layer.PLAYER_LEVEL)
    }

    override fun detach() {
        remove()
    }
}