package com.etb.growmyplanet.screens.game

import com.badlogic.gdx.scenes.scene2d.Actor

interface LayerHandler {
    fun addActorOnLayer(actor: Actor, layer: Layer)
}
