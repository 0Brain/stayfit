package com.zenith.stayfit.ui.profile.view.ui.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.ProfileFragmentBinding
import java.util.*


class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private lateinit var countriesSpinnerItems:SortedSet<String>
//    private lateinit var binding:

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (locale in Locale.getAvailableLocales()) {
            if (!TextUtils.isEmpty(locale.displayCountry)) {
                countriesSpinnerItems.add(locale.displayCountry)
            }
        }


    }
}