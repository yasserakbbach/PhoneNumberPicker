package com.yasserakbbach.phonenumberpicker.utils

import com.yasserakbbach.phonenumberpicker.models.Continent
import com.yasserakbbach.phonenumberpicker.models.Country

/**
 * Helper class to generate countries and filter them according to a given criteria
 *
 * @author: Yasser AKBBACH
 */
object CountryFactory {

    /**
     * Get all countries
     */
    fun all() = mutableListOf(
        *africa().toTypedArray(),
        *asia().toTypedArray(),
        *europe().toTypedArray(),
        *northAmerica().toTypedArray(),
        *southAmerica().toTypedArray(),
        *oceania().toTypedArray()
    )

    /**
     * Get countries of a given continents
     */
    fun onlyContinents(vararg continents : Continent) : MutableList<Country> {

        val countries = mutableSetOf<Country>()
        continents.distinct().forEach {

            when(it) {

                Continent.AFRICA -> countries.addAll(africa())
                Continent.ASIA   -> countries.addAll(asia())
                Continent.EUROPE -> countries.addAll(europe())
                Continent.NORTH_AMERICA -> countries.addAll(northAmerica())
                Continent.SOUTH_AMERICA -> countries.addAll(southAmerica())
                Continent.OCEANIA -> countries.addAll(oceania())
            }
        }

        return countries.toMutableList()
    }

    /**
     * Filter selected continents from XML attributes,
     * for more info about the flag approach visit:
     * @link: https://medium.com/@JakobUlbrich/flag-attributes-in-android-how-to-use-them-ac4ec8aee7d1
     */
    fun onlyContinents(intFlag : Int): MutableList<Country> {

        // If all was selected OR random bigger/lesser number was selected
        if(intFlag == 63 || intFlag > 63 || intFlag < 1) {
            return all()
        }

        // 100000 => 000001
        val continents = mutableSetOf<Continent>()

        val reversed = intFlag.toString(radix = 2).reversed()
        reversed.toCharArray().forEachIndexed { i, char ->
            if(char == '1') {
                continents.add(
                    Continent.values()[i]
                )
            }
        }

        return onlyContinents(*continents.toTypedArray())
    }

    /**
     * Africa countries
     */
    fun africa() = listOf(
        Country("dz", "Algeria (‫الجزائر‬‎)", 213),
        Country("ao", "Angola", 244),
        Country("bj", "Benin (Bénin)", 229),
        Country("bw", "Botswana", 267),
        Country("bf", "Burkina Faso", 226),
        Country("bi", "Burundi (Uburundi)", 257),
        Country("cv", "Cape Verde (Cabo Verdi)", 238),
        Country("cm", "Cameroon (Cameroun)", 237),
        Country("cf", "Central African Republic (République centrafricaine)", 236),
        Country("td", "Chad (Tchad)", 235),
        Country("km", "Comoros (‫جزر القمر‬‎)", 269),
        Country("ci", "Ivory Coast (Côte d’Ivoire)", 225),
        Country("cd", "Congo (DRC) (Jamhuri ya Kidemokrasia ya Kongo)", 243),
        Country("dj", "Djibouti", 253),
        Country("eg", "Egypt (‫مصر‬‎)", 20),
        Country("gq", "Equatorial Guinea (Guinea Ecuatorial)", 240),
        Country("er", "Eritrea", 291),
        Country("et", "Ethiopia", 251),
        Country("ga", "Gabon", 241),
        Country("gm", "Gambia", 220),
        Country("gh", "Ghana (Gaana)", 233),
        Country("gn", "Guinea (Guinée)", 224),
        Country("gw", "Guinea-Bissau (Guiné Bissau)", 245),
        Country("ke", "Kenya", 254),
        Country("ls", "Lesotho", 266),
        Country("lr", "Liberia", 231),
        Country("ly", "Libya (‫ليبيا‬‎)", 218),
        Country("mg", "Madagascar (Madagasikara)", 261),
        Country("mw", "Malawi", 265),
        Country("ml", "Mali", 223),
        Country("mr", "Mauritania (‫موريتانيا‬‎)", 222),
        Country("mu", "Mauritius (Moris)", 230),
        Country("ma", "Morocco (‫المغرب‬‎)", 212),
        Country("mz", "Mozambique (Moçambique)", 258),
        Country("na", "Namibia (Namibië)", 264),
        Country("ne", "Niger (Nijar)", 227),
        Country("ng", "Nigeria", 234),
        Country("cg", "Congo (Republic) (Congo-Brazzaville)", 242),
        Country("rw", "Rwanda", 250),
        Country("st", "São Tomé and Príncipe (São Tomé e Príncipe)", 239),
        Country("sn", "Senegal (Sénégal)", 221),
        Country("sc", "Seychelles", 248),
        Country("sl", "Sierra Leone", 232),
        Country("so", "Somalia (Soomaaliya)", 252),
        Country("za", "South Africa", 27),
        Country("ss", "South Sudan (‫جنوب السودان‬‎)", 211),
        Country("sd", "Sudan (‫السودان‬‎)", 249),
        Country("sz", "Swaziland", 268),
        Country("tz", "Tanzania", 255),
        Country("tg", "Togo", 228),
        Country("tn", "Tunisia (‫تونس‬‎)", 216),
        Country("ug", "Uganda", 256),
        Country("eh", "Western Sahara (‫الصحراء الغربية‬‎)", 212),
        Country("zm", "Zambia", 260),
        Country("zw", "Zimbabwe", 263),
    )

