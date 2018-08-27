package com.etb.growmyplanet.screens.game.usecases

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.managers.level.START_PLAYER_RADIUS

/**
 * Created by etb on 26.08.2018.
 */
class SwapLevelsUseCase(
        private val currPlayer: Actor,
        private val swapLayersUseCase: UseCase<Unit, Collection<Group>>,
        private val addNewLayersUseCase: UseCase<Unit, Unit>
) : UseCase<UseCase<Unit, Unit>, Unit> {

    override fun invoke(parseNewLevelAndGetNewPlayerUseCase: UseCase<Unit, Unit>) {
        val groups = swapLayersUseCase.invoke(Unit)
        val scaleKoef = START_PLAYER_RADIUS * 2 / (currPlayer.width * currPlayer.scaleX)


        groups.forEach {
            it.setOrigin(Align.top)
            it.addAction(
                    Actions.sequence(
                            Actions.scaleTo(scaleKoef, scaleKoef, 1f),
                            Actions.moveBy(
                                    0f,
                                    -it.height + currPlayer.height * currPlayer.scaleY,
                                    2f
                            ),
                            Actions.run { parseNewLevelAndGetNewPlayerUseCase.invoke(Unit) },
                            Actions.run { addNewLayersUseCase.invoke(Unit) },
                            Actions.removeActor()
                    )
            )
        }

    }

}