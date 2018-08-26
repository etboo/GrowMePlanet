package com.yarrtest.balloon.screens.game.behaviors.stage_related

import com.badlogic.gdx.math.Vector2

/**
 * Created by etb on 26.08.2018.
 */
sealed class FailedReason

class Absorption(val absorptionCenter: Vector2): FailedReason()

class Collision: FailedReason()