    /**
     * Asia countries
     */
    fun asia() = listOf(
        Country("af", "Afghanistan (‫افغانستان‬‎)", 93),
        Country("am", "Armenia (Հայաստան)", 374),
        Country("az", "Azerbaijan (Azərbaycan)", 994),
        Country("bh", "Bahrain (‫البحرين‬‎)", 973),
        Country("bd", "Bangladesh (বাংলাদেশ)", 880),
        Country("bt", "Bhutan (འབྲུག)", 975),
        Country("io", "British Indian Ocean Territory", 246),
        Country("bn", "Brunei", 673),
        Country("kh", "Cambodia (កម្ពុជា)", 855),
        Country("cn", "China (中国)", 86),
        Country("cx", "Christmas Island", 61),
        Country("cc", "Cocos (Keeling) Islands", 61),
        Country("ge", "Georgia (საქართველო)", 995),
        Country("hk", "Hong Kong (香港)", 852),
        Country("in", "India (भारत)", 91),
        Country("id", "Indonesia", 62),
        Country("ir", "Iran (‫ایران‬‎)", 98),
        Country("iq", "Iraq (‫العراق‬‎)", 964),
        Country("il", "Israel (‫ישראל‬‎)", 972),
        Country("jp", "Japan (日本)", 81),
        Country("jo", "Jordan (‫الأردن‬‎)", 962),
        Country("kz", "Kazakhstan (Казахстан)", 7),
        Country("kw", "Kuwait (‫الكويت‬‎)", 965),
        Country("kg", "Kyrgyzstan (Кыргызстан)", 996),
        Country("la", "Laos (ລາວ)", 856),
        Country("lb", "Lebanon (‫لبنان‬‎)", 961),
        Country("mo", "Macau (澳門)", 853),
        Country("my", "Malaysia", 60),
        Country("mv", "Maldives", 960),
        Country("mn", "Mongolia (Монгол)", 976),
        Country("mm", "Myanmar (Burma) (မြန်မာ)", 95),
        Country("np", "Nepal (नेपाल)", 977),
        Country("kp", "North Korea (조선 민주주의 인민 공화국)", 850),
        Country("om", "Oman (‫عُمان‬‎)", 968),
        Country("pk", "Pakistan (‫پاکستان‬‎)", 92),
        Country("ph", "Philippines", 63),
        Country("qa", "Qatar (‫قطر‬‎)", 974),
        Country("sa", "Saudi Arabia (‫المملكة العربية السعودية‬‎)", 966),
        Country("sg", "Singapore", 65),
        Country("kr", "South Korea (대한민국)", 82),
        Country("lk", "Sri Lanka (ශ්‍රී ලංකාව)", 94),
        Country("ps", "Palestine (‫فلسطين‬‎)", 970),
        Country("sy", "Syria (‫سوريا‬‎)", 963),
        Country("tw", "Taiwan (台灣)", 886),
        Country("tj", "Tajikistan", 992),
        Country("th", "Thailand (ไทย)", 66),
        Country("tl", "Timor-Leste", 670),
        Country("tr", "Turkey (Türkiye)", 90),
        Country("tm", "Turkmenistan", 993),
        Country("ae", "United Arab Emirates (‫الإمارات العربية المتحدة‬‎)", 971),
        Country("uz", "Uzbekistan (Oʻzbekiston)", 998),
        Country("vn", "Vietnam (Việt Nam)", 84),
        Country("ye", "Yemen (‫اليمن‬‎)", 967),
    )

