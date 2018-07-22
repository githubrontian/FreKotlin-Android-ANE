# FreKotlin

Example Android Studio project showing how to create Air Native Extensions for Android using Kotlin.  
  
This project is used as the basis for the following ANEs   
[Google Maps ANE](https://github.com/tuarua/Google-Maps-ANE)   
[AdMob ANE](https://github.com/tuarua/AdMob-ANE)  
[WebViewANE](https://github.com/tuarua/WebViewANE)
[FirebaseANE](https://github.com/tuarua/Firebase-ANE)
[ZipANE](https://github.com/tuarua/Zip-ANE)

-------------
  

### Getting Started

A basic Hello World [starter project](/starter_project) is included 


### How to use 
###### Converting from FREObject args into Kotlin types, returning FREObjects
The following table shows the primitive as3 types which can easily be converted to/from Kotlin types


| AS3 type | Kotlin type | AS3 param->Kotlin | return Kotlin->AS3 |
|:--------:|:--------:|:--------------|:-----------|
| String | String | `val s = String(argv[0])` | `return s.toFREObject()`|
| int | Int | `val i = Int(argv[0])` | `return i.toFREObject()`|
| Boolean | Boolean | `val b = Boolean(argv[0])` | `return b.toFREObject()`|
| Number | Double | `val d = Double(argv[0])` | `return d.toFREObject()`|
| Number | Float | `val fl = Float(argv[0])` | `return fl.toFREObject()`|
| Date | Date | `val dt = Date(argv[0])` | `return dt.toFREObject()`|
| Rectangle | Rect | `val r = Rect(argv[0])` | `return r.toFREObject()`|
| Point | Point | `val pnt = Point(argv[0])` | `return pnt.toFREObject()`|
| Vector Int | IntArray | `val arr = IntArray(argv[0])` | `return arr.toFREArray()`|
| Vector Boolean | BooleanArray | `val arr = BooleanArray(argv[0])` | `return arr.toFREArray()`|
| Vector Number | DoubleArray | `val arr = DoubleArray(argv[0])` | `return arr.toFREArray()`|
| Vector String | List | `val al = List<String>(argv[0])` | `return al.toFREArray()`|
| Object | Map<String, Any>? | `val dict: Map<String, Any>? = Map(argv[0])` | TODO |
| null | null | | |


#### Basic Types

```kotlin
val myString: String? = String(argv[0])
val myInt = Int(argv[1]);
val myBool = Boolean(argv[2]);

val kotlinString = "I am a string from Kotlin"
return kotlinString.toFREObject()
```

#### Creating new FREObjects

```kotlin
val newPerson = FREObject("com.tuarua.Person")

// create a FREObject passing args
// 
// The following param types are allowed: 
// String, Int, Double, Float, Long, Short, Boolean, Date, FREObject
val frePerson = FREObject("com.tuarua.Person", "Bob", "Doe", 28, myFREObject);
```

#### Calling Methods

```kotlin
// call a FREObject method passing args
// 
// The following param types are allowed: 
// String, Int, Double, Float, Long, Short, Boolean, Date, FREObject
val addition = freCalculator.call("add", 100, 31)
```

#### Getting / Setting Properties

```kotlin
val oldAge = Int(person["age"])
val newAge = oldAge + 10

// Set property using braces access
person["age"] = (oldAge + 10).toFREObject()

// Set property using setProp
person.setProp("age", oldAge + 10)

```

#### Arrays

```kotlin
val airArray: FREArray? = FREArray(argv[0])
// convert to a Kotlin List<String>
val airStringVector = List<String>(argv[0])

// create a Vector.<com.tuarua.Person> with fixed length of 5
val newFreArray = FREArray("com.tuarua.Person", 5, true)
val len = newFreArray.length

// loop over FREArray
for (fre: FREObject? in airArray) {
    trace(Int(fre))
}

// set element 1 to 123
airArray[0] = 123.toFREObject()

// return Kotlin IntArray to AIR
val kotArr: IntArray = intArrayOf(99, 98, 92, 97, 95)
return kotArr.toFREArray()
```

#### Sending Events back to AIR

```kotlin
trace("Hi", "There")

// with interpolation
trace("My name is: $name")

dispatchEvent("MY_EVENT", "this is a test")
```

#### Bitmapdata

```kotlin
val icon: Bitmap? = Bitmap(argv[0])

return icon.toFREObject()
```

#### ByteArrays

```kotlin
val byteArray = ByteArray(argv[0])
if (byteArray != null) {
    val str = String(Base64.encode(byteArray, Base64.NO_WRAP), Charset.forName("utf-8"))
}
```

#### Error Handling

```kotlin
try {
    person.getProp("doNotExist")
} catch (e: FreException) {
    return e.getError(Thread.currentThread().stackTrace) //return the error as an actionscript error
}
```


Advanced Example - Extending. Convert to/from LatLng
```kotlin
package com.tuarua.frekotlin

import com.adobe.fre.FREObject
import com.google.android.gms.maps.model.LatLng

class FreCoordinateKotlin() : FreObjectKotlin() {
    private var TAG = "com.tuarua.FreCoordinateKotlin"

    constructor(value: LatLng) : this() {
        rawValue = FREObject("com.tuarua.googlemaps.Coordinate", value.longitude, value.latitude)
    }

    constructor(freObject: FREObject?) : this() {
        rawValue = freObject
    }

    override val value: LatLng
        get() {
            return LatLng(Double(rawValue?.get("latitude")) ?: 0.0,
                    Double(rawValue?.get("longitude")) ?: 0.0)
        }
}

fun LatLng(freObject: FREObject?): LatLng = FreCoordinateKotlin(freObject = freObject).value
```

### Prerequisites

You will need

- Android Studio 3.0
- IntelliJ IDEA
- AIR 27+
