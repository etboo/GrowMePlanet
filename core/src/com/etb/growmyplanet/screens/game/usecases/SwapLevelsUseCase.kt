package com.etb.growmyplanet.screens.game.usecases

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.UseCase

/**
 * Created by etb on 26.08.2018.
 */
class SwapLevelsUseCase(
        private val oldLayers: Collection<Group>
) : UseCase<Float, Unit> {

    override fun invoke(scaleKoef: Float) {
        oldLayers.forEach {
            it.setOrigin(Align.top)
            it.addAction(
                    Actions.scaleTo(scaleKoef, scaleKoef, 0.5f)
            )
        }

        //TODO: addNew group

        //TODO: transformGroup

        //TODO:remove oldGroup
    }

}