package com.teej107.virtualdesktop

import org.jnativehook.mouse.NativeMouseEvent
import org.jnativehook.mouse.NativeMouseMotionAdapter

/**
 * Created by teej107 on 1/17/2018.
 */
class MouseListener(private val edgeListener: EdgeListener) : NativeMouseMotionAdapter() {

    override fun nativeMouseMoved(event: NativeMouseEvent) {
        val edgeOptional = Edge.getEdge(event.point, 5)
        if(edgeOptional.isPresent)
            edgeListener.onEdge(event.point, edgeOptional.get())
        else
            edgeListener.offEdge()
    }
}
