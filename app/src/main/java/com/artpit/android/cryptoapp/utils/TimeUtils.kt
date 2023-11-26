package com.artpit.android.cryptoapp.utils

import com.artpit.android.cryptoapp.api.ApiFactory.BASE_IMAGE_URL
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertTimestampToTime(timeStamp: Long?): String {
    if (timeStamp == null) {
        return ""
    }

    val stamp = Timestamp(timeStamp * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

