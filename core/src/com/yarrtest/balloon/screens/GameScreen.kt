package com.yarrtest.balloon.screens

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.yarrtest.balloon.BalloonGame
import com.yarrtest.balloon.base.AppConfig
import com.yarrtest.balloon.base.BaseScreen
import com.yarrtest.balloon.screens.game.GameController

/**
 * Created by etb on 21.08.2018.
 */
class GameScreen(
        game: BalloonGame,
        config: AppConfig
) : BaseScreen<BalloonGame, FitViewport>(
        game,
        FitViewport(config.camWidth, config.camHeight)
) {

    private var background: Image? = null
    private var isShown = false
    private var controller: GameController? = null

    init {

        controller?.let {
            lifecycleListener = it
            addInputListener(it)
        }

    }

    override fun prepareStage(stage: Stage) {
        if (isShown.not()) {
            //TODO: show background
        }
    }

    override fun render(delta: Float) {
        super.render(delta)
        controller?.update(delta)
    }
}