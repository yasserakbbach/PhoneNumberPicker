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
        Country("dz", "Algeria (???????????????????????)", 213),
        Country("ao", "Angola", 244),
        Country("bj", "Benin (B??nin)", 229),
        Country("bw", "Botswana", 267),
        Country("bf", "Burkina Faso", 226),
        Country("bi", "Burundi (Uburundi)", 257),
        Country("cv", "Cape Verde (Cabo Verdi)", 238),
        Country("cm", "Cameroon (Cameroun)", 237),
        Country("cf", "Central African Republic (R??publique centrafricaine)", 236),
        Country("td", "Chad (Tchad)", 235),
        Country("km", "Comoros (????????? ????????????????)", 269),
        Country("ci", "Ivory Coast (C??te d???Ivoire)", 225),
        Country("cd", "Congo (DRC) (Jamhuri ya Kidemokrasia ya Kongo)", 243),
        Country("dj", "Djibouti", 253),
        Country("eg", "Egypt (???????????????)", 20),
        Country("gq", "Equatorial Guinea (Guinea Ecuatorial)", 240),
        Country("er", "Eritrea", 291),
        Country("et", "Ethiopia", 251),
        Country("ga", "Gabon", 241),
        Country("gm", "Gambia", 220),
        Country("gh", "Ghana (Gaana)", 233),
        Country("gn", "Guinea (Guin??e)", 224),
        Country("gw", "Guinea-Bissau (Guin?? Bissau)", 245),
        Country("ke", "Kenya", 254),
        Country("ls", "Lesotho", 266),
        Country("lr", "Liberia", 231),
        Country("ly", "Libya (???????????????????)", 218),
        Country("mg", "Madagascar (Madagasikara)", 261),
        Country("mw", "Malawi", 265),
        Country("ml", "Mali", 223),
        Country("mr", "Mauritania (???????????????????????????)", 222),
        Country("mu", "Mauritius (Moris)", 230),
        Country("ma", "Morocco (?????????????????????)", 212),
        Country("mz", "Mozambique (Mo??ambique)", 258),
        Country("na", "Namibia (Namibi??)", 264),
        Country("ne", "Niger (Nijar)", 227),
        Country("ng", "Nigeria", 234),
        Country("cg", "Congo (Republic) (Congo-Brazzaville)", 242),
        Country("rw", "Rwanda", 250),
        Country("st", "S??o Tom?? and Pr??ncipe (S??o Tom?? e Pr??ncipe)", 239),
        Country("sn", "Senegal (S??n??gal)", 221),
        Country("sc", "Seychelles", 248),
        Country("sl", "Sierra Leone", 232),
        Country("so", "Somalia (Soomaaliya)", 252),
        Country("za", "South Africa", 27),
        Country("ss", "South Sudan (??????????? ????????????????????)", 211),
        Country("sd", "Sudan (???????????????????????)", 249),
        Country("sz", "Swaziland", 268),
        Country("tz", "Tanzania", 255),
        Country("tg", "Togo", 228),
        Country("tn", "Tunisia (?????????????????)", 216),
        Country("ug", "Uganda", 256),
        Country("eh", "Western Sahara (????????????????? ????????????????????)", 212),
        Country("zm", "Zambia", 260),
        Country("zw", "Zimbabwe", 263),
    )

    /**
     * Asia countries
     */
    fun asia() = listOf(
        Country("af", "Afghanistan (???????????????????????????)", 93),
        Country("am", "Armenia (????????????????)", 374),
        Country("az", "Azerbaijan (Az??rbaycan)", 994),
        Country("bh", "Bahrain (???????????????????????)", 973),
        Country("bd", "Bangladesh (????????????????????????)", 880),
        Country("bt", "Bhutan (???????????????)", 975),
        Country("io", "British Indian Ocean Territory", 246),
        Country("bn", "Brunei", 673),
        Country("kh", "Cambodia (?????????????????????)", 855),
        Country("cn", "China (??????)", 86),
        Country("cx", "Christmas Island", 61),
        Country("cc", "Cocos (Keeling) Islands", 61),
        Country("ge", "Georgia (??????????????????????????????)", 995),
        Country("hk", "Hong Kong (??????)", 852),
        Country("in", "India (????????????)", 91),
        Country("id", "Indonesia", 62),
        Country("ir", "Iran (???????????????????)", 98),
        Country("iq", "Iraq (?????????????????????)", 964),
        Country("il", "Israel (???????????????????)", 972),
        Country("jp", "Japan (??????)", 81),
        Country("jo", "Jordan (?????????????????????)", 962),
        Country("kz", "Kazakhstan (??????????????????)", 7),
        Country("kw", "Kuwait (?????????????????????)", 965),
        Country("kg", "Kyrgyzstan (????????????????????)", 996),
        Country("la", "Laos (?????????)", 856),
        Country("lb", "Lebanon (???????????????????)", 961),
        Country("mo", "Macau (??????)", 853),
        Country("my", "Malaysia", 60),
        Country("mv", "Maldives", 960),
        Country("mn", "Mongolia (????????????)", 976),
        Country("mm", "Myanmar (Burma) (??????????????????)", 95),
        Country("np", "Nepal (???????????????)", 977),
        Country("kp", "North Korea (?????? ???????????? ?????? ?????????)", 850),
        Country("om", "Oman (???????????????????)", 968),
        Country("pk", "Pakistan (???????????????????????)", 92),
        Country("ph", "Philippines", 63),
        Country("qa", "Qatar (???????????????)", 974),
        Country("sa", "Saudi Arabia (????????????????? ?????????????? ??????????????????????)", 966),
        Country("sg", "Singapore", 65),
        Country("kr", "South Korea (????????????)", 82),
        Country("lk", "Sri Lanka (??????????????? ???????????????)", 94),
        Country("ps", "Palestine (?????????????????????)", 970),
        Country("sy", "Syria (???????????????????)", 963),
        Country("tw", "Taiwan (??????)", 886),
        Country("tj", "Tajikistan", 992),
        Country("th", "Thailand (?????????)", 66),
        Country("tl", "Timor-Leste", 670),
        Country("tr", "Turkey (T??rkiye)", 90),
        Country("tm", "Turkmenistan", 993),
        Country("ae", "United Arab Emirates (??????????????????? ?????????????? ????????????????????)", 971),
        Country("uz", "Uzbekistan (O??zbekiston)", 998),
        Country("vn", "Vietnam (Vi???t Nam)", 84),
        Country("ye", "Yemen (???????????????????)", 967),
    )

    /**
     * Europe countries
     */
    fun europe() = listOf(
        Country("ax", "??land Islands", 358),
        Country("al", "Albania (Shqip??ri)", 355),
        Country("ad", "Andorra", 376),
        Country("at", "Austria (??sterreich)", 43),
        Country("by", "Belarus (????????????????)", 375),
        Country("be", "Belgium (Belgi??)", 32),
        Country("ba", "Bosnia and Herzegovina (?????????? ?? ??????????????????????)", 387),
        Country("bg", "Bulgaria (????????????????)", 359),
        Country("hr", "Croatia (Hrvatska)", 385),
        Country("cy", "Cyprus (????????????)", 357),
        Country("cz", "Czech Republic (??esk?? republika)", 420),
        Country("dk", "Denmark (Danmark)", 45),
        Country("gb", "United Kingdom", 44),
        Country("ee", "Estonia (Eesti)", 372),
        Country("fo", "Faroe Islands (F??royar)", 298),
        Country("fi", "Finland (Suomi)", 358),
        Country("fr", "France", 33),
        Country("de", "Germany (Deutschland)", 49),
        Country("gi", "Gibraltar", 350),
        Country("gr", "Greece (????????????)", 30),
        Country("gg", "Guernsey", 44),
        Country("va", "Vatican City (Citt?? del Vaticano)", 39),
        Country("hu", "Hungary (Magyarorsz??g)", 36),
        Country("is", "Iceland (??sland)", 354),
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
        Country("mk", "Macedonia (FYROM) (????????????????????)", 389),
        Country("no", "Norway (Norge)", 47),
        Country("pl", "Poland (Polska)", 48),
        Country("pt", "Portugal", 351),
        Country("ro", "Romania (Rom??nia)", 40),
        Country("ru", "Russia (????????????)", 7),
        Country("sm", "San Marino", 378),
        Country("rs", "Serbia (????????????)", 381),
        Country("sk", "Slovakia (Slovensko)", 421),
        Country("si", "Slovenia (Slovenija)", 386),
        Country("es", "Spain (Espa??a)", 34),
        Country("se", "Sweden (Sverige)", 46),
        Country("ch", "Switzerland (Schweiz)", 41),
        Country("ua", "Ukraine (??????????????)", 380),
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
        Country("do", "Dominican Republic (Rep??blica Dominicana)", 1),
        Country("sv", "El Salvador", 503),
        Country("gl", "Greenland (Kalaallit Nunaat)", 299),
        Country("gd", "Grenada", 473),
        Country("gt", "Guatemala", 502),
        Country("ht", "Haiti", 509),
        Country("hn", "Honduras", 504),
        Country("jm", "Jamaica", 1876),
        Country("mq", "Martinique", 596),
        Country("mx", "Mexico (M??xico)", 52),
        Country("ms", "Montserrat", 1664),
        Country("ni", "Nicaragua", 505),
        Country("pa", "Panama (Panam??)", 507),
        Country("pr", "Puerto Rico", 1),
        Country("bl", "Saint Barth??lemy (Saint-Barth??lemy)", 590),
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
        Country("cw", "Cura??ao", 599),
        Country("ec", "Ecuador", 593),
        Country("fk", "Falkland Islands (Islas Malvinas)", 500),
        Country("py", "Paraguay", 595),
        Country("pe", "Peru (Per??)", 51),
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
        Country("pf", "French Polynesia (Polyn??sie fran??aise)", 689),
        Country("gu", "Guam", 671),
        Country("ki", "Kiribati", 686),
        Country("mh", "Marshall Islands", 692),
        Country("nr", "Nauru", 674),
        Country("nc", "New Caledonia (Nouvelle-Cal??donie)", 687),
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