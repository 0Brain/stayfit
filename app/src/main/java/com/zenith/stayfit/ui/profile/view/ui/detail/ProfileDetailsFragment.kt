
package com.zenith.stayfit.ui.profile.view.ui.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.zenith.stayfit.R
import java.util.Locale
import java.util.SortedSet

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private lateinit var countriesSpinnerItems: SortedSet<String>
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
