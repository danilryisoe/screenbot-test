package com.example.screenbot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.Intent
import android.graphics.Path
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class BotAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
        Log.d("ScreenBot", "AccessibilityService подключён")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // События интерфейса при необходимости
    }

    override fun onInterrupt() {
        Log.w("ScreenBot", "AccessibilityService прерван")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        if (instance === this) {
            instance = null
        }
        return super.onUnbind(intent)
    }

    fun performSwipe(startX: Float, startY: Float, endX: Float, endY: Float, durationMs: Long = 160L) {
        val path = Path().apply {
            moveTo(startX, startY)
            lineTo(endX, endY)
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0L, durationMs))
            .build()

        dispatchGesture(gesture, null, null)
    }

    companion object {
        @Volatile
        var instance: BotAccessibilityService? = null
            private set
    }
}
