package org.noodule.launcher.game

import android.content.Context
import android.os.Build

/**
 * Neutral label, not a verdict. Reports where the game was installed from.
 * No blocking, no piracy judgement — just the raw signal.
 */
object InstallSource {
    private const val GAME_PKG = "com.robtopx.geometryjump"

    fun label(context: Context): String {
        val pm = context.packageManager
        val installer = if (Build.VERSION.SDK_INT >= 30) {
            try {
                pm.getInstallSourceInfo(GAME_PKG).installingPackageName
            } catch (t: Throwable) {
                null
            }
        } else {
            @Suppress("DEPRECATION")
            try {
                pm.getInstallerPackageName(GAME_PKG)
            } catch (t: Throwable) {
                null
            }
        }

        return when (installer) {
            "com.android.vending" -> "Google Play"
            null -> "unknown (sideload)"
            else -> installer
        }
    }
}