package com.yasserakbbach.phonenumberpicker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yasserakbbach.phonenumberpicker.databinding.CountryItemBinding
import com.yasserakbbach.phonenumberpicker.models.Country

class CountryAdapter(private val presenter : Presenter) : ListAdapter<Country, CountryAdapter.CountryHolder>(
    CountryComparator()
) {

    class CountryHolder(private val binding : CountryItemBinding, private val presenter: Presenter) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {

            binding.apply {
                val context = root.context
                countryFlag.load(
                    context.resources.getIdentifier(country.resourceNameDrawable, "drawable", context.packageName)
                )
                countryName.text = country.name
                countryCode.text = country.countryCodeFormatted

                binding.root.setOnClickListener {
                    presenter.onCountryClick(country)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {

        return CountryHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            presenter
        )
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class CountryComparator : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem.iso2 == newItem.iso2
    }

    interface Presenter {
        fun onCountryClick(country: Country)
    }
}