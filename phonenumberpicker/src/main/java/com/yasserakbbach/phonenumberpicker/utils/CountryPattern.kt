package com.yasserakbbach.phonenumberpicker.utils

object CountryPattern {

    private const val PATTERN_DIGIT_GROUP = "(\\d+)"
    private const val PATTERN_DIGITS = "\\d"

    fun create(number: String): String {
        return number
            .replace(PATTERN_DIGIT_GROUP.toRegex(), "[$0]")
            .replace(PATTERN_DIGITS.toRegex(), "0")
    }
}
