package org.noodule.launcher.native

object NoduleNative {
    init {
        System.loadLibrary("nodule_core")
    }

    external fun versionName(): String
    external fun versionCode(): Int
    external fun packageId(): String
}