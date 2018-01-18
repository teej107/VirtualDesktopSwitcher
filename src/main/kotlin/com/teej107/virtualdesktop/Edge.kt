package com.teej107.virtualdesktop

import java.awt.GraphicsEnvironment
import java.awt.Point
import java.awt.event.KeyEvent
import java.util.*

/**
 * Created by teej107 on 1/17/2018.
 */
enum class Edge(val key: Int, val rotate: Double) {
    RIGHT(KeyEvent.VK_RIGHT, 0.0),
    LEFT(KeyEvent.VK_LEFT, 180.0);

    companion object {
        private val BOUNDS = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds.size

        fun getEdge(location: Point, padding: Int): Optional<Edge> {
            if (location.x <= padding)
                return Optional.of(LEFT)
            if (location.x >= BOUNDS.width - padding)
                return Optional.of(RIGHT)

            return Optional.empty();
        }
    }
}