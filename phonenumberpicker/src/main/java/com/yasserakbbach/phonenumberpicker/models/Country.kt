package com.yasserakbbach.phonenumberpicker.models

import com.yasserakbbach.phonenumberpicker.utils.CountryFactory

/**
 * Country model
 * @author: Yasser AKBBACH
 */
data class Country(
    val iso2: String,
    val name: String,
    val countryCode: Int
) {

    override fun toString() =
        "{iso2:$iso2, name:$name, countryCode:$countryCode}"

    /**
     * To get country code with the [+] prefix
     */
    val countryCodeFormatted : String
        get() = "+$countryCode"

    /**
     * To load proper flag from the resources
     */
    val resourceNameDrawable : String
        get() = "ic_country_flag_$iso2"

    companion object {

        /**
         * Get country by passed iso2
         */
        fun byIso2(iso2 : String) = CountryFactory.all().firstOrNull { it.iso2 == iso2.lowercase() }

        /**
         * Get country by passed iso2 a list of countries
         */
        fun byIso2(iso2 : String, countries : List<Country>) = countries.firstOrNull { it.iso2 == iso2.lowercase() }
    }
}