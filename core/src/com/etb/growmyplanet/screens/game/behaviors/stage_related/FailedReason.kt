package com.etb.growmyplanet.screens.game.behaviors.stage_related

import com.badlogic.gdx.math.Circle

/**
 * Created by etb on 26.08.2018.
 */
sealed class FailedReason

class Absorption(val absorptionTarget: Circle): FailedReason()

class Collision: FailedReason()