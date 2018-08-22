package com.yarrtest.balloon.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.yarrtest.balloon.BalloonGame
import com.yarrtest.balloon.base.AppConfig
import com.yarrtest.balloon.base.BaseScreen
import com.yarrtest.balloon.screens.di.ScreenScope
import com.yarrtest.balloon.screens.game.GameController
import com.yarrtest.balloon.screens.game.di.GameScreenComponent
import com.yarrtest.balloon.screens.game.views.Balloon
import dagger.Module
import dagger.Provides

/**
 * Created by etb on 21.08.2018.
 */
@Module
class GameScreen(
        game: BalloonGame,
        config: AppConfig
) : BaseScreen<BalloonGame, FitViewport>(
        game,
        FitViewport(config.camWidth, config.camHeight)
) {

    var component: GameScreenComponent? = null
    private set

    private var isShown = false
    private var controller: GameController? = null

    init {
        component = game.component!!.plus(this)
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

    @ScreenScope
    @Provides
    fun createBalloonView(): Balloon {
        val texture = Texture(Gdx.files.internal("ball.png"))
        return Balloon(stage!!, texture)
    }
}