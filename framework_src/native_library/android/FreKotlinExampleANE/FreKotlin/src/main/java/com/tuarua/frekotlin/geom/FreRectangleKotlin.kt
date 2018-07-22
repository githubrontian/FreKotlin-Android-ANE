/*
 *  Copyright 2017 Tua Rua Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

@file:Suppress("unused", "FunctionName", "PrivatePropertyName")

package com.tuarua.frekotlin.geom

import com.adobe.fre.FREObject
import com.tuarua.frekotlin.*

open class FreRectangleKotlin() : FreObjectKotlin() {
    private var TAG = "com.tuarua.FreRectangleKotlin"

    constructor(value: FreObjectKotlin) : this() {
        this.rawValue = value.rawValue
    }

    constructor(value: FREObject?) : this() {
        this.rawValue = value
    }

    @Throws(FreException::class)
    constructor(value: Rect) : this() {
        rawValue = FREObject("flash.geom.Rectangle",
                value.x,
                value.y,
                value.width,
                value.height)
    }

    override val value: Rect
        get() {
            return Rect(Double(rawValue?.get("x")) ?: 0.0,
                    Double(rawValue?.get("y")) ?: 0.0,
                    Double(rawValue?.get("width")) ?: 0.0,
                    Double(rawValue?.get("height")) ?: 0.0)
        }

}

class Rect() {
    var x: Double = 0.0
    var y: Double = 0.0
    var width: Double = 0.0
    var height: Double = 0.0

    constructor(x: Double, y: Double, width: Double, height: Double) : this() {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    constructor(x: Int, y: Int, width: Int, height: Int) : this() {
        this.x = x.toDouble()
        this.y = y.toDouble()
        this.width = width.toDouble()
        this.height = height.toDouble()
    }

    constructor(rect: android.graphics.Rect) : this() {
        this.x = rect.left.toDouble()
        this.y = rect.top.toDouble()
        this.width = rect.width().toDouble()
        this.height = rect.height().toDouble()
    }

    fun toRect(): android.graphics.Rect {
        return android.graphics.Rect(this.x.toInt(), this.y.toInt(), (this.x + this.width).toInt(), (this.y + this
                .height).toInt())
    }

    fun set(x: Double, y: Double, width: Double, height: Double) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    fun set(x: Int, y: Int, width: Int, height: Int) {
        this.x = x.toDouble()
        this.y = y.toDouble()
        this.width = width.toDouble()
        this.height = height.toDouble()
    }

}

fun Rect(freObject: FREObject?): Rect? = FreRectangleKotlin(value = freObject).value
fun Rect(freRectangleObject: FreRectangleKotlin?): Rect? = freRectangleObject?.value

@Throws(FreException::class)
fun Rect.toFREObject(): FREObject? {
    return try {
        FreRectangleKotlin(this).rawValue
    } catch (e: FreException) {
        e.getError(Thread.currentThread().stackTrace)
    } catch (e: Exception) {
        FreException(e).getError(Thread.currentThread().stackTrace)
    }
}