package com.yarrtest.balloon.screens.game

import com.badlogic.gdx.scenes.scene2d.InputListener
import com.yarrtest.balloon.base.ScreenLifecycleListener
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.managers.level.LevelManager
import com.yarrtest.balloon.screens.game.behaviors.BalloonBehavior
import com.yarrtest.balloon.screens.game.di.GameSessionComponent
import com.yarrtest.balloon.screens.game.di.SessionScope
import com.yarrtest.balloon.screens.game.usecases.BalloonMoveCheckUseCase
import com.yarrtest.balloon.screens.game.usecases.CollideDetectedUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */

const val SCORE_PER_RING = 100

@Module
class GameController(
        private val scoreManager: ScoreManager,
        private val levelManager: LevelManager
) : InputListener(), ScreenLifecycleListener {

    @Inject lateinit var balloon: BalloonBehavior

    private var component: GameSessionComponent? = null

    fun loadLevel(gameScreen: GameScreen, index: Int) {
        stopPrevBehaviors()

        component = gameScreen.component?.plus(
                this,
                levelManager.loadLevel(index)
        )
        component?.inject(this)
    }

    override fun onShow() {
        balloon.start()
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onHide() {
        balloon.stop()
    }

    override fun onDispose() {
    }

    fun update(delta: Float) {
        balloon.act(delta)
    }

    @SessionScope
    @Provides
    fun provideBalloonModeCheckUseCase()
            = BalloonMoveCheckUseCase(emptyList())

    @SessionScope
    @Provides
    fun provideCollideDetectedUseCase()
            = CollideDetectedUseCase(scoreManager)

    private fun stopPrevBehaviors() {
        component?.let {
            balloon.stop()
        }
    }
}