    /**
     * Europe countries
     */
    fun europe() = listOf(
        Country("ax", "Åland Islands", 358),
        Country("al", "Albania (Shqipëri)", 355),
        Country("ad", "Andorra", 376),
        Country("at", "Austria (Österreich)", 43),
        Country("by", "Belarus (Беларусь)", 375),
        Country("be", "Belgium (België)", 32),
        Country("ba", "Bosnia and Herzegovina (Босна и Херцеговина)", 387),
        Country("bg", "Bulgaria (България)", 359),
        Country("hr", "Croatia (Hrvatska)", 385),
        Country("cy", "Cyprus (Κύπρος)", 357),
        Country("cz", "Czech Republic (Česká republika)", 420),
        Country("dk", "Denmark (Danmark)", 45),
        Country("gb", "United Kingdom", 44),
        Country("ee", "Estonia (Eesti)", 372),
        Country("fo", "Faroe Islands (Føroyar)", 298),
        Country("fi", "Finland (Suomi)", 358),
        Country("fr", "France", 33),
        Country("de", "Germany (Deutschland)", 49),
        Country("gi", "Gibraltar", 350),
        Country("gr", "Greece (Ελλάδα)", 30),
        Country("gg", "Guernsey", 44),
        Country("va", "Vatican City (Città del Vaticano)", 39),
        Country("hu", "Hungary (Magyarország)", 36),
        Country("is", "Iceland (Ísland)", 354),
        Country("ie", "Ireland", 353),
        Country("im", "Isle of Man", 44),
        Country("it", "Italy (Italia)", 39),
        Country("je", "Jersey", 44),
        Country("xk", "Kosovo", 383),
        Country("lv", "Latvia (Latvija)", 371),
        Country("li", "Liechtenstein", 423),
        Country("lt", "Lithuania (Lietuva)", 370),
        Country("lu", "Luxembourg", 352),
        Country("mt", "Malta", 356),
        Country("md", "Moldova (Republica Moldova)", 373),
        Country("mc", "Monaco", 377),
        Country("me", "Montenegro (Crna Gora)", 382),
        Country("nl", "Netherlands (Nederland)", 31),
        Country("mk", "Macedonia (FYROM) (Македонија)", 389),
        Country("no", "Norway (Norge)", 47),
        Country("pl", "Poland (Polska)", 48),
        Country("pt", "Portugal", 351),
        Country("ro", "Romania (România)", 40),
        Country("ru", "Russia (Россия)", 7),
        Country("sm", "San Marino", 378),
        Country("rs", "Serbia (Србија)", 381),
        Country("sk", "Slovakia (Slovensko)", 421),
        Country("si", "Slovenia (Slovenija)", 386),
        Country("es", "Spain (España)", 34),
        Country("se", "Sweden (Sverige)", 46),
        Country("ch", "Switzerland (Schweiz)", 41),
        Country("ua", "Ukraine (Україна)", 380),
    )

