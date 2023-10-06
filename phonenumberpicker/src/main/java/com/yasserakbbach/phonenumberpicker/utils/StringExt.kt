package com.yasserakbbach.phonenumberpicker.utils

internal fun String?.clearSpaces(): String? {
    return this?.replace("\\s+", "")
}