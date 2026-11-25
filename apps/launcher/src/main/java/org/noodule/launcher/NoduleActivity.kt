package org.noodule.launcher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.noodule.launcher.game.InstallSource
import org.noodule.launcher.native.NoduleNative

class NoduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
        }

        val info = TextView(this).apply {
            text = buildString {
                appendLine("Nodule ${NoduleNative.versionName()} (${NoduleNative.versionCode()})")
                appendLine("Package: ${NoduleNative.packageId()}")
                appendLine("Install source: ${InstallSource.label(this@NoduleActivity)}")
            }
        }

        val launch = Button(this).apply {
            text = "Launch"
            setOnClickListener {
                startActivity(Intent(this@NoduleActivity, GameLaunchActivity::class.java))
            }
        }

        layout.addView(info)
        layout.addView(launch)
        setContentView(layout)
    }
}