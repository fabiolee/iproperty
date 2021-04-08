package com.fabiolee.iproperty.ui

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment

fun Fragment.dpToPx(dp: Int): Int {
    return (dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT) + 0.5f).toInt()
}