# PhoneNumberPicker [![Build Status](https://jitpack.io/v/yasserakbbach/PhoneNumberPicker.svg)](https://jitpack.io/v/yasserakbbach/PhoneNumberPicker)
Phone number picker is an Android library that's built upon the material **TextInputLayout** to ease the process of entering/retreiving phone numbers.

# Features
-  **All countries**: it has around 220+ country (including some independent ones).
-  **Filter by continents**: you can filter countries by [continent](https://en.wikipedia.org/wiki/Continent).
-  **Exclude country**: you can exclude countries by their [ISO2 code](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes).
-  **Modern country flags**: the flags used in the library are here [rectangular-rounded-edges](https://www.flaticon.com/packs/international-flags/2?k=1626958303329).
-  **Full phone number details**: you can extract country name, code and ISO2 easily!
-  **Full control**: you have the ability to **change default country flag**, **text color and size**, **outline border color** and **max length of digits**.
-  **Bottom Sheet Dialog**: country list

# Installation
**Step 1.** Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2.** Add the dependency
```gradle
dependencies {
	        implementation 'com.github.yasserakbbach:PhoneNumberPicker:1.0.0'
	}
```

# Demo
![demo](https://media.giphy.com/media/JcVSj4T1CYST7aKHXL/giphy.gif)

# Quick Start
```xml
<com.yasserakbbach.phonenumberpicker.PhoneNumberPicker
        android:id="@+id/phone_number"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="12dp"
        android:layout_marginEnd="12dp"/>
```

# XML properties
| Name | Value                                             |
|------|---------------------------------------------------|
|```app:textColor``` | ```#FF0000``` OR ```@color/red``` |
|```app:outlineBorderColor``` | ```#FF0000``` OR ```@color/red``` |
|```app:textSize``` | ```18sp``` |
|```app:defaultCountry``` | ```ma```, ```us```, ```gb``` or other ISO2 code|
|```app:continents``` | ```all|africa|asia|europe|north_america|south_america|oceania``` |
|```app:maxLength``` | ```14``` |

# Methods and properties
```kotlin
// To get current countries list
phoneNumber.currentCountries

// Getet full phone number
fun getFullPhoneNumber() : String

// Get selected country
fun getSelectedCountry() : Country

// Get countries of specifi continents
fun onlyContinents(vararg continents : Continent) : Unit

// Exclude certain countries by ISO2 criteria
fun exceptCountries(vararg iso2s : String) : Unit

// Add listener on country selection
fun setOnCountrySelected(onCountrySelected: OnCountrySelected) : Unit

// Change text color by a resource OR hexadecimal color #FF0000
fun setTextColor(@ColorRes color : Int) : Unit
fun setTextColor(color : String) : Unit

// Change the outline border color of the TextInputLayout by resource OR hexadecimal color 
fun setOutlineBorderColor(@ColorRes color : Int) : Unit
fun setOutlineBorderColor(color : String) : Unit

// Change text size, the used unit is PIXEL
fun setTextSize(size : Float)

// Set default country flag by given iso2
fun setDefaultCountry(iso2 : String)

// To change max length of digits including the [+] digit!
fun setMaxLength(maxLength : Int)
```

# Special Thanks to:
- [PhoneNumberKit](https://github.com/ibrahimsn98/PhoneNumberKit) for inspiring me to work on **PhoneNumberPicker** library.
- [Dillinger](https://dillinger.io/) for making it easier to create this markup file.
- [Flagicons](https://flagicons.lipis.dev/) for helping me to verify country flags and get ISO2 codes.
- [SVG-COMPRESSOR](https://online-converting.com/svg-optimizer/) for compressing SVG files and making them lighter.
- [FLAG ATTRIBUTES ON ANDROID](https://medium.com/@JakobUlbrich/flag-attributes-in-android-how-to-use-them-ac4ec8aee7d1)

> Knowledge is weapon

# License
[MIT](https://github.com/yasserakbbach/PhoneNumberPicker/blob/main/LICENSE)
