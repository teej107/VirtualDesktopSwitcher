package com.teej107.virtualdesktop

import java.awt.Point

/**
 * Created by teej107 on 1/17/2018.
 */
interface EdgeListener {
    fun onEdge(point: Point, edge: Edge)
    fun offEdge()
}