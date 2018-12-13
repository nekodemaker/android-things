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
import com.google.android.things.pio.Pwm
import com.google.android.things.contrib.driver.pwmservo.Servo
import com.google.android.things.pio.PeripheralManager






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
    val buttonA = RainbowHat.openButtonA()
    val buttonB = RainbowHat.openButtonB()
    val segment = RainbowHat.openDisplay()
    val servo = Servo("PWM0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val ledstrip = RainbowHat.openLedStrip()
        ledstrip.brightness = 1
        val rainbow = IntArray(RainbowHat.LEDSTRIP_LENGTH)
        for (i in rainbow.indices) {
            rainbow[i] = Color.HSVToColor(255, floatArrayOf(i * 360f / rainbow.size, 1.0f, 1.0f))
        }
        ledstrip.write(rainbow)

        ledstrip.close()*/

        segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
        segment.display("HELLO WORLD")
        segment.setEnabled(true)
        segment.close()
        buttonC.setOnButtonEventListener(object : OnButtonEventListener {
            override fun onButtonEvent(button: Button, pressed: Boolean) {
                finish()
            }
        })
        buttonB.setOnButtonEventListener(object : OnButtonEventListener {
            override fun onButtonEvent(button: Button, pressed: Boolean) {
                if (pressed) {
                    servo.angle = servo.minimumAngle
                    servo.setPulseDurationRange(1.0, 2.0)
                    servo.setAngleRange(-90.0, 90.0)
                    servo.setEnabled(true)
                    Thread.sleep(100)
                    servo.setEnabled(false)
                }
            }
        })
        buttonA.setOnButtonEventListener(object : OnButtonEventListener {
            override fun onButtonEvent(button: Button, pressed: Boolean) {
                if (pressed) {
                    servo.angle = servo.maximumAngle
                    servo.setPulseDurationRange(1.0, 2.0)
                    servo.setAngleRange(-90.0, 90.0)
                    servo.setEnabled(true)
                    Thread.sleep(100)
                    servo.setEnabled(false)
                }
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        servo.setEnabled(false)
        servo.close()
    }

}
