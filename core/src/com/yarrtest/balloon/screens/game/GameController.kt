package com.yarrtest.balloon.screens.game

import com.badlogic.gdx.scenes.scene2d.InputListener
import com.yarrtest.balloon.base.ScreenLifecycleListener
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.managers.level.LevelManager
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.RingBehavior
import com.yarrtest.balloon.screens.game.di.GameLevelComponent
import com.yarrtest.balloon.screens.game.di.LevelScope
import com.yarrtest.balloon.screens.game.stages.FloatingStageBehaviors
import com.yarrtest.balloon.screens.game.stages.GrowthStageBehaviors
import com.yarrtest.balloon.screens.game.stages.Stage
import com.yarrtest.balloon.screens.game.usecases.PlanetMoveCheckUseCase
import com.yarrtest.balloon.screens.game.usecases.ScreenTouchesUseCase
import com.yarrtest.balloon.screens.game.views.Planet
import com.yarrtest.balloon.screens.game.views.Ring
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

    lateinit var planet: PlanetBehavior

    @Inject
    lateinit var ring: RingBehavior

    @Inject
    lateinit var planetView: Planet
    @Inject
    lateinit var ringView: Ring

    private var component: GameLevelComponent? = null

    private lateinit var stage: Stage

    fun loadLevel(gameScreen: GameScreen, index: Int) {
        disposePrevBehaviors()

        component = gameScreen.component?.plus(
                this,
                levelManager.loadLevel(index)
        )
        component?.inject(this)

        switchStage(Stage.GROWTH_STAGE)
    }

    override fun onShow() {
        planet.attachView(planetView)
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onHide() {
        planet.detachView()
    }

    override fun onDispose() {
    }

    fun update(delta: Float) {
        planet.act(delta)
    }

    @LevelScope
    @Provides
    fun provideScreenTouchesUseCase() = ScreenTouchesUseCase()

    @LevelScope
    @Provides
    fun provideBalloonModeCheckUseCase() = PlanetMoveCheckUseCase { ring.collider }

    private fun switchStage(newStage: Stage) {
        stage = newStage
        val behaviors = when(stage) {
            Stage.GROWTH_STAGE -> GrowthStageBehaviors()
            Stage.FLOATING_STAGE -> FloatingStageBehaviors()
        }

        component?.let {
            it.inject(behaviors)
            planet = behaviors.providePlanetBehavior()
        }

    }

    private fun disposePrevBehaviors() {
        component?.let {
            planet.dispose()
        }
    }
}