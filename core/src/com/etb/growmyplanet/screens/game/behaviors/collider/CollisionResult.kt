package com.etb.growmyplanet.screens.game.behaviors.collider

import com.etb.growmyplanet.screens.game.models.ObstacleModel

/**
 * Created by etb on 25.08.2018.
 */
sealed class CollisionResult

class None: CollisionResult()

class Collision(val obstacleModel: ObstacleModel): CollisionResult()

class Passed(val obstacleModel: ObstacleModel): CollisionResult()
