package com.teej107.virtualdesktop

import org.jnativehook.GlobalScreen
import java.awt.Point
import java.awt.Robot
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.SwingUtilities

/**
 * Created by teej107 on 1/17/2018.
 */
fun main(args: Array<String>) {

    var currentEdge: Edge? = null
    val robot = Robot()

    val window = SwitcherWindow()
    window.contentPane.addMouseListener(object : MouseAdapter() {
        override fun mousePressed(e: MouseEvent?) {
            val keyArr = listOf(KeyEvent.VK_CONTROL, KeyEvent.VK_WINDOWS, currentEdge!!.key)
            keyArr.forEach { robot.keyPress(it) }
            keyArr.forEach { robot.keyRelease(it) }
        }
    })
    val edgeListener = object : EdgeListener {
        override fun onEdge(point: Point, edge: Edge) {
            SwingUtilities.invokeAndWait({
                currentEdge = edge
                val xPoint = when (edge) {
                    Edge.RIGHT -> point.x - window.width + 10
                    Edge.LEFT -> point.x - 10
                }
                window.rotateImage(edge.rotate)
                window.location = Point(xPoint, point.y - window.height / 2)
                window.isVisible = true
            })
        }

        override fun offEdge() {
            SwingUtilities.invokeAndWait({ window.isVisible = false })
        }
    }

    Logger.getLogger(GlobalScreen::class.java.`package`.name).level = Level.OFF
    GlobalScreen.addNativeMouseMotionListener(MouseListener(edgeListener))
    GlobalScreen.registerNativeHook()
    Runtime.getRuntime().addShutdownHook(Thread({ GlobalScreen.unregisterNativeHook() }))
}