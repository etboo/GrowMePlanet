package com.etb.growmyplanet.screens.game.usecases

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.managers.level.START_PLAYER_RADIUS
import com.etb.growmyplanet.managers.level.START_PLAYER_Y
import com.etb.growmyplanet.screens.game.Layer

/**
 * Created by etb on 26.08.2018.
 */
class SwapLevelsUseCase(
        private val currPlayer: Actor,
        private val getOldLayersUseCase: UseCase<Unit, Collection<Group>>,
        private val removeOldLayersUseCase: UseCase<Unit, Unit>,
        private val addNewLayersUseCase: UseCase<Unit, Unit>
) : UseCase<UseCase<Unit, Unit>, Unit> {

    override fun invoke(parseNewLevelAndGetNewPlayerUseCase: UseCase<Unit, Unit>) {
        val groups = getOldLayersUseCase.invoke(Unit)
        val scaleKoef = START_PLAYER_RADIUS * 2 / (currPlayer.width * currPlayer.scaleX)

        groups.forEachIndexed { index, group ->
            group.setOrigin(Align.top)
            group.addAction(
                    SequenceAction().also { action ->
                        action.addAction(getAnimation(group, scaleKoef))
                        if (index == Layer.PLAYER_LEVEL.ordinal) {
                            action.addAction(
                                    getLevelChangeAction(
                                        parseNewLevelAndGetNewPlayerUseCase
                                    )
                            )
                        }
                        action.addAction(Actions.removeActor())
                    }

            )
        }
    }

    private fun getAnimation(group: Actor, scaleKoef: Float): Action {
        return Actions.parallel(
                Actions.scaleTo(scaleKoef, scaleKoef, 1f),
                Actions.moveTo(
                        group.x,
                        -group.height + START_PLAYER_Y + currPlayer.height * currPlayer.scaleY * .2f,
                        1f
                ),
                Actions.delay(.5f)
        )
    }

    private fun getLevelChangeAction(parseNewLevel: UseCase<Unit, Unit>): Action {
        return Actions.sequence(
                Actions.run { removeOldLayersUseCase.invoke(Unit) },
                Actions.run { parseNewLevel.invoke(Unit) },
                Actions.run { addNewLayersUseCase.invoke(Unit) }
        )
    }

}