package org.noodule.launcher.game

import android.content.Context
import android.util.Log
import java.io.File

/**
 * The fixed library load order. Do not reorder:
 *   libfmod.so -> libcocos2dcpp.so -> liblauncherfix -> libnodule_core.so
 */
object GameLoader {

    fun loadAndLaunch(context: Context) {
        val gamePkg = "com.robtopx.geometryjump"
        val gameInfo = context.packageManager.getApplicationInfo(gamePkg, 0)
        val nativeLibDir = File(gameInfo.nativeLibraryDir)

        val fmod = File(nativeLibDir, "libfmod.so")
        val cocos = File(nativeLibDir, "libcocos2dcpp.so")

        if (!fmod.exists() || !cocos.exists()) {
            Log.e("Nodule", "Game native libraries not found in ${nativeLibDir.absolutePath}")
            return
        }

        try {
            System.load(fmod.absolutePath)
            Log.i("Nodule", "loaded ${fmod.name}")

            System.load(cocos.absolutePath)
            Log.i("Nodule", "loaded ${cocos.name}")

            // TODO: liblauncherfix equivalent — file redirection, symbol patches
            // System.load(launcherFixPath)

            System.loadLibrary("nodule_core")
            Log.i("Nodule", "loaded libnodule_core.so")

            // TODO: create GLSurfaceView + Cocos renderer, drive the game loop
        } catch (t: Throwable) {
            Log.e("Nodule", "Load sequence failed", t)
        }
    }
}