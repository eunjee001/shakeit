package com.kkyoungs.shakeit

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kkyoungs.shakeit.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {
    var mBinding : ActivityMainBinding ?= null
    val binding get() = mBinding!!

    private var accel : Float = 0.0f    // 초기
    private var accelCurrent : Float = 0.0f     // 이동하는 치수
    private var accelLast : Float = 0.0f

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accel = 10f
        accelCurrent = SensorManager.GRAVITY_EARTH      // 지구 중력값 주기
        accelLast = SensorManager.GRAVITY_EARTH

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x :Float = event ?.values?.get(0) as Float
        val y :Float = event ?.values?.get(0) as Float
        val z :Float = event ?.values?.get(0) as Float

        binding.x.text = "X: " +x.toInt().toString()
        binding.y.text = "Y: " +y.toInt().toString()
        binding.z.text = "Z: " +z.toInt().toString()

        accelLast = accelCurrent
        accelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        val delta : Float = accelCurrent - accelLast

        accel = accel * 0.9f + delta

        // 액셀 치수가 10이 넘어가면 흔들었다고 휴대폰이 판단

        if (accel > 10){
            binding.smile.setImageResource(R.drawable.baby)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.smile.setImageResource(R.drawable.baby_fighting)
            }, 1000L)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("tag", "MainActivit - onAccuracyChanged() called")
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorManager
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}