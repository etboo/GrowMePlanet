package com.etb.growmyplanet.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.base.ScreenLifecycleListener
import com.etb.growmyplanet.managers.ScoreManager
import com.etb.growmyplanet.managers.level.LevelManager
import com.etb.growmyplanet.screens.game.behaviors.BlackHoleBehavior
import com.etb.growmyplanet.screens.game.behaviors.PlanetBehavior
import com.etb.growmyplanet.screens.game.behaviors.RingBehavior
import com.etb.growmyplanet.screens.game.behaviors.collider.Obstacle
import com.etb.growmyplanet.screens.game.behaviors.phase_related.*
import com.etb.growmyplanet.screens.game.behaviors.phase_related.di.GamePhaseComponent
import com.etb.growmyplanet.screens.game.behaviors.phase_related.di.PlanetBehaviorProvider
import com.etb.growmyplanet.screens.game.di.GameLevelComponent
import com.etb.growmyplanet.screens.game.di.GameScreenComponent
import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.models.ObstacleModel
import com.etb.growmyplanet.screens.game.models.RingModel
import com.etb.growmyplanet.screens.game.usecases.PlanetMoveCheckUseCase
import com.etb.growmyplanet.screens.game.usecases.SwapLevelsUseCase
import com.etb.growmyplanet.screens.game.views.BlackHole
import com.etb.growmyplanet.screens.game.views.Planet
import com.etb.growmyplanet.screens.game.views.Ring
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
const val COLLISION_USE_CASE = "CollisionUseCase"
const val FAILED_ANIMATION_FINISHED_USE_CASE = "FailedAnimationFinished"
const val PASSED_ANIMATION_FINISHED_USE_CASE = "PassedAnimationFinished"

@Module
class GameController(
        private val scoreManager: ScoreManager,
        private val levelManager: LevelManager,
        private val swapLevelsUseCase: SwapLevelsUseCase
) : InputListener(), ScreenLifecycleListener {

    //Behaviors
    @Inject
    lateinit var ring: RingBehavior

    @Inject
    lateinit var blackHole: BlackHoleBehavior

    private var planet: PlanetBehavior? = null

    //Views
    @Inject
    lateinit var planetView: Planet
    @Inject
    lateinit var ringView: Ring
    @Inject
    lateinit var blackHoleView: BlackHole

    private lateinit var parentComponent: GameScreenComponent

    private var levelComponent: GameLevelComponent? = null
    private var phaseComponent: GamePhaseComponent? = null

    private lateinit var phase: Phase

    private val obstacles = mutableListOf<Obstacle>()
    private var touchesListener: ScreenTouchesListener? = null

    private var controllerTask: Runnable? = null

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
        Gdx.app.log("@", "touchDown x = $x y = $y")
        touchesListener?.let {
            it.onTouchDown()
            return true
        }

        return false
    }

    fun start(gameScreen: GameScreen) {
        parentComponent = gameScreen.component!!
        loadNextLevel()
    }

    fun update(delta: Float) {
        controllerTask?.run()
        controllerTask = null

        planet?.act(delta)
        ring.act(delta)
        blackHole.act(delta)
    }

    @LevelScope
    @Provides
    @Named(GROWTH_TOUCH_UP_USE_CASE)
    fun provideGrowthTouchAction(): UseCase<@JvmWildcard Unit, @JvmWildcard Unit> = {
        touchesListener = null
        switchGamePhase(FloatingPhase())
    }

    @LevelScope
    @Provides
    @Named(GLOBAL_TOUCHES_USE_CASE)
    fun provideScreenTouchObservable(): UseCase<@JvmWildcard ScreenTouchesListener?, @JvmWildcard Unit>
            = { touchesListener = it }

    @LevelScope
    @Provides
    @Named(REGISTER_OBSTACLE_USE_CASE)
    fun provideObstaclesRegistrator(): UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>
            = { obstacles.add(it) }

    @LevelScope
    @Provides
    @Named(UNREGISTER_OBSTACLE_USE_CASE)
    fun provideObstacleUnregistrator(): UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>
            = {
        if(obstacles.contains(it)) {
            obstacles.remove(it)
            if(obstacles.isEmpty()) {
                switchGamePhase(LevelPassed())
            }
        }
    }

    @LevelScope
    @Provides
    @Named(SCORED_USE_CASE)
    fun provideScoredAction(): UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit>
            = { scoreManager.scored(it.score) }

    @LevelScope
    @Provides
    @Named(COLLISION_USE_CASE)
    fun provideCollisionAction(): UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit>
            = {
        val failedReason = when(it) {
            is BlackHoleModel -> Absorption(Circle(it.x, it.y, it.radius))
            is RingModel -> Collision()
        }

        switchGamePhase(LevelFailed(failedReason))
    }

    @LevelScope
    @Provides
    @Named(FAILED_ANIMATION_FINISHED_USE_CASE)
    fun provideFailedAnimationListener(): UseCase<@JvmWildcard Unit, @JvmWildcard Unit>
     = {
        //TODO: switch phase to level_finished
    }

    @LevelScope
    @Provides
    @Named(PASSED_ANIMATION_FINISHED_USE_CASE)
    fun providePassedAnimationListener(): UseCase<@JvmWildcard Unit, @JvmWildcard Unit>
            = {
            swapLevelsUseCase.invoke {
                loadNextLevel()
            }
    }

    @LevelScope
    @Provides
    fun providePlanetMoveChecker(
            @Named(SCORED_USE_CASE) scored: UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit>,
            @Named(COLLISION_USE_CASE) collided: UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit>
    ) = PlanetMoveCheckUseCase(
            { obstacles.copy() },
            passedAction = scored,
            collisionAction = collided
    )

    private fun loadNextLevel() {
        disposeLevelBehaviors()

        levelComponent = parentComponent.plus(
                this,
                levelManager.loadNextLevel()
        )
        levelComponent?.inject(this)

        switchGamePhaseImpl(GrowingPhase())
        attachLevelScopeViews()
    }

    private fun attachLevelScopeViews() {
        ring.attachView(ringView)
        blackHole.attachView(blackHoleView)
    }

    private fun switchGamePhase(newStage: Phase) {
        controllerTask = Runnable { switchGamePhaseImpl(newStage) }
    }

    private fun switchGamePhaseImpl(newPhase: Phase) {
        Gdx.app.log("@", "switch phase $newPhase")
        disposeStageBehaviors()

        phase = newPhase
        phaseComponent = levelComponent?.stage()?.also {
            val provider = PlanetBehaviorProvider(newPhase)
            it.inject(provider)
            planet = provider.planetBehavior
            planet?.attachView(planetView)
        }
    }

    private fun disposeLevelBehaviors() {
        levelComponent?.let {
            ring.detachView()
            ring.dispose()

            blackHole.detachView()
            blackHole.dispose()
        }
    }

    private fun disposeStageBehaviors() {
        planet?.detachView()
        planet?.dispose()
    }
}

private fun <T> List<T>.copy() = ArrayList(this)


