package com.sweed.saunameet.additem

import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sweed.saunameet.R
import com.sweed.saunameet.database.Oil
import java.text.SimpleDateFormat


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
}


@BindingAdapter("moodImage")
fun ImageView.setMoodImage(progress: Int) {
    setImageResource(
        when (progress) {
            0 -> R.drawable.ic_0_sauna_face_tired
            1 -> R.drawable.ic_1_sauna_face_sad
            2 -> R.drawable.ic_2_sauna_face_neutral
            3 -> R.drawable.ic_3_sauna_face_content_08
            4 -> R.drawable.ic_4_sauna_face_happy
            else -> R.drawable.ic_2_sauna_face_neutral
        }
    )
}

@BindingAdapter("favoriteImage")
fun ImageView.setFavoriteImage(oil: Oil?) {
    oil?.let {
        if (oil.favorite) setImageResource(R.drawable.ic_baseline_favorite_24)
        else setImageResource(R.drawable.ic_baseline_favorite_border_24)

    } ?: run {
        setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }

}


@BindingAdapter("oilcreatedat")
fun TextView.setCreatedAt(oil: Oil?) {
    oil?.let {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(oil.createdMillis)
        text = dateString //String.format("Date: %s", dateString)
    }
}

@BindingAdapter("oillastused")
fun TextView.setOillastused(oil: Oil?) {
    oil?.let {
//        Todo use R.string.neverdeault

        var dateString = "Never"
        oil.lastUsedMillis?.let {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            dateString = simpleDateFormat.format(oil.createdMillis)
        }

        text = dateString
    }

}
