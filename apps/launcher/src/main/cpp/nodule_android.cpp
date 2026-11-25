#include <jni.h>
#include <android/log.h>

#define LOG_TAG "NoduleCpp"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_org_noodule_launcher_native_NoduleCpp_greeting(JNIEnv* env, jclass) {
    LOGI("nodule_android loaded");
    return env->NewStringUTF("Hello from nodule_android C++");
}