    /**
     * North America countries
     */
    fun northAmerica() = listOf(
        Country("ai", "Anguilla", 264),
        Country("ag", "Antigua and Barbuda", 268),
        Country("bs", "Bahamas", 242),
        Country("bb", "Barbados", 246),
        Country("bz", "Belize", 501),
        Country("bm", "Bermuda", 441),
        Country("ca", "Canada", 1),
        Country("ky", "Cayman Islands", 345),
        Country("cr", "Costa Rica", 506),
        Country("cu", "Cuba", 53),
        Country("dm", "Dominica", 767),
        Country("do", "Dominican Republic (República Dominicana)", 1),
        Country("sv", "El Salvador", 503),
        Country("gl", "Greenland (Kalaallit Nunaat)", 299),
        Country("gd", "Grenada", 473),
        Country("gt", "Guatemala", 502),
        Country("ht", "Haiti", 509),
        Country("hn", "Honduras", 504),
        Country("jm", "Jamaica", 1876),
        Country("mq", "Martinique", 596),
        Country("mx", "Mexico (México)", 52),
        Country("ms", "Montserrat", 1664),
        Country("ni", "Nicaragua", 505),
        Country("pa", "Panama (Panamá)", 507),
        Country("pr", "Puerto Rico", 1),
        Country("bl", "Saint Barthélemy (Saint-Barthélemy)", 590),
        Country("kn", "Saint Kitts and Nevis", 1869),
        Country("vc", "Saint Vincent and the Grenadines", 1784),
        Country("sx", "Sint Maarten", 721),
        Country("tc", "Turks and Caicos Islands", 1649),
        Country("us", "United States", 1),
        Country("vg", "British Virgin Islands", 284),
    )

    /**
     * South America countries
     */
    fun southAmerica() = listOf(
        Country("ar", "Argentina", 54),
        Country("aw", "Aruba", 297),
        Country("bo", "Bolivia", 591),
        Country("bq", "Caribbean Netherlands", 599),
        Country("br", "Brazil (Brasil)", 55),
        Country("cl", "Chile", 56),
        Country("co", "Colombia", 57),
        Country("cw", "Curaçao", 599),
        Country("ec", "Ecuador", 593),
        Country("fk", "Falkland Islands (Islas Malvinas)", 500),
        Country("py", "Paraguay", 595),
        Country("pe", "Peru (Perú)", 51),
        Country("sr", "Suriname", 597),
        Country("tt", "Trinidad and Tobago", 1868),
        Country("uy", "Uruguay", 598),
        Country("ve", "Venezuela", 58),
    )

    /**
     * Oceania countries
     */
    fun oceania() = listOf(
        Country("as", "American Samoa", 684),
        Country("au", "Australia", 61),
        Country("ck", "Cook Islands", 682),
        Country("fm", "Micronesia", 691),
        Country("fj", "Fiji", 679),
        Country("pf", "French Polynesia (Polynésie française)", 689),
        Country("gu", "Guam", 671),
        Country("ki", "Kiribati", 686),
        Country("mh", "Marshall Islands", 692),
        Country("nr", "Nauru", 674),
        Country("nc", "New Caledonia (Nouvelle-Calédonie)", 687),
        Country("nz", "New Zealand", 64),
        Country("nu", "Niue", 683),
        Country("nf", "Norfolk Island", 672),
        Country("pw", "Palau", 680),
        Country("pg", "Papua New Guinea", 675),
        Country("pn", "Pitcairn", 64),
        Country("ws", "Samoa", 685),
        Country("sb", "Solomon Islands", 677),
        Country("tk", "Tokelau", 690),
        Country("to", "Tonga", 676),
        Country("tv", "Tuvalu", 688),
        Country("vu", "Vanuatu", 678),
        Country("wf", "Wallis and Futuna", 681),
    )
}