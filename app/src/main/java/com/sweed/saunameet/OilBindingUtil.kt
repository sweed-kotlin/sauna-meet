package com.sweed.saunameet.additem

import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sweed.saunameet.R
import com.sweed.saunameet.database.Oil


@BindingAdapter("textname")
fun TextView.setOilName(oil: Oil?) {
    oil?.let {
        text = oil.name
    }

}

@BindingAdapter("editname")
fun EditText.setEditName(oil: Oil?) {
    oil?.let {
        setText(oil.name)
    }

}

@BindingAdapter("oilrating")
fun RatingBar.setSleepQualityString(oil: Oil?) {
    oil?.let {
        rating = oil.rating
    }
}

@BindingAdapter("oilimage")
fun ImageView.setOilImage(oil: Oil?) {
    oil?.let {
        setImageResource(R.drawable.ic_sauna_meet_logo)
    }

//    setImageResource(when (item.sleepQuality) {
//        0 -> R.drawable.ic_sleep_0
//        1 -> R.drawable.ic_sleep_1
//        2 -> R.drawable.ic_sleep_2
//        3 -> R.drawable.ic_sleep_3
//        4 -> R.drawable.ic_sleep_4
//        5 -> R.drawable.ic_sleep_5
//        else -> R.drawable.ic_sleep_active
//    })
}
