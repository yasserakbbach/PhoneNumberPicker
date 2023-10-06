package com.yasserakbbach.phonenumberpicker.utils

private const val CHAR_PLUS = "+"

internal fun CharSequence?.prependPlus(): String {
    return StringBuilder()
        .append(CHAR_PLUS)
        .append(this)
        .toString()
}

internal fun CharSequence?.startsWithPlus(): Boolean {
    return this?.startsWith(CHAR_PLUS) == true
}