package com.teej107.virtualdesktop

import javafx.embed.swing.JFXPanel
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.paint.Color
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame

/**
 * Created by teej107 on 1/17/2018.
 */
class SwitcherWindow : JFrame() {

    companion object {
        private val URL_RESOURCE = javaClass.getResource("switcher-window.fxml")
    }

    private var controller: ImageController

    init {
        type = Type.UTILITY
        isUndecorated = true
        size = Dimension(50, 50)
        location = Point(0, 0)
        background = java.awt.Color(0, 0, 0, 0)
        opacity = 0.8f

        val panel = JFXPanel()
        val loader = FXMLLoader(URL_RESOURCE)
        val scene = Scene(loader.load())
        scene.fill = Color.TRANSPARENT
        this.controller = loader.getController<ImageController>()

        panel.scene = scene
        contentPane = panel
    }

    fun rotateImage(degree: Double) {
        controller.imageView.rotate = degree
    }

    override fun setVisible(b: Boolean) {
        controller.imageView.fitHeight = height.toDouble()
        controller.imageView.fitWidth = width.toDouble()
        super.setVisible(b)
    }
}