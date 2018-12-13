package com.example.sodara.android_things

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.things.contrib.driver.apa102.Apa102
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import java.io.IOException
import java.util.*
import com.google.android.things.contrib.driver.button.Button.OnButtonEventListener
import com.google.android.things.contrib.driver.ht16k33.Ht16k33


/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * val service = PeripheralManagerService()
 * val mLedGpio = service.openGpio("BCM6")
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * mLedGpio.value = true
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 *
 */
class MainActivity : Activity() {
    private var mLedstrip: Apa102? = null
    private var LEDSTRIP_BRIGHTNESS = 1
    val buttonC = RainbowHat.openButtonC()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonC.setOnButtonEventListener(object : OnButtonEventListener {
            override fun onButtonEvent(button: Button, pressed: Boolean) {
                finish()
                val segment = RainbowHat.openDisplay()
                segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
                //segment.display("")
                segment.setEnabled(true)
                segment.close()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        butt

    }

}
