package com.core.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.core.common.utils.SettingsConstants
import com.core.domain.repository.ContactUsManager

internal class ContactUsManagerImpl(private val context: Context) : ContactUsManager {

    override fun contactUs() {
        Intent.createChooser(
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse(
                    buildString {
                        append("mailto:${SettingsConstants.mailTo}")
                        append("?subject=${USER_INQUIRY}")
                    }
                )
            },
            EMAIL_VIA
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(this)
        }
    }

    companion object {
        private const val EMAIL_VIA = "Email via"
        private const val USER_INQUIRY = "User inquiry"
    }
}
