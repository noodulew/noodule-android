package org.noodule.launcher

import android.app.Activity
import android.os.Bundle
import android.util.Log
import org.noodule.launcher.game.GameLoader

class GameLaunchActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Nodule", "GameLaunchActivity onCreate — calling GameLoader")
        GameLoader.loadAndLaunch(this)
    }
}