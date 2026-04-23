package com.kostas.gohealth.services

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

// Step tracking flow = MainActivity initializes and starts tracking, onSensorChanged triggers at every step, updateStepsProgress from
// TrackingsViewModel executes. This way the updates only happen when the user is on the app, that's why for the online leaderboard to
// work, MainActivity periodically updates the stepsProgress as well.
class DailyStepTracker(context:Context, private val updateStepsProgress: (Int) -> Unit) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    // Starts tracking changes to the steps column in the trackings table
    fun startTracking() {
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    // Stops tracking changes
    fun stopTracking() {
        sensorManager.unregisterListener(this)
    }

    // Actually updates the steps column in the TrackingsViewModel
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val totalSteps = event.values[0].toInt()
            updateStepsProgress(totalSteps)
        }
    }

    // Have to
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
}
