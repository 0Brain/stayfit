
package com.zenith.stayfit.ui.supplements.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Supplement(
  var title: String,
  var subText: String,
  var description: String
) : Parcelable
