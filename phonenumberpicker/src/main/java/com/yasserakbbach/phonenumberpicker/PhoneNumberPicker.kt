package com.yasserakbbach.phonenumberpicker

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yasserakbbach.phonenumberpicker.databinding.CountryListBinding
import com.yasserakbbach.phonenumberpicker.databinding.PhoneNumberPickerBinding
import com.yasserakbbach.phonenumberpicker.models.Country
import com.yasserakbbach.phonenumberpicker.adapters.CountryAdapter
import com.yasserakbbach.phonenumberpicker.adapters.OnCountrySelected
import com.yasserakbbach.phonenumberpicker.models.Continent
import com.yasserakbbach.phonenumberpicker.utils.CountryFactory
import com.yasserakbbach.phonenumberpicker.utils.disableCopyPaste
import java.util.*

/**
 * Basic widget built on Material Text Input to wrap picking phone number.
 *
 * @author: Yasser AKBBACH
 */
class PhoneNumberPicker(context: Context, private val attrs: AttributeSet?) : LinearLayout(context, attrs),
    CountryAdapter.Presenter {

    /**
     * To keep track of the selected country
     */
    private lateinit var mSelectedCountry : Country

    /**
     * Reference of the layout to handle the UI changes
     */
    private val binding : PhoneNumberPickerBinding by lazy {
        PhoneNumberPickerBinding.inflate(
            LayoutInflater.from(context), this, false
        )
    }

    /**
     * List of the current countries
     */
    private lateinit var mCountries: List<Country>

    /**
     * Bottom sheet dialog the wraps the countries + search of country by name
     */
    private val countryBS: BottomSheetDialog by lazy {
        BottomSheetDialog(context).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            behavior.isFitToContents = false
        }
    }

    /**
     * Bottom sheet layout of country list
     */
    private val countryBinding: CountryListBinding by lazy {
        CountryListBinding.inflate(LayoutInflater.from(context), this, false)
    }

    /**
     * Listener of country selection
     */
    private var mOnCountrySelected : OnCountrySelected? = null

    /**
     * To initialize everything
     */
    init {

        addView(binding.root)
        binding.apply {
            phoneNumberLayout.setStartIconOnClickListener {
                initCountryList()
            }
        }
        initAttributes()
        preventDeletion(mSelectedCountry.countryCodeFormatted)
        focusSelectionToEnd()
    }

    /**
     * Init attributes passed from XML
     */
    private fun initAttributes() {

        context?.withStyledAttributes(attrs, R.styleable.PhoneNumberPicker) {
            val textColor = getColor(R.styleable.PhoneNumberPicker_textColor, DEFAULT_TEXT_COLOR)
            val borderColor = getColor(R.styleable.PhoneNumberPicker_outlineBorderColor, DEFAULT_OUTLINE_BORDER_COLOR)
            val textSize = getDimensionPixelSize(R.styleable.PhoneNumberPicker_textSize, DEFAULT_TEXT_SIZE)
            val defaultCountry = getString(R.styleable.PhoneNumberPicker_defaultCountry) ?: DEFAULT_COUNTRY_KEY
            val continents = getInteger(R.styleable.PhoneNumberPicker_continents, DEFAULT_CONTINENT_KEY)
            val maxLength = getInteger(R.styleable.PhoneNumberPicker_maxLength, DEFAULT_MAX_LENGTH)

            mCountries = CountryFactory.onlyContinents(continents)

            binding.apply {
                phoneNumber.filters = arrayOf(InputFilter.LengthFilter(maxLength))
                phoneNumber.disableCopyPaste()
                phoneNumber.setTextColor(textColor)
                phoneNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
                phoneNumberLayout.boxStrokeColor = borderColor
                mSelectedCountry = Country.byIso2(defaultCountry, mCountries) ?: mCountries[0]
                loadSelectedCountry()
            }
        }
    }

    /**
     * Get the current country list
     */
    val currentCountries : List<Country>
        get() = mCountries

    /**
     * Init country list
     */
    private fun initCountryList() {

        val countriesAdapter = CountryAdapter(this)
        countriesAdapter.submitList(mCountries)
        countryBinding.apply {
            countryList.adapter = countriesAdapter
            searchView.setQuery("", false)
        }

        countryBS.apply {
            setContentView(countryBinding.root)
            show()
        }

        // Search query
        countryBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                if(query != null) {

                    countriesAdapter.submitList(mCountries.filter { it.name.lowercase(Locale.ENGLISH)
                        .contains(
                        query.lowercase(
                            Locale.ENGLISH
                        )
                    ) })
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != null) {

                    countriesAdapter.submitList(mCountries.filter { it.name.lowercase(Locale.ENGLISH)
                        .contains(
                        newText.lowercase(
                            Locale.ENGLISH
                        )
                    ) })
                }
                return true
            }
        })
    }

    /**
     * Handle on country click
     */
    override fun onCountryClick(country: Country) {

        mSelectedCountry = country
        loadSelectedCountry()
        //val phoneNumber = binding.phoneNumber
        val countryCode = country.countryCodeFormatted

        focusSelectionToEnd()

        // Prevent deleting country code
        preventDeletion(countryCode)

        countryBS.hide()
        mOnCountrySelected?.setOnCountrySelected(country)
    }

    /**
     * Prevent deletion on certain point to avoid country code getting wiped
     */
    private fun preventDeletion(countryCode : String) {

        binding.phoneNumber.apply {
            setOnKeyListener { _, keyCode, _ ->

                KeyEvent.KEYCODE_DEL == keyCode && countryCode.length == this.text.toString().length
            }
        }
    }

    /**
     * Focus input to the last input character
     */
    private fun focusSelectionToEnd() {

        binding.apply {
            phoneNumber.requestFocus()
            phoneNumber.setOnClickListener {
                phoneNumber.setSelection(phoneNumber.text.toString().length)
            }
        }
    }

    /**
     * Render the selected country's flag + country code
     */
    private fun loadSelectedCountry() {

        binding.apply {
            phoneNumberLayout.startIconDrawable = ContextCompat.getDrawable(context, loadCountryFlag(mSelectedCountry.resourceNameDrawable))
            phoneNumber.setText(mSelectedCountry.countryCodeFormatted)
        }
    }

    /**
     * Get the drawable id of a given drawable name
     */
    private fun loadCountryFlag(drawableName : String) =
        context.resources.getIdentifier(drawableName, "drawable", context.packageName)

    /**
     * Get full phone number (with +)
     */
    fun getFullPhoneNumber() =
        binding.phoneNumber.text.toString()

    /**
     * Get the selected country
     */
    fun getSelectedCountry() = mSelectedCountry

    /**
     * Filter the countries according to passed continents,
     * For more details check [Continent]
     * XML attribute:
     * app:continents="all|africa|asia|europe|north_america|south_america|oceania"
     *
     */
    fun onlyContinents(vararg continents : Continent) {

        mCountries = CountryFactory.onlyContinents(*continents)
    }

    /**
     * Exclude certain countries by iso2 criteria,
     * Check [CountryFactory] to get ISO2s
     */
    fun exceptCountries(vararg iso2s : String) {

        mCountries = mCountries.filter {
            it.iso2 !in iso2s
        }
    }

    /**
     * Add listener on country selection
     */
    fun setOnCountrySelected(onCountrySelected: OnCountrySelected) {
        mOnCountrySelected = onCountrySelected
    }

    /**
     * Change text color by a resource color,
     * XML attribute: app:textColor="@color/black"
     */
    fun setTextColor(@ColorRes color : Int) {

        binding.phoneNumber.setTextColor(
            ContextCompat.getColor(context, color)
        )
    }

    /**
     * Change text color by hexadecimal color
     * e.g: #FF0000 (Red color)
     * XML attribute: app:textColor="#FF0000"
     */
    fun setTextColor(color : String) {

        binding.phoneNumber.setTextColor(Color.parseColor(color))
    }

    /**
     * Change the outline border color of the TextInputLayout by resource color,
     * XML attribute: app:outlineBorderColor="@color/blue"
     */
    fun setOutlineBorderColor(@ColorRes color : Int) {

        binding.phoneNumberLayout.boxStrokeColor = ContextCompat.getColor(context, color)
    }

    /**
     * Change the outline border color of the TextInputLayout by hexadecimal color,
     * e.g: #0000FF (Blue color)
     * XML attribute: app:outlineBorderColor="#0000FF"
     */
    fun setOutlineBorderColor(color : String) {

        binding.phoneNumberLayout.boxStrokeColor = Color.parseColor(color)
    }

    /**
     * Change text size, the used unit is PIXEL
     * XML attribute: app:textSize="18sp"
     */
    fun setTextSize(size : Float) {

        binding.phoneNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    /**
     * Set default country flag by given iso2,
     * NB: In case the countries were filtered according to specific continents and the given flag wasn't there
     * the default flag would be the first country of the current country list
     */
    fun setDefaultCountry(iso2 : String) {

        mSelectedCountry = Country.byIso2(iso2, mCountries) ?: mCountries[0]
        loadSelectedCountry()
    }

    /**
     * To change max length of digits including the [+] digit!
     * XML attribute: app:maxLength="14"
     */
    fun setMaxLength(maxLength : Int) {

        binding.phoneNumber.filters = arrayOf(InputFilter.LengthFilter(maxLength))
    }

    companion object {

        var DEFAULT_TEXT_COLOR = R.color.black
        var DEFAULT_OUTLINE_BORDER_COLOR = R.color.outlineBorderColor
        const val DEFAULT_TEXT_SIZE = 18
        const val DEFAULT_COUNTRY_KEY = "ma"
        const val DEFAULT_CONTINENT_KEY = 63
        const val DEFAULT_MAX_LENGTH = 14
    }
}