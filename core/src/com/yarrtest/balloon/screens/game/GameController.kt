package com.yarrtest.balloon.screens.game

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.base.ScreenLifecycleListener
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.managers.level.LevelManager
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.RingBehavior
import com.yarrtest.balloon.screens.game.behaviors.collider.Obstacle
import com.yarrtest.balloon.screens.game.behaviors.stage_related.Stage
import com.yarrtest.balloon.screens.game.behaviors.stage_related.di.GameStageComponent
import com.yarrtest.balloon.screens.game.behaviors.stage_related.di.PlanetBehaviorProvider
import com.yarrtest.balloon.screens.game.di.GameLevelComponent
import com.yarrtest.balloon.screens.game.di.LevelScope
import com.yarrtest.balloon.screens.game.models.ObstacleModel
import com.yarrtest.balloon.screens.game.usecases.PlanetMoveCheckUseCase
import com.yarrtest.balloon.screens.game.views.Planet
import com.yarrtest.balloon.screens.game.views.Ring
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 22.08.2018.
 */
const val GLOBAL_TOUCHES_USE_CASE = "GlobalTouchesUseCase"
const val REGISTER_OBSTACLE_USE_CASE = "RegisterObstacleUseCase"
const val UNREGISTER_OBSTACLE_USE_CASE = "UnregisterObstacleUseCase"
const val GROWTH_TOUCH_UP_USE_CASE = "GrowthTouchUpUseCase"
const val SCORED_USE_CASE = "ScoredUseCase"

@Module
class GameController(
        private val scoreManager: ScoreManager,
        private val levelManager: LevelManager
) : InputListener(), ScreenLifecycleListener {

    //Behaviors
    @Inject
    lateinit var ring: RingBehavior

    private var planet: PlanetBehavior? = null

    //Views
    @Inject
    lateinit var planetView: Planet
    @Inject
    lateinit var ringView: Ring

    private var levelComponent: GameLevelComponent? = null
    private var stageComponent: GameStageComponent? = null

    private lateinit var stage: Stage

    private val obstacles = mutableListOf<Obstacle>()
    private var touchesListener: ScreenTouchesListener? = null

    override fun onShow() {
        planet?.attachView(planetView)
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onHide() {
        planet?.detachView()
    }

    override fun onDispose() {
    }

    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        touchesListener?.onTouchUp()
    }

    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        touchesListener?.let {
            it.onTouchDown()
            return true
        }

        return false
    }

    fun loadLevel(gameScreen: GameScreen, index: Int) {
        disposeLevelBehaviors()

        levelComponent = gameScreen.component?.plus(
                this,
                levelManager.loadLevel(index)
        )
        levelComponent?.inject(this)

        switchStage(Stage.GROWING_STAGE)
    }

    fun update(delta: Float) {
        planet?.act(delta)
    }

    @LevelScope
    @Provides
    @Named(GROWTH_TOUCH_UP_USE_CASE)
    fun provideGrowthTouchUpUseCase(): UseCase<@JvmWildcard Unit, @JvmWildcard Unit> = {
        touchesListener = null
        switchStage(Stage.FLOATING_STAGE)
    }

    @LevelScope
    @Provides
    @Named(GLOBAL_TOUCHES_USE_CASE)
    fun provideScreenTouchesUseCase(): UseCase<@JvmWildcard ScreenTouchesListener?, @JvmWildcard Unit> = { touchesListener = it }

    @LevelScope
    @Provides
    @Named(REGISTER_OBSTACLE_USE_CASE)
    fun provideObstaclesRegistrator(): UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit> = { obstacles.add(it) }

    @LevelScope
    @Provides
    @Named(UNREGISTER_OBSTACLE_USE_CASE)
    fun provideObstacleUnregistrator(): UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit> = { obstacles.remove(it) }

    @LevelScope
    @Provides
    @Named(SCORED_USE_CASE)
    fun provideScoredUseCase(): UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit> = { scoreManager.scored(it.score) }

    @LevelScope
    @Provides
    fun provideBalloonModeCheckUseCase() = PlanetMoveCheckUseCase { obstacles.copy() }

    private fun switchStage(newStage: Stage) {
        disposeStageBehaviors()

        stage = newStage
        stageComponent = levelComponent?.stage()?.also {
            val provider = PlanetBehaviorProvider(newStage)
            it.inject(provider)
            planet = provider.planetBehavior
            planet?.attachView(planetView)
        }

    }

    private fun disposeLevelBehaviors() {
        levelComponent?.let {
            ring.detachView()
            ring.dispose()
        }
    }

    private fun disposeStageBehaviors() {
        planet?.detachView()
        planet?.dispose()
    }
}

private fun <T> List<T>.copy() = ArrayList(this)


